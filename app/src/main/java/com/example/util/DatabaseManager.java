package com.example.util;
import android.content.Context;

import com.couchbase.lite.CouchbaseLite;
import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.DatabaseConfiguration;
import com.couchbase.lite.Document;
import com.couchbase.lite.MutableDocument;
/*
 * Copyright SecureKey Technologies Inc. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

public class DatabaseManager {
    public void initCouchbaseLite(Context context){
        CouchbaseLite.init(context);
    }
    public Database openOrCreateDatabase(Context context,String namespace) throws CouchbaseLiteException {
        DatabaseConfiguration config = new DatabaseConfiguration();

        config.setDirectory(context.getFilesDir().getAbsolutePath());
        System.out.println(context.getFilesDir().getAbsolutePath());

        Database database = new Database(namespace, config);
        System.out.println("############################################");
        System.out.println("store opened for:"+namespace);

        return database;
    }
}
