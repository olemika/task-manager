package com.olemika.taskmanager.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.olemika.taskmanager.R;

public class addTaskActivity extends AppCompatActivity {
    private static final String TAG = "addTaskActivity";
    private long groupId;
    TextView textView;
    private TextInputEditText editText;
    private String newTask;
    private Button buttonAdd;


    AppDatabase db = App.getInstance().getDatabase();
    public GroupDao gDao = db.groupDao();
    public TaskDao tDao =  db.taskDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
        groupId = getIntent().getLongExtra("GroupId", 1);
        Log.d(TAG, "onCreate: get extras from list Activity " + gDao.getById(groupId).getName());

        editText = (TextInputEditText) findViewById(R.id.task_add_edit_text);
        textView = (TextView) findViewById(R.id.text_add_to_group);
        textView.setText(gDao.getById(groupId).getName());

        buttonAdd = (Button) findViewById(R.id.task_add_button);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newTask = editText.getText().toString();
                Log.d(TAG, "onClick: get edit text input = " + newTask);

                Task task = new Task(newTask, groupId, false);
                tDao.insert(task);
                Log.d(TAG, "Add new instance to Tasks = " + task);

                Intent intent = new Intent(addTaskActivity.this, listActivity.class);
                intent.putExtra("GroupId", groupId);

                startActivity(intent);
                addTaskActivity.this.finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(addTaskActivity.this, listActivity.class);
        intent.putExtra("GroupId", groupId);
        startActivity(intent);
        addTaskActivity.this.finish();
    }



}
