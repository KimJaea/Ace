package com.example.ace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class DBAdapter extends BaseAdapter {

    Context dbContext = null;
    LayoutInflater dbLayoutInflater = null;
    ArrayList<DBItem> dbItems;

    public DBAdapter(Context context, ArrayList<DBItem> data) {
        dbContext = context;
        dbItems = data;
        dbLayoutInflater = LayoutInflater.from(dbContext);
    }

    @Override
    public int getCount() {
        return dbItems.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public DBItem getItem(int position) {
        return dbItems.get(position);
    }

    @Override
    public View getView(int position, View conView, ViewGroup parent) {
        View view = dbLayoutInflater.inflate(R.layout.listview_db, null);

        TextView trashName = (TextView)view.findViewById(R.id.trash);
        TextView stuffName = (TextView)view.findViewById(R.id.stuff);
        TextView recycleName = (TextView)view.findViewById(R.id.recycle);

        trashName.setText(dbItems.get(position).getTrash());
        stuffName.setText(dbItems.get(position).getStuff());
        boolean recycleValue = dbItems.get(position).getRecycle();
        if(recycleValue) {
            recycleName.setText("수거 완료");
            recycleName.setTextColor(ContextCompat.getColor(dbContext, R.color.green_700));
        } else {
            recycleName.setText("수거 필요");
            recycleName.setTextColor(ContextCompat.getColor(dbContext, R.color.red_700));
        }

        return view;
    }
}
