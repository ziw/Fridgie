package ying.zi.fridgie.util;

import android.content.Context;
import android.widget.Toast;

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



}
