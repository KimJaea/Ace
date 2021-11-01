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
import com.amplifyframework.datastore.generated.model.ObjectArray;
import com.amplifyframework.datastore.generated.model.UserData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DBActivity extends AppCompatActivity {
    String UserID;
    ArrayList<DBItem> dbDataList;

    // ArrayList<DBItem> showList;
    // Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        Intent myIntent = getIntent();
        UserID = myIntent.getStringExtra("ID");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(UserID + "님의 기록 확인");

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
        final DBAdapter dbAdapter = new DBAdapter(this, dbDataList);
        listView.setAdapter(dbAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                String msg = dbAdapter.getItem(position).getTrash() + " 쓰레기";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });

        /*
        ArrayList arrayList = new ArrayList<>();
        arrayList.add("전체");
        arrayList.add("유리");
        arrayList.add("고철");
        arrayList.add("종이");
        arrayList.add("플라스틱");
        arrayList.add("비닐");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, arrayList);

        spinner = (Spinner) findViewById(R.id.sort_spinner);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //search(arrayList.get(position));
                Toast.makeText(getApplicationContext(), arrayList.get(position) + " 선택", Toast.LENGTH_LONG).show();
            }
        });
        */
    }

    /*
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
    */

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

                                    dbDataList.add(new DBItem("유리", object.getName(),false));

                                    if(!object.getRecycleElement().get(j).isEmpty()) {
                                        if(j == 0) {
                                            dbDataList.add(new DBItem("유리", object.getName(),false));
                                        } else if(j == 1) {
                                            dbDataList.add(new DBItem("고철", object.getName(),false));
                                        } else if(j == 2) {
                                            dbDataList.add(new DBItem("종이", object.getName(),false));
                                        } else if(j == 3) {
                                            dbDataList.add(new DBItem("플라스틱", object.getName(),false));
                                        } else if(j == 4) {
                                            dbDataList.add(new DBItem("비닐", object.getName(),false));
                                        } else {
                                            dbDataList.add(new DBItem("일반 쓰레기", object.getName(),false));
                                        }
                                        // FORMAT - 물품명_개수_YYYY/M/D/T/m
                                    } else {
                                        if(j == 0) {
                                            dbDataList.add(new DBItem("유리", object.getName(),true));
                                        } else if(j == 1) {
                                            dbDataList.add(new DBItem("고철", object.getName(),true));
                                        } else if(j == 2) {
                                            dbDataList.add(new DBItem("종이", object.getName(),true));
                                        } else if(j == 3) {
                                            dbDataList.add(new DBItem("플라스틱", object.getName(),true));
                                        } else if(j == 4) {
                                            dbDataList.add(new DBItem("비닐", object.getName(),true));
                                        } else {
                                            dbDataList.add(new DBItem("일반 쓰레기", object.getName(),true));
                                        }
                                    }
                                }
                            }
                        }
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );

        // dbDataList.add(new DBItem("테스트 종류", "테스트 물품",false));
        //showList = new ArrayList<DBItem>();
        //showList.addAll(dbDataList);

    }

}
