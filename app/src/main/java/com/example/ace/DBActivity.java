package com.example.ace;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.document.Table;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DBActivity extends AppCompatActivity {
    // DB
    private String TAG = "DynamoDb_Demo";
    DatabaseAccess databaseAccess;

    ArrayList<DBItem> dbDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        Intent myIntent = getIntent();
        String ID = myIntent.getStringExtra("ID");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(ID + "님의 기록 확인");

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
                // String msg = dbAdapter.getItem(position).getTrash() + " 쓰레기";
                // Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void InitializeData() {
        AssetManager assetManager = getAssets();
        try {
            InputStream is = assetManager.open("raw/user_record.json");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            StringBuffer buffer = new StringBuffer();
            String line = reader.readLine();
            while(line != null) {
                buffer.append(line + "\n");
                line = reader.readLine();
            }

            String jsonData = buffer.toString();
            Toast.makeText(getApplicationContext(), jsonData, Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
            //Toast.makeText(getApplicationContext(), "error occured " + e, Toast.LENGTH_LONG).show();
        }

        dbDataList = new ArrayList<DBItem>();
        dbDataList.add(new DBItem("유리", "e)제비표에이드애플그린340ml", true));
        dbDataList.add(new DBItem("비닐", "e)제비표에이드애플그린340ml", true));
        dbDataList.add(new DBItem("비닐", "e)제비표에이드애플그린340ml",false));
        dbDataList.add(new DBItem("비닐", "e)제비표에이드애플그린340ml",false));
        dbDataList.add(new DBItem("비닐", "아임이)바닐라향웨이퍼롤115g(S)",true));
    }

    // DB
    private class UpdateAsyncTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... telephoneNumbers) {
            boolean isSuccess = false;
            databaseAccess = DatabaseAccess.getInstance(DBActivity.this);
            try {
                isSuccess = databaseAccess.updateContact(telephoneNumbers[0]);
            } catch (Exception e) {
                Log.i(TAG, "error updating contact: ");
            }
            return isSuccess;
        }
        @Override
        protected void onPostExecute(Boolean isSuccess) {
            super.onPostExecute(isSuccess);
            Log.i(TAG, "in UpdateAsyncTask onPostExecute is success: " + isSuccess);
            if (isSuccess) {
                System.out.println("isSuccess는 true");
            } else {
                System.out.println("isSuccess는 false");
            }
        }
    }
}

