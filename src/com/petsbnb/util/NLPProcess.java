package com.petsbnb.util;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NLPProcess {

   private String apiUrl = "http://aiopen.etri.re.kr:8000/WiseNLU";
   private String accessKey = "14ee6f69-451b-4555-9807-a0d84683c6b5";
   private String analysisCode = "dparse";
   public Map<String, Object> adjectiveMap = new HashMap<>();
   Gson gson = new Gson();
   Map<String, Object> request = new HashMap<>();
    Map<String, Object> parm = new HashMap<>();
    
    public int NLPrun(String text) throws Exception{
        Map<String, Object> request = new HashMap<>();
        Map<String, String> argument = new HashMap<>();
 
        argument.put("analysis_code", analysisCode);
        argument.put("text", text);
 
        request.put("access_key", accessKey);
        request.put("argument", argument);
 
        URL url;
        Integer responseCode = null;
        String responBodyJson = null;
        Map<String, Object> responeBody = null;
        int emotionRate = 0;
        try {
            url = new URL(apiUrl);
             HttpURLConnection con = (HttpURLConnection) url.openConnection();
             con.setRequestMethod("POST");
             con.setDoOutput(true);

             DataOutputStream wr = new DataOutputStream(con.getOutputStream());
             wr.write(gson.toJson(request).getBytes("UTF-8"));
             wr.flush();
             wr.close();
  
             responseCode = con.getResponseCode();
             InputStream is = con.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is));
             StringBuffer sb = new StringBuffer();
  
             String inputLine = "";
             while ((inputLine = br.readLine()) != null) {
                 sb.append(inputLine);
             }
             responBodyJson = sb.toString();
             System.out.println(responBodyJson);
             if ( responseCode != 200 ) {
                 System.out.println("[error] " + responBodyJson);
                 return 0;
             }
  
             responeBody = gson.fromJson(responBodyJson, Map.class);
             Integer result = ((Double) responeBody.get("result")).intValue();
             Map<String, Object> returnObject;
             List<Map> sentences;
  
             if ( result != 0 ) {
  
                 System.out.println("[error] " + responeBody.get("result"));
                 return 0;
             }
  
             returnObject = (Map<String, Object>) responeBody.get("return_object");
             sentences = (List<Map>) returnObject.get("sentence");
  
             Map<String, Morpheme> morphemesMap = new HashMap<String, Morpheme>();
             Map<String, Dependency> dependencyMap = new HashMap<String, Dependency>();
             Map<String, NameEntity> nameEntitiesMap = new HashMap<String, NameEntity>();
             List<Morpheme> morphemes = null;
             List<Dependency> dependencys = null;
             List<NameEntity> nameEntities = null;
  
             for( Map<String, Object> sentence : sentences ) {
                 List<Map<String, Object>> morphologicalAnalysisResult = (List<Map<String, Object>>) sentence.get("morp");
                 for( Map<String, Object> morphemeInfo : morphologicalAnalysisResult ) {
                     String lemma = (String) morphemeInfo.get("lemma");
                     Morpheme morpheme = morphemesMap.get(lemma);
                     if ( morpheme == null ) {
                         morpheme = new Morpheme(lemma, (String) morphemeInfo.get("type"), 1);
                         morphemesMap.put(lemma, morpheme);
                     } else {
                         morpheme.count = morpheme.count + 1;
                     }
                 }
                 
                 List<Map<String, Object>> nameEntityRecognitionResult = (List<Map<String, Object>>) sentence.get("NE");
                 for( Map<String, Object> nameEntityInfo : nameEntityRecognitionResult ) {
                     String name = (String) nameEntityInfo.get("text");
                     NameEntity nameEntity = nameEntitiesMap.get(name);
                     if ( nameEntity == null ) {
                         nameEntity = new NameEntity(name, (String) nameEntityInfo.get("type"), 1);
                         nameEntitiesMap.put(name, nameEntity);
                     } else {
                         nameEntity.count = nameEntity.count + 1;
                     }
                 }
             }
             
             if ( 0 < dependencyMap.size() ) {
                dependencys = new ArrayList<Dependency>(dependencyMap.values());
                dependencys.sort( (dependency1, dependency2) -> {
                     return dependency2.count - dependency1.count;
                 });
             }
             
             if ( 0 < morphemesMap.size() ) {
                 morphemes = new ArrayList<Morpheme>(morphemesMap.values());
                 morphemes.sort( (morpheme1, morpheme2) -> {
                     return morpheme2.count - morpheme1.count;
                 });
             }
  
             if ( 0 < nameEntitiesMap.size() ) {
                 nameEntities = new ArrayList<NameEntity>(nameEntitiesMap.values());
                 nameEntities.sort( (nameEntity1, nameEntity2) -> {
                     return nameEntity2.count - nameEntity1.count;
                 });
             }
             java.util.List<String> words = new java.util.ArrayList();
             // 형태소들 중 명사들에 대해서 많이 노출된 순으로 출력 ( 최대 5개 )
             morphemes
                 .stream()
                 .filter(morpheme -> {
                     return morpheme.type.equals("NNG") ||
                             morpheme.type.equals("NNP") ||
                             morpheme.type.equals("NNB");
                 })
                 .limit(5)
                 .forEach(morpheme -> {
                    words.add(morpheme.text);
                     System.out.println("[명사] " + morpheme.text + " ("+morpheme.count+")" );
                 });
  
             // 형태소들 중 동사들에 대해서 많이 노출된 순으로 출력 ( 최대 5개 )
             System.out.println("");
             morphemes
                 .stream()
                 .filter(morpheme -> {
                     return morpheme.type.equals("VV");
                 })
                 .limit(5)
                 .forEach(morpheme -> {
                    words.add(morpheme.text);
                     System.out.println("[동사] " + morpheme.text + " ("+morpheme.count+")" );
                 });
             
             // 형태소들 중 형용사들에 대해서 많이 노출된 순으로 출력 ( 최대 5개 )
             System.out.println("");
             
             morphemes
                 .stream()
                 .filter(morpheme -> {
                     return morpheme.type.equals("VA");
                 })
                 .limit(5)
                 .forEach(morpheme -> {
                     words.add(morpheme.text);
                     adjectiveMap.put(morpheme.text, morpheme.count);
                 });
             
             
             if(adjectiveMap.size() > 0) {
                adjectiveMap.forEach((k,v) -> {
                    System.out.println(k+" : "+v);
                 });
             }
             
             MongoConnect mc = new MongoConnect();
             emotionRate = mc.getEmotion(words);
             
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return emotionRate;
    }
    
    
    static public class Morpheme {

       final String text;
       final String type;
       Integer count;

       public Morpheme(String text, String type, Integer count) {
          this.text = text;
          this.type = type;
          this.count = count;
       }
    }
    
    static public class NameEntity {
        final String text;
        final String type;
        Integer count;
        public NameEntity (String text, String type, Integer count) {
            this.text = text;
            this.type = type;
            this.count = count;
        }
    }
    
    static public class Dependency {

       final String text;
       final String label;
       Integer count;

       public Dependency(String text, String label, Integer count) {
          this.text = text;
          this.label = label;
          this.count = count;
       }
    }

}
