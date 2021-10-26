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
import com.amplifyframework.datastore.generated.model.ObjectArray;
import com.amplifyframework.datastore.generated.model.PointArray;
import com.amplifyframework.datastore.generated.model.UserData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class AccountActivity extends AppCompatActivity {
    String ID;

    TextView id, point;

    SocketThread thread;
    String addr, str;
    Socket socket;

    private static InputStream is;
    private static OutputStream os;

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

        Button check_point = findViewById(R.id.check_point);
        check_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addr = "192.168.0.136"; // HOST IP
                str = ID; // Data to Send
                thread = new SocketThread(addr, str);
                new Thread() {
                    public void run() {
                        thread.run();
                    }
                }.start();
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

        Amplify.DataStore.query(UserData.class,
                items -> {
                    while (items.hasNext()) {
                        UserData userData = items.next();

                        if(ID.equals(userData.getId())) {
                            if (userData.getPoint() != null) {
                                point.setText(userData.getPoint().get(0).toString());
                            }
                        }
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );
    }

    class SocketThread extends Thread {
        String host; // Server IP
        String data; // Data to Send;

        public SocketThread(String host, String data) {
            this.host = host;
            this.data = data;
        }
        @Override
        public void run() {
            try {
                socket = new Socket();
                socket.connect(new InetSocketAddress(host, 8080));

                byte[] byteArr = data.getBytes("UTF-8");
                os = socket.getOutputStream();
                os.write(byteArr);
                os.flush();

                try {
                    BufferedReader input = new BufferedReader((new InputStreamReader(socket.getInputStream())));
                    String read = input.readLine();
                    point.setText(read);
                } catch (Exception e) {
                    e.printStackTrace();
                    point.setText("수신 불가: " + e);
                }

                os.close();
            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                // Wrong IP Address
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                // Cannot connect to Server of Port
                e.printStackTrace();
                try { socket.close(); } catch (IOException e1) { e1.printStackTrace(); }
            }

            if(!socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        public String byteArrayToHex(byte[] a) {
            StringBuilder sb = new StringBuilder();
            for(final byte b: a)
                sb.append(String.format("%02x", b&0xff));
            return sb.toString();
        }
    }
}
