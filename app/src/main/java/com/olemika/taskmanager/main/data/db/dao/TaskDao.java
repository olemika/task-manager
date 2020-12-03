package com.olemika.taskmanager.main.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.olemika.taskmanager.main.data.db.entity.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Query("SELECT * FROM task WHERE id = :id")
    Task getById(long id);

    @Query("SELECT * FROM task WHERE groupId = :groupId")
    List<Task> getByGroupId(long groupId);

    @Query("SELECT * FROM task WHERE groupId = :groupId")
    LiveData<List<Task>> getLiveDataByGroupId(long groupId);

    @Query("SELECT COUNT(*) FROM task WHERE groupId = :groupId")
    int getCountByGroupId(long groupId);

    @Query("SELECT COUNT(*) FROM task WHERE groupId = :groupId AND isDone = 1")
    int getCountCheckedByGroupId(long groupId);

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

}
