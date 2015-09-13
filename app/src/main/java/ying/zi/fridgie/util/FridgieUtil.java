package ying.zi.fridgie.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FridgieUtil {

    private static final String IMAGE_DIR = ".image";

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
        catch (Exception e){
        }
    }

    public static void saveBitmap(Context context, Bitmap bitmap, String fileName){
        File internalImageDir = context.getDir(IMAGE_DIR, Context.MODE_PRIVATE);
        File imageFile = new File(internalImageDir, fileName);
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        }
        catch (FileNotFoundException e){

        }
        finally {
            try{
                fos.close();
            }catch (Exception e){}
        }
    }

    public static Bitmap getBitmap(Context context, String fileName){
        File internalImageDir = context.getDir(IMAGE_DIR, Context.MODE_PRIVATE);
        File imageFile = new File(internalImageDir, fileName);
        Bitmap b= BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        return b;
    }

}
