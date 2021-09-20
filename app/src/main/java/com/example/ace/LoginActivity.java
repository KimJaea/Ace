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

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText editText_ID = findViewById(R.id.editTextID);
        EditText editText_PW = findViewById(R.id.editTextPW);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ID = editText_ID.getText().toString();
                String PW = editText_PW.getText().toString();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("ID", ID);
                intent.putExtra("PW", PW);
                startActivity(intent);
            }
        });
        TextView buttonSignUp = findViewById(R.id.textSignUp);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SigninActivity.class);
                startActivity(intent);
            }
        });
        TextView buttonAccount = findViewById(R.id.textAccount);
        buttonAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "계정 찾기 서비스는 준비중입니다.", Toast.LENGTH_LONG).show();
                // 계정 찾기
            }
        });
    }
}
