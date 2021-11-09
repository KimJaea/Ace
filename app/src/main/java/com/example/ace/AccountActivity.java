package com.example.ace;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.PointArray;
import com.amplifyframework.datastore.generated.model.UserData;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

public class AccountActivity extends AppCompatActivity {
    String ID;
    TextView id, point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Intent myIntent = getIntent();
        ID = myIntent.getStringExtra("ID");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(ID + "님의 계정");

        id = (TextView)findViewById(R.id.id);
        point = (TextView)findViewById(R.id.point);
        id.setText(ID);
        point.setText("0");

        Amplify.DataStore.query(
                UserData.class,
                items -> {
                    while (items.hasNext()) {
                        UserData item = items.next();
                        if(item.getUserId().toString().equals(ID)) {
                            List<PointArray> points = item.getPoint();
                            PointArray point_ = points.get(points.size() - 1);

                            point.setText(point_.getPoint());
                        }
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );

        Button check_point = findViewById(R.id.check_point);
        check_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PointActivity.class);
                intent.putExtra("ID", ID);
                startActivity(intent);
            }
        });
        Button edit_info = findViewById(R.id.edit_info);
        edit_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "개인정보 수정하기 서비스는 준비중입니다.", Toast.LENGTH_LONG).show();
            }
        });
        Button request = findViewById(R.id.request);
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "문의하기 서비스는 준비중입니다.", Toast.LENGTH_LONG).show();
            }
        });
        Button sign_out = findViewById(R.id.sign_out);
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "탈퇴하기 서비스는 준비중입니다.", Toast.LENGTH_LONG).show();
            }
        });

    }

}
