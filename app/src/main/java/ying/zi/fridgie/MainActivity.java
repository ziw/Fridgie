package ying.zi.fridgie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import ying.zi.fridgie.util.FridgieUtil;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FridgieUtil.intentLong(getApplicationContext(), "Hello World");

        items = new ArrayAdapter<String>(getApplicationContext(),R.layout.list_item);
        items.add("Test object");
        ((ListView)findViewById(R.id.item_list)).setAdapter(items);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addItem(View view) {
        TextView t = (TextView)findViewById(R.id.item_name_input_text);
        items.add(t.getText().toString());

    }
}
