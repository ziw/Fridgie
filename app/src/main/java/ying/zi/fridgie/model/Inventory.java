package ying.zi.fridgie.model;


import android.content.ContentValues;
import android.database.Cursor;

import java.util.Date;

import ying.zi.fridgie.db.FridgieContract;

public class Inventory {

    private int id;
    private Item item;
    private Date stockDate;
    private Date expDate;
    private Status status;
    private Integer quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ContentValues toContentValues(){
        ContentValues c = new ContentValues();
        c.put(FridgieContract.InventoryContract.COL_ITEM_NAME, getItem().getName());


        return c;
    }

    public Inventory(){}

    public Inventory(Cursor c){

    }

    public enum Status{
        GOOD,ALMOST,EXPIRED
    }

}
