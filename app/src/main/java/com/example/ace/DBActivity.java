package com.example.ace;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.UserData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DBActivity extends AppCompatActivity {
    String UserID;
    ArrayList<DBItem> dbDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        Intent myIntent = getIntent();
        UserID = myIntent.getStringExtra("ID");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(UserID + "님의 기록 확인");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RecycleActivity.class);
                startActivity(intent);
            }
        });

        this.InitializeData();
        ListView listView = (ListView) findViewById(R.id.listView_db);
        final DBAdapter dbAdapter = new DBAdapter(this, dbDataList);
        listView.setAdapter(dbAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                String msg = dbAdapter.getItem(position).getTrash() + " 쓰레기";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void InitializeData() {
        dbDataList = new ArrayList<DBItem>();
        dbDataList.add(new DBItem("유리", "e)제비표에이드애플그린340ml", true));
        //dbDataList.add(new DBItem("비닐", "e)제비표에이드애플그린340ml", true));
        //dbDataList.add(new DBItem("비닐", "e)제비표에이드애플그린340ml",false));
        //dbDataList.add(new DBItem("비닐", "e)제비표에이드애플그린340ml",false));
        //dbDataList.add(new DBItem("비닐", "아임이)바닐라향웨이퍼롤115g(S)",true));

        // 앱에서 할 일:
        /// 데이터 확인 및 출력
        /// 변경 사항 수정

        Amplify.DataStore.query(UserData.class,
                items -> {
                    while (items.hasNext()) {
                        UserData userData = items.next();

                        Log.i("Amplify", "==== UserData ====");
                        Log.i("Amplify", "Name: " + userData.getId());
                        if (userData.getPoint() != null) {
                            Log.i("Amplify", "Point: " + userData.getPoint().toString());
                        }
                        if (userData.getPw() != null) {
                            Log.i("Amplify", "Description: " + userData.getPw());
                        }
                    }
                },
                failure -> Log.e("Tutorial", "Could not query DataStore", failure)
        );
    }

}

