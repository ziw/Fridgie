package ying.zi.fridgie.model;


import android.content.ContentValues;
import android.database.Cursor;

import java.util.Comparator;
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
        return "{ " + getCount() + " " +
                 itemName +
                ", expDate= " + expDate +
                " } ";
    }

    public enum Status{
        GOOD,ALMOST,EXPIRED
    }

    public static final Comparator<InventoryRecord> ALPHABETICAL_ORDER(){
        return new Comparator<InventoryRecord>() {
            @Override
            public int compare(InventoryRecord lhs, InventoryRecord rhs) {
                if (lhs == null && rhs == null){
                    return 0;
                }
                else if(lhs == null){
                    return -1;
                }
                else if(rhs == null){
                    return 1;
                }
                else {
                    String lName = lhs.getItemName();
                    String rName = rhs.getItemName();
                    if(lName == null && rName == null){
                        return 0;
                    }
                    else if(lName == null){
                        return  -1;
                    }
                    else if(rName == null){
                        return 1;
                    }
                    return lName.compareTo(rName);
                }

            }
        };
    }

    public static final Comparator<InventoryRecord> DAYS_LEFT_ORDER(){
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InventoryRecord record = (InventoryRecord) o;

        return id == record.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
