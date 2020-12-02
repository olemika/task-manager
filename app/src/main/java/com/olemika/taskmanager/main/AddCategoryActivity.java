package com.olemika.taskmanager.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.olemika.taskmanager.R;

public class AddCategoryActivity extends AppCompatActivity {

    private TextInputEditText editText;
    private String newCategory;
    private Button buttonAdd;
    AppDatabase db = App.getInstance().getDatabase();
    public GroupDao gDao = db.groupDao();


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_category_activity);


        editText = (TextInputEditText) findViewById(R.id.category_add_edit_text);
        buttonAdd = (Button) findViewById(R.id.category_add_button);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newCategory = editText.getText().toString();

                Group group = new Group(newCategory);
                gDao.insert(group);


                Intent intent = new Intent(AddCategoryActivity.this, MainActivity.class);
                startActivity(intent);
                AddCategoryActivity.this.finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddCategoryActivity.this, MainActivity.class);
        startActivity(intent);
        AddCategoryActivity.this.finish();
    }


}
