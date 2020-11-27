package com.olemika.taskmanager.main;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class, Group.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
    public abstract GroupDao groupDao();
}
