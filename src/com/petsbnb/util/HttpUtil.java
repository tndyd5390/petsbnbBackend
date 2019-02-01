package com.petsbnb.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HttpUtil {
			
	public static String MapToQueryString(HashMap<String, String> map, String charSet){
		StringBuilder sbQuery = new StringBuilder();

		try {
			Iterator<Entry<String, String>> iter = map.entrySet().iterator();
			while(iter.hasNext()){
				Entry<String, String> entry = iter.next();
				String key = entry.getKey();
				String value = entry.getValue();

				sbQuery.append("&");
				sbQuery.append(URLEncoder.encode(key, charSet) + "=" +URLEncoder.encode(value, charSet));
			}
			sbQuery.delete(0, 0);
		} catch (Exception e) {

		}

		return sbQuery.toString();
	}

	public static Object callURL(String sURL, HashMap<String, String> getData, HashMap<String, String> postData, String charSet){
        HashMap<String, String> hashmapResult = new HashMap<String, String>();

        URL url;
        HttpURLConnection urlConnection;

        String getParams = MapToQueryString(getData, charSet);
        String postParams = MapToQueryString(postData, charSet);

        if (!"".equals(getParams)){
        	sURL += "?" + getParams;
        }
        try {
			url = new URL(sURL);
			urlConnection = (HttpURLConnection) url.openConnection();
	        urlConnection.setDoOutput(true);
	        urlConnection.setInstanceFollowRedirects(false);
	        urlConnection.setRequestMethod("POST");
	        urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        urlConnection.setRequestProperty("charset", charSet);

			byte[] bytePostData	= postParams.getBytes(charSet);
			int postDataLength	= bytePostData.length;
			urlConnection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
			urlConnection.setUseCaches(false);
			urlConnection.getOutputStream().write(bytePostData);

			StringBuilder sb = new StringBuilder();
			Reader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
			for (int c = 0; (c = in.read()) >=0; ) {
				sb.append((char)c);
			}
			String responseBody = sb.toString();
	        int httpStatus = urlConnection.getResponseCode();
	        hashmapResult.put("httpStatus", String.valueOf(httpStatus));
	        hashmapResult.put("responseBody", responseBody);
		} catch (IOException e) {
			e.printStackTrace();
        } catch(IllegalArgumentException e){
			e.printStackTrace();
        } catch (Exception e) {
			e.printStackTrace();
		}

        return hashmapResult;
	}

	public static Object callURL(String sURL, HashMap<String, String> getData, HashMap<String, String> postData, String charSet, String token){
        HashMap<String, String> hashmapResult = new HashMap<String, String>();

        URL url;
        HttpURLConnection urlConnection;

        String getParams = MapToQueryString(getData, charSet);
        String postParams = MapToQueryString(postData, charSet);

        if (!"".equals(getParams)){
        	sURL += "?" + getParams;
        }
        try {
			url = new URL(sURL);
			urlConnection = (HttpURLConnection) url.openConnection();
	        urlConnection.setDoOutput(true);
	        urlConnection.setInstanceFollowRedirects(false);
	        urlConnection.setRequestMethod("POST");
	        urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        urlConnection.setRequestProperty("charset", charSet);
	        urlConnection.setRequestProperty("Authorization", token);
			byte[] bytePostData	= postParams.getBytes(charSet);
			int postDataLength	= bytePostData.length;
			urlConnection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
			urlConnection.setUseCaches(false);
			urlConnection.getOutputStream().write(bytePostData);

			StringBuilder sb = new StringBuilder();
			Reader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
			for (int c = 0; (c = in.read()) >=0; ) {
				sb.append((char)c);
			}
			String responseBody = sb.toString();
	        int httpStatus = urlConnection.getResponseCode();
	        hashmapResult.put("httpStatus", String.valueOf(httpStatus));
	        hashmapResult.put("responseBody", responseBody);
		} catch (IOException e) {
			e.printStackTrace();
        } catch(IllegalArgumentException e){
			e.printStackTrace();
        } catch (Exception e) {
			e.printStackTrace();
		}

        return hashmapResult;
	}

	
	public static String getRequestBody(HttpServletRequest request) throws IOException {
		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null){
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while((bytesRead = bufferedReader.read(charBuffer)) > 0){
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null){
				try {
					bufferedReader.close();
				} catch (Exception ex) {
					throw ex;
				}
			}
		}

		body = stringBuilder.toString();
		return body;
	}
	
	public static String getIamportToken() {
		HashMap<String, String> hashmapJson = new HashMap<String,String>();
		HashMap<String, String> hashmapRes = new HashMap<String, String>();
		String result = "";
		
		try{
			hashmapJson.put("imp_key", "9253904035662612");
			hashmapJson.put("imp_secret", "SAZMbdV9usytxYzUyevOUC6CzXF0apS2ZROb5KkzcS3JRCEpR9dLMjomh3msseus6jDc4YMgRP0IsE8j");
			String charSet = "UTF-8";
			hashmapRes = (HashMap<String, String>) HttpUtil.callURL("https://api.iamport.kr/users/getToken", null, hashmapJson, charSet);
			
			if("200".equals(hashmapRes.get("httpStatus"))){
				String responseBody = hashmapRes.get("responseBody");
				JsonParser jsonParser = new JsonParser();
				JsonObject jsonObject = (JsonObject)jsonParser.parse(responseBody);
				JsonObject response = (JsonObject)jsonObject.get("response");
				String token = response.get("access_token").toString();
				token = token.substring(1, token.length()-1);
				result = token;
			}else{
				result = "";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static boolean cancelIamport(String imp_uid, String reason){
		HashMap<String, String> hashmapJson = new HashMap<String, String>();
		HashMap<String, String> hashmapRes = new HashMap<String, String>();
		boolean result;
		try{
			hashmapJson.put("imp_uid", imp_uid);
			hashmapJson.put("reason", reason);
			String charSet = "UTF-8";
			hashmapRes = (HashMap<String, String>) callURL("https://api.iamport.kr/payments/cancel", null, hashmapJson, charSet, getIamportToken());
			if("200".equals(hashmapRes.get("httpStatus"))){
				result = true;
			}else{
				result = false;
			}
		}catch(Exception e){
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	public static boolean sendFcm(String title, String body, String token) throws Exception{
		final String apiKey = "AAAAJuOsoX4:APA91bGVJeBud0Ski4Iba1nQxGMm842QsZ4xVKB7lPv_l82h6rqEbqbayPUnWN3RXntBOEgPrEhqhKyxRnuc_7xsrGIMJNLO5nyf5zgKo_2pms8v9QHMMbFH5wxxdQezwgV0wCO-YvZg";
		
		URL url = new URL("https://fcm.googleapis.com/fcm/send");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "key=" + apiKey);
        conn.setDoOutput(true);

        String input = "{\"notification\" : {\"title\" : \"" + title + " \", \"body\" : \"" + body + "\"}, \"to\":\"" + token + "\"}";

        OutputStream os = conn.getOutputStream();
        
        // 서버에서 날려서 한글 깨지는 사람은 아래처럼  UTF-8로 인코딩해서 날려주자
        os.write(input.getBytes("UTF-8"));
        os.flush();
        os.close();

        int responseCode = conn.getResponseCode();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
		return 200 == responseCode;
	}
	
	
	
}