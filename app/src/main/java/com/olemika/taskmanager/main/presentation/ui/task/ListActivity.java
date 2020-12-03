package com.olemika.taskmanager.main.presentation.ui.task;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.olemika.taskmanager.R;
import com.olemika.taskmanager.main.data.db.entity.Task;
import com.olemika.taskmanager.main.presentation.ui.task.adapter.TaskAdapter;
import com.olemika.taskmanager.main.presentation.ui.task.adapter.TaskDiffUtil;
import com.olemika.taskmanager.main.presentation.ui.task.add.AddTaskActivity;
import com.olemika.taskmanager.main.presentation.ui.task.viewmodel.TaskListViewModel;
import com.olemika.taskmanager.main.presentation.ui.utils.AdapterClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ListActivity extends AppCompatActivity implements AdapterClickListener<Task> {
    private static final String TAG = "listActivity";
    public List<Task> myTasks = new ArrayList<>();
    private long groupId;
    private TextView attention;
    private TextView title;
    private TaskDiffUtil taskDiffUtil;


    //    AppDatabase db = App.getInstance().getDatabase();
//    public GroupDao gDao = db.groupDao();
//    public TaskDao tDao = db.taskDao();
    private RecyclerView rv;
    private String groupName;

    private TaskListViewModel viewModel;
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_activity);
        Log.d(TAG, "onCreate: started");
        initViews();
    }


    private Observer<List<Task>> taskObserver = tasks -> {
        if (tasks.isEmpty()) {
            attention.setVisibility(View.VISIBLE);
            title.setVisibility(View.GONE);
            rv.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
            attention.setText("Сейчас нет ни одной задачи в категории " + groupName);
        } else {
            attention.setVisibility(View.GONE);
            title.setVisibility(View.VISIBLE);
            rv.setVisibility(View.VISIBLE);
            title.setVisibility(View.VISIBLE);
        }
        Collections.sort(tasks, (task1, task2) -> Boolean.compare(task1.isDone, task2.isDone));

        taskDiffUtil = new TaskDiffUtil(adapter.mTask, tasks);
        DiffUtil.DiffResult productDiffResult = DiffUtil.calculateDiff(taskDiffUtil);
        adapter.update(tasks);
        productDiffResult.dispatchUpdatesTo(adapter);
    };

    private void initViews() {

        attention = findViewById(R.id.no_task_attention);
        viewModel = new ViewModelProvider(this).get(TaskListViewModel.class);

        adapter = new TaskAdapter(this, myTasks, this);
        groupId = getIntent().getLongExtra("GroupId", 1);
//        Log.d(TAG, "get Extras group name = " + (gDao.getById(groupId)).getName());
        Log.d(TAG, "Check we have something in tasks = " + myTasks);
        groupName = viewModel.getGroupName(groupId);
        title = findViewById(R.id.task_list_title);
        title.setText(viewModel.getGroupName(groupId));

        rv = findViewById(R.id.task_recycler);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
//        if (myTasks.size() == 0) {
//            attention.setVisibility(View.VISIBLE);
//            title.setVisibility(View.GONE);
//            rv.setVisibility(View.GONE);
//            title.setVisibility(View.GONE);
//            attention.setText("Сейчас нет ни одной задачи в категории " + gDao.getById(groupId).getName());
//        } else {
//            initListRecycler();
//        }


        Button addButton = findViewById(R.id.add_button_task);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(ListActivity.this, AddTaskActivity.class);
            intent.putExtra("GroupId", groupId);
            startActivity(intent);
//                listActivity.this.finish();
        });
        viewModel.getTaskListLiveData(groupId).observe(this, taskObserver);

    }
//
//    private void initListRecycler() {
//        Log.d(TAG, "initRecyclerView: init task list recyclerview.");
//        rv.setAdapter(adapter);
//        rv.setLayoutManager(new LinearLayoutManager(this));
//        adapter.update(myTasks);
//    }


    @Override
    public void onBackPressed() {
//        Intent intent = new Intent(listActivity.this, MainActivity.class);
//        startActivity(intent);
//        listActivity.this.finish();
        super.onBackPressed();
    }

    @Override
    public void onClick(Task item) {
        item.setDone(!item.getDone());
        viewModel.updateTask(item);
//        tDao.update(item);
    }

    @Override
    public void onDelete(Task item) {
        viewModel.deleteTask(item);
//        tDao.delete(item);
    }
}