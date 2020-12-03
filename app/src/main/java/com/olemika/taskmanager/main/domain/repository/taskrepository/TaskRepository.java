package com.olemika.taskmanager.main.domain.repository.taskrepository;

import androidx.lifecycle.LiveData;

import com.olemika.taskmanager.main.data.db.entity.Task;

import java.util.List;

public interface TaskRepository {
   void createTask(Task task);
   void updateTask(Task task);
   void deleteTask(Task task);
   LiveData<List<Task>> getTaskList(Long id);
}
