package ying.zi.fridgie.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ziw on 8/23/2015.
 */
public class FridgieUtil {

    public static void intentShort(Context context, String msg){
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void intentLong(Context context, String msg){
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        toast.show();
    }

    public static void closeDB(SQLiteDatabase db){
        try{
            db.close();
        }
        catch (Exception e){}
    }

}
