package com.olemika.taskmanager.main.data.repository;

import androidx.lifecycle.LiveData;

import com.olemika.taskmanager.main.App;
import com.olemika.taskmanager.main.data.db.AppDatabase;
import com.olemika.taskmanager.main.data.db.dao.GroupDao;
import com.olemika.taskmanager.main.data.db.dao.TaskDao;
import com.olemika.taskmanager.main.data.db.entity.Task;
import com.olemika.taskmanager.main.domain.repository.taskrepository.TaskRepository;

import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {
    private AppDatabase db = App.getInstance().getDatabase();
    private TaskDao tDao = db.taskDao();

    @Override
    public void createTask(Task task) {
        tDao.insert(task);
    }

    @Override
    public void updateTask(Task task) {
        tDao.update(task);
    }

    @Override
    public void deleteTask(Task task) {
        tDao.delete(task);
    }

    @Override
    public LiveData<List<Task>> getTaskList(Long id) {
        return tDao.getLiveDataByGroupId(id);
    }
}
