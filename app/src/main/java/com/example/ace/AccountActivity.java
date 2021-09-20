package com.example.ace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class AccountActivity extends AppCompatActivity {
    TextView id, point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Intent myIntent = getIntent();
        String ID = myIntent.getStringExtra("ID");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(ID + "님의 계정");

        id = (TextView)findViewById(R.id.id);
        point = (TextView)findViewById(R.id.point);
        id.setText(ID);
        point.setText("0");

        Button check_point = findViewById(R.id.check_point);
        check_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //포인트 내역 확인하기
            }
        });
        Button edit_info = findViewById(R.id.edit_info);
        edit_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "개인정보 수정하기 서비스는 준비중입니다.", Toast.LENGTH_LONG).show();
                // 개인정보 수정하기
            }
        });
        Button sign_out = findViewById(R.id.sign_out);
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "탈퇴하기 서비스는 준비중입니다.", Toast.LENGTH_LONG).show();
                // 탈퇴하기
            }
        });
    }
}
