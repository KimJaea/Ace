package com.example.ace;

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

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class AccountActivity extends AppCompatActivity {
    TextView id, point;

    String addr, str;
    String response;
    Handler handler = new Handler(); // Main Thread Handler Object to Toast Message

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
                addr = "192.168.0.136"; // HOST IP
                // str = ID; // Data to Send;
                str = "내가 보내는 아이디는 " + ID + "입니다. 확인해주세요.";

                Toast.makeText(getApplicationContext(), addr + ", " + str, Toast.LENGTH_LONG).show();
                SocketThread thread = new SocketThread(addr, str);

                thread.run(); // 포인트 내역 확인하기
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

    class SocketThread extends Thread {
        String host; // Server IP
        String data; // Data to Send;

        public SocketThread(String host, String data) {
            Log.d("thread", host + "thread created!"); // log for check
            this.host = host;
            this.data = data;
        }

        @Override
        public void run() {
            try {
                Log.d("thread", "thread started!"); // log for check

                int port = 8080; // Port Number, Same with Server's
                Socket socket = new Socket(host, port);
                ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());
                outstream.writeObject(data); // Put Data
                outstream.flush(); // Send Data

                ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());
                response = (String)instream.readObject(); // Get Response

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(AccountActivity.this, "서버 응답: " + response, Toast.LENGTH_LONG).show();
                    }
                });

                socket.close();;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
