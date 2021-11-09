package com.example.ace;

public class DBItem {
    String trash;
    String stuff;
    String date;

    public DBItem(String trash, String stuff, String date) {
        this.trash = trash;
        this.stuff = stuff;
        this.date = date;
    }

    public String getTrash() {
        return trash;
    }
    public void setTrash(String trash) {
        this.trash = trash;
    }
    public String getStuff() {
        return stuff;
    }
    public void setStuff(String stuff) {
        this.stuff = stuff;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    // Method fo Print the Information
    @Override
    public String toString() {
        return "Trash: " + trash
                + "\nStuff: " + stuff
                + "\nDate: " + date;
    }

}
