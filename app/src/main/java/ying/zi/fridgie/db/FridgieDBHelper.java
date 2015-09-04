package ying.zi.fridgie.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


class FridgieDBHelper extends SQLiteOpenHelper {

    public FridgieDBHelper(Context context){
        super(context, FridgieContract.DB_NAME, null, FridgieContract.DB_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FridgieContract.ItemContract.createItemTable());
        db.execSQL(FridgieContract.InventoryContract.createInventoryTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //do nothing for now
        onCreate(db);
    }
}
