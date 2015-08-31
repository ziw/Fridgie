package ying.zi.fridgie.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
        db = helper.getWritableDatabase();
    };

    public void close(){

        if(helper != null){
            helper.close();
        }

    }

    public boolean insertItem(Item item){

        return true;
    }

    public Item getItemByName(String name){

        return null;
    }



}
