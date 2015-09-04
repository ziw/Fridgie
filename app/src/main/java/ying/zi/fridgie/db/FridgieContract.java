package ying.zi.fridgie.db;

import android.provider.BaseColumns;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FridgieContract {

    public static final String DB_NAME = "Fridgie_DB";
    public static final int DB_VERSION = 1;

    /**
     * Defines the fridgie_item table. Stores item definition
     */
    public static final class ItemContract implements BaseColumns {

        public static final String TABLE_NAME = "fridgie_items";

        public static final String COL_ITEM_NAME = "item_name";
        //public static final String COL_ITEM_TYPE = "item_type";
        public static final String COL_ITEM_PHOTO = "photo";
        public static final String COL_ITEM_EXP_DAYS = "exp_days";
        public static final String COL_ITEM_LAST_ADDED = "last_added";
        public static final String COL_ITEM_COUNT_ADDED = "count";

        private static final String TYPE_ID = " INTEGER PRIMARY KEY AUTOINCREMENT ";
        private static final String TYPE_ITEM_NAME = " TEXT NOT NULL UNIQUE ";
        //private static final String TYPE_ITEM_TYPE = " TEXT NOT NULL ";
        private static final String TYPE_ITEM_PHOTO = " TEXT ";
        private static final String TYPE_ITEM_EXP_DAYS = " INTEGER ";
        private static final String TYPE_ITEM_LAST_ADDED = " REAL ";
        private static final String TYPE_ITEM_COUNT_ADDED = " INTEGER ";

        private static Map<String, String> getCreationMap(){
            Map<String,String> m = new LinkedHashMap<>();
            m.put(ItemContract._ID, TYPE_ID);
            m.put(COL_ITEM_NAME, TYPE_ITEM_NAME);
            m.put(COL_ITEM_EXP_DAYS, TYPE_ITEM_EXP_DAYS);
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

        public static final String COL_ITEM_NAME = "item_name";
        //public static final String COL_EXP_DAYS = "exp_days";
        public static final String COL_EXP_DATE ="exp_date";
        public static final String COL_STOCK_DATE = "stock_date";
        public static final String COL_COUNT = "count";
        public static final String COL_PHOTO = "photo";

        private static final String TYPE_ID = " INTEGER PRIMARY KEY AUTOINCREMENT ";
        private static final String TYPE_ITEM_NAME = " TEXT NOT NULL ";
        //private static final String TYPE_EXP_DAYS = "";
        private static final String TYPE_EXP_DATE = " REAL NOT NULL ";
        private static final String TYPE_STOCK_DATE = " REAL NOT NULL ";
        private static final String TYPE_COUNT = " INTEGER ";
        private static final String TYPE_PHOTO = " TEXT " ;

        private static final String FOREIGN_KEY_COL = " FOREIGN KEY(" + COL_ITEM_NAME + ") ";
        private static final String FOREIGN_KEY_REF = " REFERENCES " + ItemContract.TABLE_NAME + "("
                                                        + ItemContract.COL_ITEM_NAME + ")";

        private static Map<String, String> getCreationMap(){
            Map<String,String> m = new LinkedHashMap<>();
            m.put(_ID, TYPE_ID);
            m.put(COL_ITEM_NAME, TYPE_ITEM_NAME);
            m.put(COL_EXP_DATE, TYPE_EXP_DATE);
            m.put(COL_STOCK_DATE, TYPE_STOCK_DATE);
            m.put(COL_COUNT, TYPE_COUNT);
            m.put(COL_PHOTO, TYPE_PHOTO);
            m.put(FOREIGN_KEY_COL, FOREIGN_KEY_REF);
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
