package ying.zi.fridgie.db;

import android.net.Uri;
import android.provider.BaseColumns;

import java.util.HashMap;
import java.util.LinkedHashMap;
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

    public static final String DB_NAME = "Fridgie_DB";
    public static final int DB_VERSION = 1;

    /**
     * Defines the fridgie_item table. Stores item definition
     */
    public static final class ItemContract implements BaseColumns {

        public static final String TABLE_NAME = "fridgie_item";

        public static final String COL_ITEM_NAME = "item_name";
        //public static final String COL_ITEM_TYPE = "item_type";
        public static final String COL_ITEM_PHOTO = "item_photo";
        public static final String COL_ITEM_EXP = "item_exp";
        public static final String COL_ITEM_LAST_ADDED = "item_last_added";
        public static final String COL_ITEM_COUNT_ADDED = "item_count_added";

        private static final String TYPE_ID = " INTEGER PRIMARY KEY ";
        private static final String TYPE_ITEM_NAME = " TEXT NOT NULL UNIQUE ";
        //private static final String TYPE_ITEM_TYPE = " TEXT NOT NULL ";
        private static final String TYPE_ITEM_PHOTO = " TEXT ";
        private static final String TYPE_ITEM_EXP = " INTEGER ";
        private static final String TYPE_ITEM_LAST_ADDED = " REAL ";
        private static final String TYPE_ITEM_COUNT_ADDED = " INTEGER ";

        private static Map<String, String> getCreationMap(){
            Map<String,String> m = new LinkedHashMap<>();
            m.put(ItemContract._ID, TYPE_ID);
            m.put(COL_ITEM_NAME, TYPE_ITEM_NAME);
            m.put(COL_ITEM_EXP, TYPE_ITEM_EXP);
            m.put(COL_ITEM_PHOTO, TYPE_ITEM_PHOTO);
            m.put(COL_ITEM_LAST_ADDED, TYPE_ITEM_LAST_ADDED);
            m.put(COL_ITEM_COUNT_ADDED, TYPE_ITEM_COUNT_ADDED);
            return m;
        }

        public static String createItemTable(){
            return constructCreatSQL(getCreationMap(),TABLE_NAME);
        }

    }

    public static class InventoryContract implements BaseColumns {

        public static final String TABLE_NAME = "fridgie_inventory";

        public static final String COL_ITEM_EXP = "item_exp";
        public static final String COL_ITEM_NAME = "item_name";


        private static Map<String, String> getCreationMap(){
            Map<String,String> m = new HashMap<>();
            return m;
        }

        public static String createInventoryTable(){
            return constructCreatSQL(getCreationMap(),TABLE_NAME);
        }
    }

    private static String constructCreatSQL(Map<String,String> m, String tableName){
        StringBuilder sb = new StringBuilder("CREATE TABLE ");
        sb.append(tableName + " (");
        for(Map.Entry<String,String> e : m.entrySet()){
            sb.append(e.getKey() +  e.getValue() + "," );
        }
        sb.deleteCharAt(sb.length()-1);//remove last ','

        sb.append(" );");
        return sb.toString();
    }




}
