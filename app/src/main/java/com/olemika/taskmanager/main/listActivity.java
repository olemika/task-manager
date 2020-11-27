package com.olemika.taskmanager.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.olemika.taskmanager.R;

import java.util.ArrayList;
import java.util.List;


public class listActivity extends AppCompatActivity  {
    private static final String TAG = "listActivity";
    public List<Task> myTasks = new ArrayList<>();
    private long groupId;

    AppDatabase db = App.getInstance().getDatabase();
    public GroupDao gDao = db.groupDao();
    public TaskDao tDao =  db.taskDao();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_activity);
        Log.d(TAG, "onCreate: started");


        groupId = getIntent().getLongExtra("GroupId", 1);
        Log.d(TAG, "get Extras group name = " + (gDao.getById(groupId)).getName());
        Log.d(TAG, "Check we have something in tasks = " + myTasks);
        myTasks = tDao.getByGroupId(groupId);
        initListRecycler();

        Button addButton = (Button) findViewById(R.id.add_button_task);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listActivity.this, addTaskActivity.class);
                intent.putExtra("GroupId", groupId);
                startActivity(intent);
            }
        });



    }

    private void initListRecycler() {
        Log.d(TAG, "initRecyclerView: init task list recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.task_recycler);
        taskAdapter adapter = new taskAdapter(this, myTasks);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
            }

    }


