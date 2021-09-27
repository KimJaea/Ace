package com.example.ace;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.document.DeleteItemOperationConfig;
import com.amazonaws.mobileconnectors.dynamodbv2.document.PutItemOperationConfig;
import com.amazonaws.mobileconnectors.dynamodbv2.document.ScanOperationConfig;
import com.amazonaws.mobileconnectors.dynamodbv2.document.Search;
import com.amazonaws.mobileconnectors.dynamodbv2.document.Table;
import com.amazonaws.mobileconnectors.dynamodbv2.document.UpdateItemOperationConfig;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.DynamoDBEntry;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Primitive;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class DatabaseAccess {
    // DynamoDB
    private String TAG = "DynamoDB_Demo";

    private final String COGNITO_IDENTITY_POOL_ID = "ap-northeast-2:222837c5-ec43-4483-8bbd-bd131e002856";
    private final Regions COGNITO_IDENTITY_POOL_REGION = Regions.AP_NORTHEAST_2;
    private final String DYNAMODB_TABLE = "TestMyApp";
    private Context context;
    private CognitoCachingCredentialsProvider credentialsProvider;
    private AmazonDynamoDBClient dbClient;
    private Table dbTable;

    private static volatile DatabaseAccess instance;
    private DatabaseAccess(Context context) {
        this.context = context;

        // Create a nev credentials provider
        credentialsProvider = new CognitoCachingCredentialsProvider(context, COGNITO_IDENTITY_POOL_ID, COGNITO_IDENTITY_POOL_REGION);
        // Create a connection to the DynamoDB service
        dbClient = new AmazonDynamoDBClient(credentialsProvider);
        /* MUST SET db client REGION HERE ELSE DEFAULTS TO US_EAST_1 */
        dbClient.setRegion(Region.getRegion(Regions.AP_NORTHEAST_2));
        // Create a table reference
        dbTable = Table.loadTable(dbClient, DYNAMODB_TABLE);
    }
    public static synchronized DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    // 새 Document 생성
    public void create(Document memo) {
        if (!memo.containsKey("userId")) {
            memo.put("userId", credentialsProvider.getCachedIdentityId());
        }
        if (!memo.containsKey("noteId")) {
            memo.put("noteId", UUID.randomUUID().toString());
        }
        if (!memo.containsKey("creationDate")) {
            memo.put("creationDate", System.currentTimeMillis());
        }
        dbTable.putItem(memo);
    }

    public Document deleteContact(String string) {
        Document result = dbTable.deleteItem(new Primitive(string), new DeleteItemOperationConfig().withReturnValues(ReturnValue.ALL_OLD));
        return result;
    }

    public List<Document> getAllContacts() {
        ScanOperationConfig scanConfig = new ScanOperationConfig();
        List<String> attributeList = new ArrayList<>();
        attributeList.add("Telephone");
        attributeList.add("name");
        scanConfig.withAttributesToGet(attributeList);
        Search searchResult = dbTable.scan(scanConfig);
        return searchResult.getAllResults();
    }

    // 새 요소 집어넣기
    public Document addContact(Document dummyContact) {
        Log.i(TAG, "adding contact...");

        String newName = "Billy Bob";
        PutItemOperationConfig putItemOperationConfig = new PutItemOperationConfig();
        putItemOperationConfig.withReturnValues(ReturnValue.ALL_OLD);
        Document result = dbTable.putItem(dummyContact, putItemOperationConfig);
        return result;
    }

    // 기존 요소 세부 변경
    public boolean updateContact(String telephone) {
        Document retrieveDoc = dbTable.getItem(new Primitive(telephone));

        if (retrieveDoc != null) {
            String newName = "새로운 이름";
            if(retrieveDoc.get("name").asString().equals("새로운 이름")) {
                newName = "찐 새로운 이름";
            }

            retrieveDoc.put("name", newName);
            Set<String> mySet = new HashSet<>();
            DynamoDBEntry theSet = retrieveDoc.get("Set");
            List<String> stringSetList = theSet.convertToAttributeValue().getSS();
            mySet.addAll(stringSetList);

            mySet.add("set item 1011");
            mySet.add("set item 2022");
            retrieveDoc.put("Set", mySet);

            Document retrievedCarDoc = retrieveDoc.get("Car").asDocument();
            Document car = new Document();
            car.put("Car_color", retrievedCarDoc.get("Car_color").asString());
            car.put("Car_make", retrievedCarDoc.get("Car_make").asString());
            car.put("Car_wheels", "square");
            retrieveDoc.put("Car", car);

            Document updateResult = dbTable.updateItem(retrieveDoc, new Primitive(telephone)
            , new UpdateItemOperationConfig().withReturnValues(ReturnValue.UPDATED_NEW));

            try {
                Log.i(TAG, "updateResult: " + Document.toJson(updateResult));
            } catch (IOException e) {
                e.printStackTrace();
                Log.i(TAG, "updateResult json error: " + e.getLocalizedMessage());
            }
        }

        return true;
    }
}
