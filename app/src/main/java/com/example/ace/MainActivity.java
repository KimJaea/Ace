package com.example.ace;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent myIntent = getIntent();
        String ID = myIntent.getStringExtra("ID");
        String PW = myIntent.getStringExtra("PW");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(ID + "님 환영합니다.");

        Button buttonQR = findViewById(R.id.buttonQR);
        Button buttonDB = findViewById(R.id.buttonDB);
        Button buttonHow = findViewById(R.id.buttonHow);

        buttonQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QRActivity.class);
                intent.putExtra("ID", ID);
                startActivity(intent);
            }
        });
        buttonDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DBActivity.class);
                intent.putExtra("ID", ID);
                startActivity(intent);
            }
        });
        buttonHow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
                intent.putExtra("ID", ID);
                startActivity(intent);
            }
        });
    }
}