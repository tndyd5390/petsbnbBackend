package com.petsbnb.util;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.MongoClientSettings;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.Block;
import com.mongodb.ConnectionString;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.ValidationOptions;
/*import com.mongodb.MongoClientOptions;*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.bson.Document;

public class MongoConnect {
   MongoDatabase database;
   MongoClient mongoClient;
   
   public MongoConnect() {
      mongoClient = MongoClients.create("mongodb://116.124.128.185:27017");
      database = mongoClient.getDatabase("petbnb");
   }
   
   public int getReviewCount() {
      MongoCollection<Document> coll = database.getCollection("review");
      int count = (int)coll.countDocuments();
      
      return count;
   }
   
   public int getEmotion(List<String> words)throws Exception {

      MongoCollection<Document> coll = database.getCollection("dictionary");
      int result = 0;
      for (String word : words) {
         Map<String, Object> map = new HashMap<>();
         Document doc = coll.find(eq("word_root", word)).first();
         if (doc != null) {
            map = new ObjectMapper().readValue(doc.toJson(), Map.class);
            Iterator<String> keys = map.keySet().iterator();
            while (keys.hasNext()) {
               String key = keys.next();
               if ("polarity".equals(key)) {
                  result += Integer.parseInt((String) map.get(key));
               }
            }
         }
      }
      return result;
   }

   Block<Document> printBlock = new Block<Document>() {
      @Override
      public void apply(final Document document) {
         System.out.println(document.toJson());
      }
   };
   
}