package com.example.util;
import com.couchbase.lite.Array;
import com.couchbase.lite.ArrayFunction;
import com.couchbase.lite.DataSource;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Expression;
import com.couchbase.lite.MutableArray;
import com.couchbase.lite.MutableDocument;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryBuilder;
import com.couchbase.lite.Result;
import com.couchbase.lite.ResultSet;
import com.couchbase.lite.SelectResult;

import org.hyperledger.aries.api.Iterator;
import org.hyperledger.aries.api.Store;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
public class StoreImplementation implements Store{
    Database database;

    public StoreImplementation(Database database) {
        this.database = database;
    }

    @Override
    public void batch(byte[] bytes) throws Exception {

    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public void delete(String s) throws Exception {

    }

    @Override
    public void flush() throws Exception {

    }

    @Override
    public byte[] get(String s) throws Exception {
        System.out.println("In get store:"+s);
        Document document = database.getDocument(s);
        if (document==null){
            throw new Exception("data not found");
        }
        return document.getString("value").getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public byte[] getBulk(byte[] bytes) throws Exception {
        return new byte[0];
    }

    @Override
    public byte[] getTags(String s) throws Exception {

        Document document = database.getDocument(s);
        if (document==null){
            throw new Exception("data not found");
        }
//        List<String> keys = document.getKeys();
        Array keys = document.getArray("tags");
        JSONArray jTag = new JSONArray();
        for (int i=0;i<keys.count();i++)
        {
            String tagKey = keys.getString(i);
            JSONObject j = new JSONObject();
            try {
                j.put("name",tagKey);
                String v = document.getString(tagKey);
                if (v!="5599Dataevolve"){j.put("value",v);}
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jTag.put(j);
        }
        String tag = jTag.toString();

        return tag.getBytes(StandardCharsets.UTF_8);

//        return document.getString("value").getBytes(StandardCharsets.UTF_8);
//        Query query = QueryBuilder
//                .select(SelectResult.all())
//                .from(DataSource.database(database)).where(Expression.property("key")
//                        .equalTo(Expression.string(s)));
//
//        ResultSet rows  = query.execute();
//        List<String> data = new ArrayList<>();
//        for (Result row: rows) data.add(row.getString("value"));
//        int size = data.size();
//
//        if (size==0){
//            throw new Exception("data not found");
//        }
//        else if(size>1){
//            throw new Exception("duplicate key");
//        }
////        return data.get(0).getBytes(StandardCharsets.UTF_8);
//
//        return new byte[0];
    }

    @Override
    public void put(String s, byte[] value, byte[] tags) throws Exception {
        System.out.println("In store put with key:"+s);
        String val= new String(value, StandardCharsets.UTF_8);
        System.out.println("Bytes :"+value);
//        System.out.println(tags.length);
        if(database.getDocument(s)!=null)
        {
            throw new Exception("duplicate key");
        }
        MutableDocument newDocument = new MutableDocument(s);
        newDocument.setString("key", s);
        newDocument.setString("value", val);

        if (tags!=null && tags.length>0) {
            System.out.print("tags exist:");
            String tagArray = new String(tags, StandardCharsets.UTF_8);
            System.out.println("tags:"+tagArray);
            JSONArray jArray = new JSONArray(tagArray);
            JSONObject element;
            MutableArray arr = new MutableArray();
            for(int i = 0; i < jArray.length(); i++){
                element = jArray.getJSONObject(i); // which for example will be Types,TotalPoints,ExpiringToday in the case of the first array(All_Details)
                String tagKey = element.getString("name");
                arr.addString(tagKey);
                String tagValue = "5599Dataevolve";
                if (element.has("value")){tagValue = element.getString("value");}
                newDocument.setString(tagKey,tagValue);
            }
            newDocument.setArray("tags",arr);
        }

        database.save(newDocument);
//        System.out.println("");

    }

    @Override
    public Iterator query(String s, long l) throws Exception {

        if (s == "") {
            throw  new Exception(s+" is not in a valid expression format.it must be in the following format: TagName:TagValue");
        }
        String[] expression = s.split(":");
        String expressionTagName;
        String expressionTagValue;
        expressionTagName=expression[0];
        Query query;
        if (expression.length==2)
        {
            expressionTagValue=expression[1];
            query = QueryBuilder
                    .select(SelectResult.all())
                    .from(DataSource.database(database))
                    .where(Expression.property(expressionTagName).equalTo(Expression.string(expressionTagValue)));

        }

        else {
            query = QueryBuilder.select(SelectResult.all())
                    .from(DataSource.database(database)).where(ArrayFunction
                            .contains(Expression.property("tags"), Expression.string(expressionTagName)));
//
        }
        ResultSet rows  = query.execute();
        return new IteratorImplementation(rows.allResults());

//        List<String> data = new ArrayList<>();
//        for (Result row: rows) data.add(row.getString("value"));
//        int size = data.size();
//
//        if (size==0){
//            throw new Exception("data not found");
//        }
//        else if(size>1){
//            throw new Exception("duplicate key");
//        }
    }
}
