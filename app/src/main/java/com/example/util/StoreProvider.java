package com.example.util;

import android.content.Context;

import com.couchbase.lite.Database;
import com.example.digi_yatra_12.GlobalApplication;

import org.hyperledger.aries.api.Provider;
import org.hyperledger.aries.api.Store;

import java.nio.charset.StandardCharsets;

public class StoreProvider implements Provider {
    DatabaseManager databaseManager;
    Database database;
    Context context;

    public StoreProvider(DatabaseManager databaseManager, Context context)
    {
        this.databaseManager=databaseManager;
        this.context = context;
    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public byte[] getStoreConfig(String s) throws Exception {
        return new byte[0];
    }

    @Override
    public Store openStore(String s) throws Exception {
        this.database = databaseManager.openOrCreateDatabase(context,s);
        return new StoreImplementation(database);
    }

    @Override
    public void setStoreConfig(String s, byte[] bytes) throws Exception {
        String storeConfig= new String(bytes, StandardCharsets.UTF_8);
        System.out.println("storeConfig :"+storeConfig);

    }
}

