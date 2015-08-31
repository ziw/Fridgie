package ying.zi.fridgie.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

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
}
