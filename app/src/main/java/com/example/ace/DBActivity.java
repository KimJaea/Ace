package com.example.ace;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.ObjectArray;
import com.amplifyframework.datastore.generated.model.UserData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DBActivity extends AppCompatActivity {
    String UserID;
    ArrayList<DBItem> dbDataList;
    DBAdapter dbAdapter;
    ArrayList<DBItem> showList;
    ArrayList<String> arrayList;
    String[] items = {"전체", "유리", "고철", "종이", "플라스틱", "비닐"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        Intent myIntent = getIntent();
        UserID = myIntent.getStringExtra("ID");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(UserID + "님의 기록");

        /*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RecycleActivity.class);
                startActivity(intent);
            }
        });
        */

        this.InitializeData();
        ListView listView = (ListView) findViewById(R.id.listView_db);
        dbAdapter = new DBAdapter(this, showList);
        listView.setAdapter(dbAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                String msg = dbAdapter.getItem(position).getTrash() + " 쓰레기";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, items);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.sort_spinner);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                search(items[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void search(String charText) {
        showList.clear();
        if(charText.equals("전체")) {
            showList.addAll(dbDataList);
        } else {
            for(int i = 0; i < dbDataList.size(); i++) {
                if(dbDataList.get(i).getTrash().equals(charText)) {
                    showList.add(dbDataList.get(i));
                }
            }
        }
        dbAdapter.notifyDataSetChanged();
    }

    public void InitializeData() {
        dbDataList = new ArrayList<DBItem>();

        Amplify.DataStore.query(
                UserData.class,
                items -> {
                    while (items.hasNext()) {
                        UserData item = items.next();
                        if(item.getUserId().toString().equals(UserID)) {
                            Log.i("Amplify", "ID: " + item.getUserId().toString());
                            List<ObjectArray> objects = item.getListObject();
                            for(int i = 0; i < objects.size(); i++) {
                                ObjectArray object = objects.get(i);
                                for(int j = 0; j < object.getRecycleElement().size(); j++) {
                                    if(!object.getRecycleElement().get(j).isEmpty()) {

                                        // FORMAT - name_cnt_YYYY/M/D/T/m
                                        String[] arr = object.getName().split("_");
                                        String[] arr_date = arr[2].split("/");
                                        String date = arr_date[0] + "년 " + arr_date[1] + "월 " + arr_date[2] + "일";

                                        switch (j) {
                                            case 0:
                                                dbDataList.add(new DBItem("유리", arr[0], date));
                                                break;
                                            case 1:
                                                dbDataList.add(new DBItem("고철", arr[0], date));
                                                break;
                                            case 2:
                                                dbDataList.add(new DBItem("종이", arr[0], date));
                                                break;
                                            case 3:
                                                dbDataList.add(new DBItem("플라스틱", arr[0], date));
                                                break;
                                            case 4:
                                                dbDataList.add(new DBItem("비닐", arr[0], date));
                                                break;
                                            default:
                                                dbDataList.add(new DBItem("일반 쓰레기", arr[0], date));
                                        }
                                    } else {
                                    }
                                }
                            }
                        }
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );

        showList = new ArrayList<DBItem>();
        showList.addAll(dbDataList);

    }

}
