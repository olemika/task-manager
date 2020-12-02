package com.olemika.taskmanager.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.olemika.taskmanager.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    //vars
    public List<Group> groups;
    Button addCategoryBtn;
    TextView textView;
    RecyclerView recyclerView;

    //database
    AppDatabase db = App.getInstance().getDatabase();
    public GroupDao gDao = db.groupDao();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gDao = db.groupDao();
        groups = gDao.getAll();

        if (gDao.getAll().size() == 0) {
            textView = findViewById(R.id.no_category_attention);
            recyclerView = findViewById(R.id.main_recycler);
            textView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);

        } else {
            initGroupRecycler();
        }


        addCategoryBtn = findViewById(R.id.add_category_btn);
        addCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCategoryActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });



    }


    private void initGroupRecycler() {
        Log.d(TAG, "initRecyclerView: init group recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.main_recycler);
        mainAdapter adapter = new mainAdapter(this, groups);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void onBackPressed() {
        //ничего не делать??
    }
}
