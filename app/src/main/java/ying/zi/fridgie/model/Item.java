package ying.zi.fridgie.model;

import android.content.ContentValues;

import java.util.Date;

import ying.zi.fridgie.db.FridgieContract;

public class Item {

    private int id;
    private String name;
    private String photo;
    private int expirationDays;
    private Date lastAdded;
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getExpirationDays() {
        return expirationDays;
    }

    public void setExpirationDays(int expirationDays) {
        this.expirationDays = expirationDays;
    }

    public Date getLastAdded() {
        return lastAdded;
    }

    public void setLastAdded(Date lastAdded) {
        this.lastAdded = lastAdded;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ContentValues toContentValues(){
        ContentValues c = new ContentValues();
        c.put(FridgieContract.ItemContract.COL_ITEM_NAME, name);
        c.put(FridgieContract.ItemContract.COL_ITEM_PHOTO, photo);
        c.put(FridgieContract.ItemContract.COL_ITEM_EXP, expirationDays);
        c.put(FridgieContract.ItemContract.COL_ITEM_LAST_ADDED, lastAdded == null ? 0 : lastAdded.getTime());
        c.put(FridgieContract.ItemContract.COL_ITEM_COUNT_ADDED, count);
        return c;
    }
}
