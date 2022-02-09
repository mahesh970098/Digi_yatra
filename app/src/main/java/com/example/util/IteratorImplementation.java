package com.example.util;
/*
 * Copyright SecureKey Technologies Inc. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
import com.couchbase.lite.Array;
import com.couchbase.lite.Result;

import org.hyperledger.aries.api.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.List;
public class IteratorImplementation implements Iterator {
    List<Result> rows;
    //    storeImplementation store;
    int currentIndex=0;
    int currentKeyIndex=0;
//    currentKey   string

    public IteratorImplementation(List<Result> rows){
        this.rows =rows;
//        this.store=store;
    }
    @Override
    public void close() throws Exception {

    }

    @Override
    public String key() throws Exception {
        Result row = this.rows.get(currentKeyIndex);

        return row.getString("key");
    }

    @Override
    public boolean next() throws Exception {
        if ((this.rows.size()==this.currentIndex)||(this.rows.size()==0)){
            return false;
        }
        this.currentKeyIndex = this.currentIndex;
        this.currentIndex++;

        return true;
    }

    @Override
    public byte[] tags() throws Exception {
        Result row = rows.get(currentKeyIndex);
        Array a = row.getArray("tags");
        JSONArray jTag = new JSONArray();
        for (int i=0;i<a.count();i++)
        {
            JSONObject j = new JSONObject();
            try {
                j.put("name",a.getString(i));
                String v = row.getString(a.getString(i));
                if (v!="5599Dataevolve"){j.put("value",v);}
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jTag.put(j);
        }

        String tag = jTag.toString();

        return tag.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public byte[] value() throws Exception
    {
        return rows.get(currentKeyIndex).getString("value").getBytes(StandardCharsets.UTF_8);
    }
}
