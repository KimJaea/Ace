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
import com.amplifyframework.datastore.generated.model.PointArray;
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
        //dbDataList.add(new DBItem("유리", "e)제비표에이드애플그린340ml", true));
        //dbDataList.add(new DBItem("비닐", "e)제비표에이드애플그린340ml", true));
        //dbDataList.add(new DBItem("비닐", "e)제비표에이드애플그린340ml",false));
        //dbDataList.add(new DBItem("비닐", "e)제비표에이드애플그린340ml",false));
        //dbDataList.add(new DBItem("비닐", "아임이)바닐라향웨이퍼롤115g(S)",true));

        Amplify.DataStore.query(UserData.class,
                items -> {
                    while (items.hasNext()) {
                        UserData userData = items.next();

                        // if(UserID.equals(userData.getId())) {
                        if( true ) {
                            if (userData.getListObject() != null) {
                                ObjectArray[] Stuffs = userData.getListObject().toArray(new ObjectArray[0]);
                                for (int i = 0; i < Stuffs.length; i++){
                                    //int recycled = Stuffs[i].getRecyclePlace().toArray().length;
                                    String[] element = Stuffs[i].getRecycleEmelent().toArray(new String[0]);

                                    Log.i("Amplify", "Stuff: " + Stuffs[i].getName());
                                    Log.i("Amplify", "elements: " + Stuffs[i].getRecycleEmelent().get(0));
                                    if(Stuffs[i].getRecycleEmelent().get(0) != null)
                                        dbDataList.add(new DBItem("유리", Stuffs[i].getName().toString(), false));
                                    if(Stuffs[i].getRecycleEmelent().get(1) != null)
                                        dbDataList.add(new DBItem("고철", Stuffs[i].getName().toString(), false));
                                    if(Stuffs[i].getRecycleEmelent().get(2) != null)
                                        dbDataList.add(new DBItem("종이", Stuffs[i].getName().toString(), false));
                                    if(Stuffs[i].getRecycleEmelent().get(3) != null)
                                        dbDataList.add(new DBItem("플라스틱", Stuffs[i].getName().toString(), false));
                                    if(Stuffs[i].getRecycleEmelent().get(4) != null)
                                        dbDataList.add(new DBItem("비닐", Stuffs[i].getName().toString(), false));
                                }
                            }
                        }
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );
    }

}

