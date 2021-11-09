package com.example.ace;

import android.util.Log;

public class PointItem {
    int num;
    String date;

    public PointItem(int num, String date) {
        this.num = num;
        this.date = date;
    }

    public int getNum() { return num; }
    public void setNum(int num) { this.num = num; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    // Method fo Print the Information
    @Override
    public String toString() {
        return "Num: " + Integer.toString(num)
                + "\nDate: " + date;
    }

}
