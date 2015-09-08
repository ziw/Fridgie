package ying.zi.fridgie.model;


import android.content.ContentValues;
import android.database.Cursor;

import java.util.Date;

import ying.zi.fridgie.db.FridgieContract;

public class InventoryRecord {

    private int id;
    private String itemName;
    private Date stockDate;
    private Date expDate;
    private Status status;
    private Integer count;
    private String photo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Date getStockDate() {
        return stockDate;
    }

    public void setStockDate(Date stockDate) {
        this.stockDate = stockDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public ContentValues toContentValues(){
        ContentValues c = new ContentValues();
        c.put(FridgieContract.InventoryContract.COL_ITEM_NAME, getItemName());
        if(getExpDate() != null)
            c.put(FridgieContract.InventoryContract.COL_EXP_DATE, getExpDate().getTime());
        if(getStockDate() != null)
            c.put(FridgieContract.InventoryContract.COL_STOCK_DATE, getStockDate().getTime());
        c.put(FridgieContract.InventoryContract.COL_COUNT, getCount());
        c.put(FridgieContract.InventoryContract.COL_PHOTO,getPhoto());
        return c;
    }

    public InventoryRecord(){}

    public InventoryRecord(Cursor c){

        setItemName(c.getString(c.getColumnIndex(FridgieContract.InventoryContract.COL_ITEM_NAME)));
        setExpDate(new Date(c.getLong(c.getColumnIndex(FridgieContract.InventoryContract.COL_EXP_DATE))));
        setStockDate(new Date(c.getLong(c.getColumnIndex(FridgieContract.InventoryContract.COL_STOCK_DATE))));
        setPhoto(c.getString(c.getColumnIndex(FridgieContract.InventoryContract.COL_PHOTO)));
        setCount(c.getInt(c.getColumnIndex(FridgieContract.InventoryContract.COL_COUNT)));
        setId(c.getInt(c.getColumnIndex(FridgieContract.InventoryContract._ID)));

    }

    @Override
    public String toString() {
        return "{ " +
                "itemName= " + itemName +
                ", expDate= " + expDate +
                " } ";
    }

    public enum Status{
        GOOD,ALMOST,EXPIRED
    }

}
