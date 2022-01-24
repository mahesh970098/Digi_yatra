/*
 * Copyright SecureKey Technologies Inc. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.github.trustbloc.ariesdemo;

import com.couchbase.lite.Database;

import org.hyperledger.aries.api.Provider;
import org.hyperledger.aries.api.Store;

import java.nio.charset.StandardCharsets;

public class storeProvider implements Provider {
    DatabaseManager databaseManager;
    Database database;

    public storeProvider(DatabaseManager databaseManager)
    {
        this.databaseManager=databaseManager;
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
        this.database = databaseManager.openOrCreateDatabase(GlobalApplication.getAppContext(),s);
        return new storeImplementation(database);
    }

    @Override
    public void setStoreConfig(String s, byte[] bytes) throws Exception {
        String storeConfig= new String(bytes, StandardCharsets.UTF_8);
        System.out.println("storeConfig :"+storeConfig);

    }
}
