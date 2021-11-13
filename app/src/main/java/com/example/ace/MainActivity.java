package com.example.ace;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.ObjectArray;
import com.amplifyframework.datastore.generated.model.UserData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent myIntent = getIntent();
        String ID = myIntent.getStringExtra("ID");
        String PW = myIntent.getStringExtra("PW");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(ID + "님 환영합니다.");

        this.InitializeData(ID);

        Button buttonQR = findViewById(R.id.buttonQR);
        Button buttonDB = findViewById(R.id.buttonDB);
        Button buttonHow = findViewById(R.id.buttonAccount);

        buttonQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QRActivity.class);
                intent.putExtra("ID", ID);
                startActivity(intent);
            }
        });
        buttonDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DBActivity.class);
                intent.putExtra("ID", ID);
                startActivity(intent);
            }
        });
        buttonHow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
                intent.putExtra("ID", ID);
                startActivity(intent);
            }
        });
    }

    public void InitializeData(String ID) {
        ArrayList<DBItem> dbDataList = new ArrayList<DBItem>();

        Amplify.DataStore.query(
                UserData.class,
                items -> {
                    while (items.hasNext()) {
                        UserData item = items.next();
                        if(item.getUserId().toString().equals(ID)) {
                            Log.i("Amplify", "ID: " + item.getUserId().toString()); //
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

        ((VariableApplication)getApplication()).setDbDataList(dbDataList);

    }
}