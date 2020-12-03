package com.olemika.taskmanager.main.presentation.ui.task.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.olemika.taskmanager.main.App;
import com.olemika.taskmanager.main.data.db.AppDatabase;
import com.olemika.taskmanager.main.data.db.dao.GroupDao;
import com.olemika.taskmanager.main.data.db.dao.TaskDao;
import com.olemika.taskmanager.main.data.db.entity.Task;
import com.olemika.taskmanager.main.data.repository.TaskRepositoryImpl;
import com.olemika.taskmanager.main.domain.repository.taskrepository.TaskRepository;

import java.util.List;

public class TaskListViewModel extends ViewModel {
    private AppDatabase db = App.getInstance().getDatabase();
    GroupDao gDao = db.groupDao();
    TaskRepositoryImpl repo = new TaskRepositoryImpl();

    private static final String TAG = "TaskListViewModel";

    public LiveData<List<Task>> getTaskListLiveData(Long id) {
        Log.d(TAG, "getTaskListLiveData: " + id);

        return repo.getTaskList(id);
    }

    public String getGroupName(Long id) {
        return gDao.getById(id).getName();
    }

    public void updateTask(Task item) {
        repo.updateTask(item);
    }

    public void deleteTask(Task item) {
        repo.deleteTask(item);
    }
}
