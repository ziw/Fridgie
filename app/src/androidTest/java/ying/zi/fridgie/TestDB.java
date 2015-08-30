package ying.zi.fridgie;

import android.test.AndroidTestCase;
import android.util.Log;

import ying.zi.fridgie.db.FridgieContract;

/**
 * Created by ziw on 8/30/2015.
 */
public class TestDB extends AndroidTestCase {

    private static final String LOG_TAG = TestDB.class.getSimpleName();

    public void testCreateItemTable(){
        String createSQL = FridgieContract.ItemContract.createItemTable();
        Log.d(LOG_TAG, createSQL);
        assertTrue(true);
    }
}
