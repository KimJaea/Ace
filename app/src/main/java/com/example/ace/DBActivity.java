package com.example.ace;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

public class DBActivity extends AppCompatActivity {

    ArrayList<DBItem> dbDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        Intent myIntent = getIntent();
        String ID = myIntent.getStringExtra("ID");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(ID + "님의 기록 확인");

        //재활용 안된 애들만 모아보기
        // 구매

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

        dbDataList.add(new DBItem("종이", "e)이천쌀콘150ml", false));
        dbDataList.add(new DBItem("플라스틱", "e)소프트워터청포도500ml(new)",false));
        dbDataList.add(new DBItem("비닐", "e)소프트워터청포도500ml(new)",true));
    }
}

/*

class CSVHelper(private val filePath: String) {
    fun writeAllData(fileName: String, dataList: ArrayList<Array<String>>) {
        try {
            FileWriter(File("$filePath/$fileName")).use } { fw ->
                //writeAll()을 이용한 리스트 데이터 등록
                CSVWriter(fw).use {
                    it.writeAll(dataList);
                }
            }
        } catch (e: IOException) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
        }
    }
}


 */
