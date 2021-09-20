package com.example.ace;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class RecycleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("수정사항 건의사항");

        // 수정사항
        EditText input_stuff = findViewById(R.id.input_stuff);
        // String stuff = input_stuff.getText();

        CheckBox checkbox_paper = findViewById(R.id.checkbox_paper);
        CheckBox checkbox_plastic = findViewById(R.id.checkbox_plastic);
        CheckBox checkbox_glass = findViewById(R.id.checkbox_glass);
        CheckBox checkbox_metal = findViewById(R.id.checkbox_metal);
        CheckBox checkbox_plastic_bag = findViewById(R.id.checkbox_plastic_bag);

        Button apply = findViewById(R.id.apply);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "수정사항이 건의되었습니다.", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }
}
