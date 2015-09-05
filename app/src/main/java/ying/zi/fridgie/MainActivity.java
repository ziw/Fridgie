package ying.zi.fridgie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ying.zi.fridgie.controller.DataFetchTask;
import ying.zi.fridgie.controller.InventoryAdapter;
import ying.zi.fridgie.db.FridgieContract;
import ying.zi.fridgie.db.FridgieDataSource;
import ying.zi.fridgie.model.InventoryRecord;
import ying.zi.fridgie.util.FridgieUtil;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FridgieDataSource ds = FridgieDataSource.getInstance(getApplicationContext());

        DataFetchTask task = new DataFetchTask(this);
        //task.execute(DataFetchTask.Task.GET_ALL_RECORDS);

        InventoryAdapter adapter = new InventoryAdapter(getApplicationContext(), ds.getAllRecords());

        ((ListView) findViewById(R.id.item_list)).setAdapter(adapter);
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
        Intent it = new Intent(this, EditInventoryActivity.class);
        startActivity(it);
    }
}
