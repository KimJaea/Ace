package com.example.ace;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.UserData;

public class FindActivity extends AppCompatActivity {
    boolean able = false;
    String pw = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        ActionBar actionBar = getSupportActionBar();
        // actionBar.setTitle("비밀번호 찾기");
        actionBar.hide();

        EditText id = findViewById(R.id.id);
        Button find = findViewById(R.id.find);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idString = id.getText().toString();
                if(idString.equals("")) {
                    Toast.makeText(getApplicationContext(), "정확한 정보를 입력해 주세요.", Toast.LENGTH_LONG).show();
                } else {
                    Amplify.DataStore.query(
                            UserData.class,
                            items -> {
                                while (items.hasNext()) {
                                    UserData item = items.next();
                                    if(item.getUserId().toString().equals(idString) ) {
                                        pw = item.getUserPw();
                                        able = true;
                                        break;
                                    }
                                }
                            },
                            failure -> Log.e("Amplify", "Could not query DataStore", failure)
                    );

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(able) {
                                // 문자 전송 기능
                                // Toast.makeText(getApplicationContext(), "문자를 전송했습니다.", Toast.LENGTH_LONG).show();

                                // Toast.makeText(getApplicationContext(), "계정 찾기 서비스는 준비중입니다.", Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(), "계정 비밀번호는 " + pw + "입니다.", Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "가입 정보가 없습니다.", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }
                    }, 500);

                }

            }
        });
    }
}
