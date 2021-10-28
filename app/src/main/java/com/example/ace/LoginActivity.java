package com.example.ace;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.UserData;

public class LoginActivity extends AppCompatActivity {
    boolean able = false;

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

                Amplify.DataStore.query(UserData.class,
                        items -> {
                            while (items.hasNext()) {
                                UserData userData = items.next();

                                if(ID.equals(userData.getUserId())) {
                                    if(userData.getUserPw() != null) {
                                        if(PW.equals(userData.getUserPw())) {
                                            able = true;
                                        }
                                    }
                                }
                            }
                        },
                        failure -> Log.e("Amplify", "Could not query DataStore", failure)
                );

                if(ID.equals("0000") && PW.equals("0000"))
                    able = true;
                if(able) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("ID", ID);
                    intent.putExtra("PW", PW);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "계정 정보가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                }

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
                Intent intent = new Intent(getApplicationContext(), FindActivity.class);
                startActivity(intent);
            }
        });

        // Amplify
        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());
            Log.i("Amplify", "Initialized Amplify");
            Toast.makeText(getApplicationContext(), "서버와 연결되었습니다.", Toast.LENGTH_LONG).show();
        } catch (AmplifyException error) {
            Log.e("Amplify", "Could not initialize Amplify", error);
            Toast.makeText(getApplicationContext(), "인터넷 연결을 확인해 주세요.", Toast.LENGTH_LONG).show();
        }
        Amplify.DataStore.observe(UserData.class,
                started -> Log.i("Amplify", "Observation began."),
                change -> Log.i("Amplify", change.item().toString()),
                failure -> Log.e("Amplify", "Observation failed.", failure),
                () -> Log.i("Amplify", "Observation complete.")
        );
    }
}
