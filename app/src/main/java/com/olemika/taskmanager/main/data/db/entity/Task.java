package com.olemika.taskmanager.main.data.db.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(foreignKeys = @ForeignKey(entity = Group.class, parentColumns = "id", childColumns = "groupId",  onDelete = ForeignKey.CASCADE))
public class Task {

    @PrimaryKey(autoGenerate = true)
    public long id;
    public String taskName;
    public long groupId;
    public boolean isDone;

    public Task(String taskName, long groupId, boolean isDone) {
        this.taskName = taskName;
        this.groupId = groupId;
        this.isDone = isDone;
    }


    public String getTaskName() {
        return taskName;
    }

    public long getGroupId() {
        return groupId;
    }

    public boolean getDone() {
        return isDone;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setGroupName(int groupName) {
        this.groupId = groupName;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

}


