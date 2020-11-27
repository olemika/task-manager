package com.olemika.taskmanager.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.olemika.taskmanager.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    //vars
    public ArrayList<Integer> idImagesGroup = new ArrayList<>();
    public List<Group> groups;
    Button addCategoryBtn;
    ImageButton dltCategory;

    //database
    AppDatabase db = App.getInstance().getDatabase();
    public GroupDao gDao = db.groupDao();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started");
        gDao = db.groupDao();
        Log.d(TAG, "onCreate: init database");
        groups = gDao.getAll();

        initTaskGroupBitmaps();

        addCategoryBtn = findViewById(R.id.add_category_btn);
        addCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCategoryActivity.class);
                MainActivity.this.finish();
                startActivity(intent);
            }
        });



    }

    private void initTaskGroupBitmaps() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps");

        idImagesGroup.add(R.drawable.study);
        idImagesGroup.add(R.drawable.work);
        idImagesGroup.add(R.drawable.sport);
        idImagesGroup.add(R.drawable.pet);

        initGroupRecycler();

    }

    private void initGroupRecycler() {
        Log.d(TAG, "initRecyclerView: init group recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.main_recycler);
        mainAdapter adapter = new mainAdapter(this, idImagesGroup, groups);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



}
