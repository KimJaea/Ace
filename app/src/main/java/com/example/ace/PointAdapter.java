package com.example.ace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class PointAdapter extends BaseAdapter {

    Context pointContext = null;
    LayoutInflater pointLayoutInflater = null;
    ArrayList<PointItem> pointItems;

    public PointAdapter(Context context, ArrayList<PointItem> data) {
        pointContext = context;
        pointItems = data;
        pointLayoutInflater = LayoutInflater.from(pointContext);
    }

    @Override
    public int getCount() {
        return pointItems.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public PointItem getItem(int position) {
        return pointItems.get(position);
    }

    @Override
    public View getView(int position, View conView, ViewGroup parent) {
        View view = pointLayoutInflater.inflate(R.layout.listview_point, null);

        TextView pointNum = (TextView)view.findViewById(R.id.point_num);
        TextView pointDate = (TextView)view.findViewById(R.id.point_date);

        pointNum.setText(Integer.toString(pointItems.get(position).getNum()) + " P");
        pointDate.setText("적립 일시: " + pointItems.get(position).getDate());

        return view;
    }
}
