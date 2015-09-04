package ying.zi.fridgie;

import java.text.SimpleDateFormat;
import java.util.Date;

import ying.zi.fridgie.model.InventoryRecord;
import ying.zi.fridgie.model.Item;

/**
 * Created by ziw on 8/31/2015.
 */
public class TestUtil {

    public static Item createApple(){
        Item i = new Item();
        i.setName("Apple");
        i.setCount(5);

        i.setLastAdded(createDateObj("20/08/2015"));
        i.setExpirationDays(7);
        i.setPhoto("/test/apple.png");
        return i;
    }

    public static Item createMilk(){
        Item i = new Item();
        i.setName("Milk");
        i.setCount(5);

        i.setLastAdded(createDateObj("20/08/2015"));
        i.setExpirationDays(7);
        i.setPhoto("/test/milk.png");
        return i;
    }

    public static InventoryRecord createAppleRecord(){
        InventoryRecord r= new InventoryRecord();
        r.setItem(createApple());
        r.setCount(3);
        r.setExpDate(createDateObj("30/08/2015"));
        r.setStockDate(createDateObj("20/08/2015"));
        
        return r;
    }


    public static Date createDateObj(String ddMMyyyy){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try{
            return sdf.parse(ddMMyyyy);
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }


}
