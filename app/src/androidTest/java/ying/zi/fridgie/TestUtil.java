package ying.zi.fridgie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ying.zi.fridgie.model.Item;

/**
 * Created by ziw on 8/31/2015.
 */
public class TestUtil {

    public static Item createTestItem(){
        Item i = new Item();
        i.setName("Apple");
        i.setCount(5);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date d = sdf.parse("20/08/2012");
            i.setLastAdded(d);
        } catch (ParseException e) {}

        i.setExpirationDays(7);
        i.setPhoto("/test/apple.png");
        return i;
    }

}
