package com.example.ace;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class FindActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("비밀번호 찾기");

        EditText id = findViewById(R.id.id);
        Button find = findViewById(R.id.find);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idString = id.getText().toString();

                // ID 가입 여부 확인하기
                // 가입 안했으면
                // Toast.makeText(getApplicationContext(), "가입 정보가 없습니다.", Toast.LENGTH_LONG).show();

                // DB에서 회원 정보 확인 후 비밀번호 송신
                Toast.makeText(getApplicationContext(), "문자를 전송했습니다.", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
