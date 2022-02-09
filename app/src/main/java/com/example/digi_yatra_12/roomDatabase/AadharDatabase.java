/*
package com.example.digi_yatra_12.roomDatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {AAdharData.class}, version = 1)
public abstract class AadharDatabase extends RoomDatabase {
    // below line is to create instance
    // for our database class.
    private static AadharDatabase instance;
    public abstract Dao Dao();

    // on below line we are getting instance for our database.
    public static synchronized AadharDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AadharDatabase.class, "aadhar_data")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(AadharDatabase instance) {
            Dao dao = instance.Dao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
*/
