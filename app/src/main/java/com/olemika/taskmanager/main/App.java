package com.olemika.taskmanager.main;

import android.app.Application;
import android.util.Log;

import androidx.room.Room;

import com.vanniktech.emoji.EmojiManager;
import com.vanniktech.emoji.ios.IosEmojiProvider;

public class App extends Application {
    private static final String TAG = "App";
    public static App instance;
    private AppDatabase database;

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: App");
        super.onCreate();

        EmojiManager.install(new IosEmojiProvider());

        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
        Log.d(TAG, "Check App " + database);
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
