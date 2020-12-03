package com.olemika.taskmanager.main.presentation.ui.main;

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
import com.olemika.taskmanager.main.App;
import com.olemika.taskmanager.main.data.db.AppDatabase;
import com.olemika.taskmanager.main.data.db.entity.Group;
import com.olemika.taskmanager.main.data.db.dao.GroupDao;
import com.olemika.taskmanager.main.presentation.ui.main.adapter.MainAdapter;
import com.olemika.taskmanager.main.presentation.ui.main.add.AddCategoryActivity;
import com.olemika.taskmanager.main.presentation.ui.task.ListActivity;
import com.olemika.taskmanager.main.presentation.ui.utils.AdapterClickListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterClickListener<Group>{
    private static final String TAG = "MainActivity";

    //vars
    public List<Group> groups;
    Button addCategoryBtn;
    TextView textView;
    RecyclerView recyclerView;
    MainAdapter adapter;

    //database
    AppDatabase db = App.getInstance().getDatabase();
    public GroupDao gDao = db.groupDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gDao = db.groupDao();
        groups = gDao.getAll();
        recyclerView = findViewById(R.id.main_recycler);
        adapter = new MainAdapter(this, groups, this);
        if (gDao.getAll().size() == 0) {
            textView = findViewById(R.id.no_category_attention);
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
//                MainActivity.this.finish();
            }
        });


    }


    private void initGroupRecycler() {
        Log.d(TAG, "initRecyclerView: init group recyclerview.");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void onBackPressed() {
        //ничего не делать??
    }

//    @Override
//    public void onClick(Group group) {
//        Intent intent = new Intent(this, listActivity.class);
//        intent.putExtra("GroupId", group.id);
//        startActivity(intent);
//    }
//
//    @Override
//    public void onDelete(Group group) {
//        gDao.delete(group);
//        adapter.deleteGroup(group);
////        Intent intent = new Intent(this, MainActivity.class);
////        startActivity(intent);
//    }

    @Override
    public void onClick(Group item) {
                Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("GroupId", item.id);
        startActivity(intent);
    }

    @Override
    public void onDelete(Group item) {
        gDao.delete(item);
        adapter.deleteGroup(item);
    }
}
