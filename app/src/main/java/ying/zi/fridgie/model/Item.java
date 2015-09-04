package ying.zi.fridgie.model;

import android.content.ContentValues;
import android.database.Cursor;

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
        c.put(FridgieContract.ItemContract.COL_ITEM_NAME, getName());
        c.put(FridgieContract.ItemContract.COL_ITEM_PHOTO, getPhoto());
        c.put(FridgieContract.ItemContract.COL_ITEM_EXP_DAYS, getExpirationDays());
        c.put(FridgieContract.ItemContract.COL_ITEM_LAST_ADDED, getLastAdded() == null ? 0 : getLastAdded().getTime());
        c.put(FridgieContract.ItemContract.COL_ITEM_COUNT_ADDED, getCount());
        return c;
    }

    public Item (Cursor c){
        if(c == null){
            throw new IllegalArgumentException("Cannot create Item from null curose");
        }
        setName(c.getString(c.getColumnIndex(FridgieContract.ItemContract.COL_ITEM_NAME)));
        setId(c.getInt(c.getColumnIndex(FridgieContract.ItemContract._ID)));
        setPhoto(c.getString(c.getColumnIndex(FridgieContract.ItemContract.COL_ITEM_PHOTO)));
        setExpirationDays(c.getInt(c.getColumnIndex(FridgieContract.ItemContract.COL_ITEM_EXP_DAYS)));
        setLastAdded(new Date(c.getLong(c.getColumnIndex(FridgieContract.ItemContract.COL_ITEM_LAST_ADDED))));
        setCount(c.getInt(c.getColumnIndex(FridgieContract.ItemContract.COL_ITEM_COUNT_ADDED)));
    }

    public Item (){
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return name.equals(item.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
