package com.olemika.taskmanager.main.data.db.entity;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {
        @Index(value = "name", unique = true),
}
)
public class Group {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;

    public Group(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
