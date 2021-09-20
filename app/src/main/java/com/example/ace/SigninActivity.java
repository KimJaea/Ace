package com.example.ace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;

public class SigninActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("계정 찾기");
//
//        EditText id = findViewById(R.id.id);
//        EditText pw = findViewById(R.id.pw);
//
//        Button signin = findViewById(R.id.signin);
//
//        signin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String idString = id.getText().toString();
//                String pwString = pw.getText().toString();
//                // ID 가입 여부 확인하기
//                // 이미 있으면
//                // Toast.makeText(getApplicationContext(), "이미 가입되어 있습니다.", Toast.LENGTH_LONG).show();
//                // 계정 찾기 추천해주기?
//                // return;
//
//                // DB에 회원 정보 넣기
//
//                Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_LONG).show();
//                finish();
//            }
//        });
        
    }
}
