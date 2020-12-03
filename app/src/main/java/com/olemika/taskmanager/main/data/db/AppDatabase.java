package com.olemika.taskmanager.main.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.olemika.taskmanager.main.data.db.entity.Group;
import com.olemika.taskmanager.main.data.db.dao.GroupDao;
import com.olemika.taskmanager.main.data.db.entity.Task;
import com.olemika.taskmanager.main.data.db.dao.TaskDao;

@Database(entities = {Task.class, Group.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
    public abstract GroupDao groupDao();
}
