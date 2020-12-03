package com.olemika.taskmanager.main.data.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.olemika.taskmanager.main.data.db.entity.Group;

import java.util.List;

@Dao
public interface GroupDao {
    @Query("SELECT * FROM 'group'")
    List<Group> getAll();

    @Query("SELECT * FROM 'group' WHERE id = :id")
    Group getById(long id);

    @Insert
    void insert(Group group);

    @Delete
    void delete(Group group);
}
