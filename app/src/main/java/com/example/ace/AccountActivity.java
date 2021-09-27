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

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
    TextView id, point;

    Button button1, button2, button3, button4, button5;
    TextView textView;

    SocketThread thread;
    String addr, str, response;
    Socket socket;
    ObjectOutputStream outstream;
    ObjectInputStream instream;

    private static InputStream is;
    private static OutputStream os;

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

        textView = (TextView)findViewById(R.id.textview);
        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addr = "192.168.0.136"; // HOST IP
                str = ID; // Data to Send

                Toast.makeText(getApplicationContext(), "send to " + addr + ", message is " + str, Toast.LENGTH_LONG).show();
                thread = new SocketThread(addr, str);
                new Thread() {
                    public void run() {
                        thread.run();
                    }
                }.start();
            }
        });
        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addr = "192.168.0.136"; // HOST IP
                str = ID; // Data to Send
                thread = new SocketThread(addr, str);
                new Thread() {
                    public void run() {
                        thread.run2();
                    }
                }.start();
            }
        });
        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addr = "192.168.0.136"; // HOST IP
                str = ID; // Data to Send
                thread = new SocketThread(addr, str);
                new Thread() {
                    public void run() {
                        thread.run3();
                    }
                }.start();
            }
        });
        button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addr = "192.168.0.136"; // HOST IP
                str = ID; // Data to Send
                thread = new SocketThread(addr, str);
                new Thread() {
                    public void run() {
                        thread.recieve2();
                    }
                }.start();
            }
        });
        button5 = (Button)findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addr = "192.168.0.136"; // HOST IP
                str = ID; // Data to Send
                thread = new SocketThread(addr, str);
                new Thread() {
                    public void run() {
                        thread.recieve3();
                    }
                }.start();
            }
        });

        Button check_point = findViewById(R.id.check_point);
        check_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "포인트 내역 확인하기 서비스는 준비중입니다.", Toast.LENGTH_LONG).show();
                // 포인트 내역 확인하기
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
            this.host = host;
            this.data = data;
        }

        @Override
        public void run() {
            try {
                int port = 8080; // Port Number, Same with Server's
                socket = new Socket(host, port);

                outstream = new ObjectOutputStream(socket.getOutputStream());
                outstream.writeObject(data); // Put Data
                outstream.flush(); // Send Data
                textView.setText(data + " 송신 완료");

                instream = new ObjectInputStream(socket.getInputStream());
                try {
                    response = (String)instream.readObject(); // Get Response
                    textView.setText("수신 완료");
                } catch (Exception e) {
                    textView.setText("수신 불가: " + e);
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(AccountActivity.this, "서버 응답: " + response, Toast.LENGTH_LONG).show();
                    }
                });

                textView.setText(response);
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void run2() {
            try {
                socket = new Socket();
                socket.connect(new InetSocketAddress(host, 8080));

                is = socket.getInputStream();
                os = socket.getOutputStream();

                byte[] byteArr = null;
                String msg = data;

                byteArr = msg.getBytes("UTF-8");
                os.write(byteArr);
                os.flush();

                textView.setText(msg + "송신 성공");

                byteArr = new byte[512];
                int readByteCount = is.read();

                if(readByteCount == -1)
                    throw new IOException();

                msg = new String(byteArr, 0, readByteCount, "UTF-8");
                textView.setText("수신 성공: <" + msg + ">"); // <>

                is.close();
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
        public void run3() {
            try {
                int port = 8080; // Port Number, Same with Server's
                socket = new Socket(host, port);

                outstream = new ObjectOutputStream(socket.getOutputStream());
                outstream.writeObject(data); // Put Data
                outstream.flush(); // Send Data
                textView.setText(data + " 송신 완료");

                try {
                    BufferedReader input = new BufferedReader((new InputStreamReader(socket.getInputStream())));
                    String read = input.readLine();
                    textView.setText("수신 완료: <" + read + ">"); // <Null>
                } catch (Exception e) {
                    e.printStackTrace();
                    textView.setText("수신 불가: " + e);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void recieve2() {
            try {
                socket = new Socket();
                socket.connect(new InetSocketAddress(host, 8080));

                is = socket.getInputStream();
                os = socket.getOutputStream();

                byte[] byteArr = null;
                String msg = data;

                byteArr = new byte[512];
                int readByteCount = is.read();

                if(readByteCount == -1)
                    throw new IOException();

                msg = new String(byteArr, 0, readByteCount, "UTF-8");
                textView.setText("수신 성공: <" + msg + ">"); // <>

                is.close();
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
        public void recieve3() {
            try {
                int port = 8080; // Port Number, Same with Server's
                socket = new Socket(host, port);

                try {
                    BufferedReader input = new BufferedReader((new InputStreamReader(socket.getInputStream())));
                    String read = input.readLine();
                    textView.setText("수신 완료: <" + read + ">"); // <Null>
                } catch (Exception e) {
                    e.printStackTrace();
                    textView.setText("수신 불가: " + e);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
