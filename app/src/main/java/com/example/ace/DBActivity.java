package com.example.ace;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.RenderScript;
import android.util.EventLogTags;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.AmplifyModelProvider;
import com.amplifyframework.datastore.generated.model.Todo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

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

        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());
            Log.i("Amplify", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("Amplify", "Could not initialize Amplify", error);
        }

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

        Date date = new Date();
        int offsetMillis = TimeZone.getDefault().getOffset(date.getTime());
        int offsetSeconds = (int) TimeUnit.MILLISECONDS.toSeconds(offsetMillis);
        Temporal.DateTime temporalDateTime = new Temporal.DateTime(date, offsetSeconds);
        Todo item = Todo.builder()
                .name("Finish quarterly taxes")
                .description("This is the first item.")
                .build();
        Amplify.DataStore.save(item,
                success -> Log.i("Amplify", "Saved item: " + success.item().getName()),
                error -> Log.e("Amplify", "Could not save item to DataStore", error)
        );

        Amplify.DataStore.query(Todo.class,
                Where.matches(Todo.DESCRIPTION.eq("This is the first item.")),
                todos -> {
                    while (todos.hasNext()) {
                        Todo todo = todos.next();

                        Log.i("Amplify", "==== Todo ====");
                        Log.i("Amplify", "Name: " + todo.getName());

                        if (todo.getDescription() != null) {
                            Log.i("Amplify", "Description: " + todo.getDescription().toString());
                        }
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );

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

