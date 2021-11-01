package com.example.ace;

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

public class SigninActivity extends AppCompatActivity {
    boolean able = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        ActionBar actionBar = getSupportActionBar();
        // actionBar.setTitle("회원 가입");
        actionBar.hide();

        EditText id = findViewById(R.id.id);
        EditText pw = findViewById(R.id.pw);

        Button signin = findViewById(R.id.signin);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                able = true;
                String idString = id.getText().toString();
                String pwString = pw.getText().toString();

                if(idString.equals("") || pwString.equals("")) {
                    Toast.makeText(getApplicationContext(), "정확한 정보를 입력해 주세요.", Toast.LENGTH_LONG).show();
                } else {
                    Amplify.DataStore.query(
                            UserData.class,
                            items -> {
                                while (items.hasNext()) {
                                    UserData item = items.next();
                                    if(item.getUserId().toString().equals(idString) ) {
                                        able = false;
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
                                // Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(), "회원가입 서비스는 준비중입니다.", Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "이미 가입된 ID(전화번호)입니다.", Toast.LENGTH_LONG).show();
                            }
                        }
                    }, 500);

                }
            }
        });
        
    }
}
