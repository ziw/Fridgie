package ying.zi.fridgie.db;

import android.net.Uri;

import java.util.HashMap;
import java.util.Map;

public class FridgieContract {

    // The "Content authority" is a name for the entire content provider, similar to the
    // relationship between a domain name and its website.  A convenient string to use for the
    // content authority is the package name for the app, which is guaranteed to be unique on the
    // device.
//    public static final String CONTENT_AUTHORITY = "ying.zi.fridgie";

    // Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
    // the content provider.
//    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


    /**
     * Defines the fridgie_item table. Stores item definition
     */
    public static final class ItemContract {

        public static final String TABLE_NAME = "fridgie_item";
        public static final String COL_ITEM_NAME = "item_name";
        //public static final String COL_ITEM_TYPE = "item_type";
        public static final String COL_ITEM_PHOTO = "item_photo";
        public static final String COL_ITEM_EXP = "item_exp";

        private static final String TYPE_ITEM_NAME = " TEXT NOT NULL UNIQUE ";
        //private static final String TYPE_ITEM_TYPE = " TEXT NOT NULL ";
        private static final String TYPE_ITEM_PHOTO = " TEXT ";
        private static final String TYPE_ITEM_EXP = " INTEGER ";

        public static Map<String, String> getCreationMap(){
            Map<String,String> m = new HashMap<>();
            m.put(COL_ITEM_NAME, TYPE_ITEM_NAME);
            m.put(COL_ITEM_EXP, TYPE_ITEM_EXP);
            m.put(COL_ITEM_PHOTO, TYPE_ITEM_PHOTO);
            return m;
        }

    }

    public static class InventoryContract {
        public static final String TABLE_NAME = "fridgie_inventory";


    }




}
