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
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        Intent myIntent = getIntent();
        UserID = myIntent.getStringExtra("ID");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(UserID + "님의 기록");

        this.InitializeData();

        ListView listView = (ListView) findViewById(R.id.listView_db);
        dbAdapter = new DBAdapter(this, showList);
        listView.setAdapter(dbAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                // String msg = dbAdapter.getItem(position).getTrash() + " 쓰레기";
                // Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                deleteData(dbAdapter.getItem(position));
            }
        });

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, items);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner = (Spinner) findViewById(R.id.sort_spinner);
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
        dbDataList = ((VariableApplication)getApplication()).getDbDataList();

        showList = new ArrayList<DBItem>();
        showList.addAll(dbDataList);
    }

    public void deleteData(DBItem purposeItem) {
        String purposeName = purposeItem.getStuff();
        String purposeTrash = purposeItem.getTrash();

        int purposeNum = 0;
        for (int i = 0; i < items.length; i++) {
            if(items[i].equals(purposeTrash)) {
                purposeNum = i - 1; // 유리0, 고철1, 종이2, 플라스틱3, 비닐4
                Log.i("Amplify", "purposeNum is " + Integer.toString(purposeNum));
            }
        }

        int finalPurposeNum = purposeNum;
        Amplify.DataStore.query(
                UserData.class,
                items -> {
                    while (items.hasNext()) {
                        UserData item = items.next();
                        if(item.getUserId().toString().equals(UserID)) {
                            List<ObjectArray> objects = item.getListObject();
                            for(int i = 0; i < objects.size(); i++) {
                                ObjectArray object = objects.get(i);
                                if (object.getName().equals(purposeName)) {
                                    object.getRecycleElement().set(finalPurposeNum, "");
                                    Log.i("Amplify", "delete " + object.getName() + ", num: " + finalPurposeNum);
                                }

                                for(int j = 0; j < object.getRecycleElement().size(); j++) {
                                    object.getRecycleElement().set(j, "");
                                }
                            }
                        }
                    }
                },
                failure -> Log.e("Amplify", "Could not change DataStore", failure)
        );

        for(int i = 0; i < dbDataList.size(); i++) {
            if(dbDataList.get(i).getStuff().equals(purposeName)) {
                if(dbDataList.get(i).getTrash().equals(purposeTrash)) {
                    dbDataList.remove(i);
                    ((VariableApplication)getApplication()).setDbDataList(dbDataList);
                    search(items[spinner.getSelectedItemPosition()]);
                }
            }
        }

        dbAdapter.notifyDataSetChanged();
    }

}
