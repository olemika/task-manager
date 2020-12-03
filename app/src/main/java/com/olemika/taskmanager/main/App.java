package com.olemika.taskmanager.main;

import android.app.Application;
import android.util.Log;

import androidx.core.provider.FontRequest;
import androidx.emoji.text.EmojiCompat;
import androidx.emoji.text.FontRequestEmojiCompatConfig;
import androidx.room.Room;

import com.olemika.taskmanager.R;
import com.olemika.taskmanager.main.data.db.AppDatabase;

public class App extends Application {
    private static final String TAG = "App";
    public static App instance;
    private AppDatabase database;

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: App");
        super.onCreate();

        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
        Log.d(TAG, "Check App " + database);


        FontRequest fontRequest = new FontRequest(
                "com.google.android.gms.fonts",
                "com.google.android.gms",
                "Noto Color Emoji Compat",
                R.array.com_google_android_gms_fonts_certs);
        EmojiCompat.Config cfg = new FontRequestEmojiCompatConfig(getApplicationContext(), fontRequest);
        EmojiCompat.init(cfg);

    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }




}
