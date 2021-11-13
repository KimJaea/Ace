package com.example.ace;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class VariableApplication extends Application {
    private ArrayList<DBItem> dbDataList = new ArrayList<DBItem>();

    public ArrayList<DBItem> getDbDataList() {
        return dbDataList;
    }
    public void setDbDataList(ArrayList<DBItem> dbDataList) {
        this.dbDataList = dbDataList;
    }
    public void printDbDataList() {
        System.out.println(dbDataList);
    }
}
