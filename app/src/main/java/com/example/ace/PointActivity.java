package com.example.ace;

import android.content.Intent;
import android.graphics.Point;
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

import java.util.ArrayList;
import java.util.List;

public class PointActivity extends AppCompatActivity {
    String ID;
    PointAdapter pointAdapter;
    ArrayList<PointItem> pointDataList;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);

        Intent myIntent = getIntent();
        ID = myIntent.getStringExtra("ID");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(ID + "님의 포인트 내역");

        this.InitializeData();
        ListView listView = (ListView) findViewById(R.id.listView_point);
        pointAdapter = new PointAdapter(this, pointDataList);
        listView.setAdapter(pointAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                String msg = pointAdapter.getItem(position).getDate() + " 기록";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });

    }

    public void InitializeData() {
        pointDataList = new ArrayList<PointItem>();

        Amplify.DataStore.query(
                UserData.class,
                items -> {
                    while (items.hasNext()) {
                        UserData item = items.next();
                        if(item.getUserId().toString().equals(ID)) {
                            List<PointArray> points = item.getPoint();
                            for(int j = 0; j < points.size(); j++) {
                                int p = 0;
                                if(!points.get(j).getPoint().equals("")) {
                                    p = Integer.parseInt(points.get(j).getPoint());
                                }

                                // FORMAT - YYYY-MM-DD TT:mm:SS.ssssss
                                // String[] arr = points.get(j).getDate().split(" ");
                                // String[] arr0 = arr[0].split("-");
                                // String[] arr1 = arr[1].split("-");
                                // String date = arr0[0] + "년 " + arr0[1] + "월 " + arr0[2] + "일 " + arr1[0] + "시 " + arr1[1] + "분 ";

                                pointDataList.add(new PointItem(p, points.get(j).getDate()));
                            }
                        }
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );

    }
}
