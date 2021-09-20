package com.example.ace;

public class DBItem {
    String trash;
    String stuff;
    boolean recycle;

    public DBItem(String trash, String stuff, boolean recycle) {
        this.trash = trash;
        this.stuff = stuff;
        this.recycle = recycle;
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
    public boolean getRecycle() {
        return recycle;
    }
    public void getRecycle(boolean recycle) {
        this.recycle = recycle;
    }

    // Method fo Print the Information
    @Override
    public String toString() {
        return "Trash: " + trash
                + "\nStuff: " + stuff
                + "\nRecycle: " + recycle;
    }

}
