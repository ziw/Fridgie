package ying.zi.fridgie.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.test.AndroidTestCase;
import android.util.Log;

import ying.zi.fridgie.TestUtil;
import ying.zi.fridgie.model.Item;

/**
 * Test Database
 */
public class TestDB extends AndroidTestCase {

    private static final String LOG_TAG = TestDB.class.getSimpleName();

    @Override
    public void setUp(){
        deleteExistingDB();
    }

    private void deleteExistingDB(){
        getContext().deleteDatabase(FridgieContract.DB_NAME);
    }

    public void testCreateTable(){

        FridgieDBHelper helper = new FridgieDBHelper(getContext());
        //this will call onCreate() and create tables
        SQLiteDatabase db = helper.getWritableDatabase();
        assertTrue(db.isOpen());
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        assertTrue(c.moveToFirst());
        Log.d(LOG_TAG, c.getString(0));
        c.moveToNext();
        Log.d(LOG_TAG, c.getString(0));

        c.close();
        db.close();

    }

    public void testInsertItem() {
        FridgieDataSource data = new FridgieDataSource(getContext());
        Item apple = TestUtil.createTestItem();
        data.insertItem(apple);
        Cursor c = data.execRawQuery("SELECT * FROM " + FridgieContract.ItemContract.TABLE_NAME + " WHERE "
                + FridgieContract.ItemContract.COL_ITEM_NAME + " = ?" , new String[]{apple.getName()});
        assertTrue(c.moveToFirst());
        assertEquals(apple.getName(), c.getString(c.getColumnIndex(FridgieContract.ItemContract.COL_ITEM_NAME)));
        assertEquals(apple.getCount(), c.getInt(c.getColumnIndex(FridgieContract.ItemContract.COL_ITEM_COUNT_ADDED)));
        assertEquals(apple.getLastAdded().getTime(),c.getLong(c.getColumnIndex(FridgieContract.ItemContract.COL_ITEM_LAST_ADDED)));
        assertEquals(apple.getExpirationDays(), c.getInt(c.getColumnIndex(FridgieContract.ItemContract.COL_ITEM_EXP)));
    }
}
