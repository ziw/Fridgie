package ying.zi.fridgie.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ying.zi.fridgie.model.InventoryRecord;
import ying.zi.fridgie.model.Item;
import ying.zi.fridgie.util.FridgieUtil;

/**
 * End point for database operations
 */
public class FridgieDataSource {

    private static final String LOG_TAG = FridgieDataSource.class.getSimpleName();

    private FridgieDBHelper helper;
    private SQLiteDatabase db;

    private static FridgieDataSource dsInstance;

    public static synchronized FridgieDataSource getInstance(Context context){
        if( dsInstance == null || dsInstance.helper == null ){
            dsInstance = new FridgieDataSource(context);
        }
        if( dsInstance.db == null || !dsInstance.db.isOpen()){
            dsInstance.db = dsInstance.helper.getWritableDatabase();
        }

        return dsInstance;
    }

    private FridgieDataSource(Context context){
        helper = new FridgieDBHelper(context);
        db = helper.getWritableDatabase();
    };

    public long insertItem(Item item){
        if(item == null){
            Log.e(LOG_TAG, "insertItem called with item null");
            throw new IllegalArgumentException("Cannot add null item.");
        }
        if(item.getName() == null){
            Log.e(LOG_TAG,"insertItem name null");
            throw new IllegalArgumentException("Item has name null.");
        }

        ContentValues c = item.toContentValues();
        long rowId = db.insertOrThrow(FridgieContract.ItemContract.TABLE_NAME, null, c);

        return rowId;
    }

    public long insertInventoryRecord(InventoryRecord record){
        if(record == null){
            Log.e(LOG_TAG, "insertInventoryRecord called with record null");
            throw new IllegalArgumentException("Cannot add null record.");
        }

        if( !containsItem(record.getItemName())){
            //insertItem(record.getItemName());
            Item item = new Item();
            item.setName(record.getItemName());
            insertItem(item);
        }

        long rowId = db.insertOrThrow(FridgieContract.InventoryContract.TABLE_NAME,null,record.toContentValues());
        return rowId;
    }

    public boolean containsItem(String name){
        if(name == null){
            return false;
        }
        Cursor c= db.query(FridgieContract.ItemContract.TABLE_NAME,
                new String[]{FridgieContract.ItemContract.COL_ITEM_NAME},
                FridgieContract.ItemContract.COL_ITEM_NAME + " = ?",
                new String[]{name}, null, null, null);
        return c.getCount() != 0;
    }

    public int updateItem(Item item){

        int numRows = -1;
        return numRows;
    }

    public int updateInventoryRecord(InventoryRecord record){
        int numRows = db.update(FridgieContract.InventoryContract.TABLE_NAME,
                                record.toContentValues(),
                                FridgieContract.InventoryContract._ID + " = ?",
                                new String[]{Integer.toString(record.getId())});


        return numRows;
    }

    public int deleteInventoryRecord(InventoryRecord record){
        return db.delete(FridgieContract.InventoryContract.TABLE_NAME,
                  FridgieContract.InventoryContract._ID + " = ?",
                  new String[]{Integer.toString(record.getId())});

    }

    public Item getItemByName(String name){

        return null;
    }

    public List<InventoryRecord> getAllRecords(){
        List<InventoryRecord> l = new ArrayList<>();
        Cursor c = db.query(FridgieContract.InventoryContract.TABLE_NAME, null, null,null,null,null,null,null);
        while(c.moveToNext()){
            l.add(new InventoryRecord(c));
        }
        return l;
    }


    public void close(){
        FridgieUtil.closeDB(db);
        helper.close();
    }

    Cursor execRawQuery(String sql, String[] args){
        db = helper.getWritableDatabase();
        return db.rawQuery(sql, args);
    }

    public void deleteAll(){

    }


}
