package ying.zi.fridgie.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import ying.zi.fridgie.model.Item;
import ying.zi.fridgie.model.Inventory;

/**
 * End point for database operations
 */
public class FridgieDataSource {

    private static final String LOG_TAG = FridgieDataSource.class.getSimpleName();

    private FridgieDBHelper helper;
    private SQLiteDatabase db;

    public FridgieDataSource(Context context){
        helper = new FridgieDBHelper(context);
    };

    public boolean insertItem(Item item){
        if(item == null){
            Log.e(LOG_TAG, "insertItem called with item null");
            throw new IllegalArgumentException("Cannot add null item.");
        }
        if(item.getName() == null){
            Log.e(LOG_TAG,"insertItem name null");
            throw new IllegalArgumentException("Item has name null.");
        }

        ContentValues c = item.toContentValues();
        try{
            db = helper.getWritableDatabase();
            db.insertOrThrow(FridgieContract.ItemContract.TABLE_NAME,null,c);
        }
        finally {
            if (db != null){
                db.close();
            }
        }
        return true;
    }


    public Item getItemByName(String name){
        return null;
    }

    Cursor execRawQuery(String sql, String[] args){
        db = helper.getWritableDatabase();
        return db.rawQuery(sql, args);
    }


}
