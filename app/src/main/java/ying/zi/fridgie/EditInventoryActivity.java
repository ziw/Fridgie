package ying.zi.fridgie;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Date;
import java.util.Random;

import ying.zi.fridgie.db.DataFetchTask;
import ying.zi.fridgie.model.InventoryRecord;

public class EditInventoryActivity extends AppCompatActivity
                                   implements DataFetchTask.DataFetchingUIActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_inventory);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_inventory, menu);
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
        String itemName = ((TextView)findViewById(R.id.edit_inv_item_name)).getText().toString();
        InventoryRecord record = new InventoryRecord();
        record.setItemName(itemName);
        record.setStockDate(new Date());
        record.setExpDate((new Date()));
        record.setCount(  (new Random()).nextInt(6) );

        DataFetchTask task = new DataFetchTask(this);
        task.execute(new DataFetchTask.Task(DataFetchTask.Task.TaskType.INSERT_RECORD, record));

    }

    @Override
    public Context getUIContext() {
        return this;
    }

    @Override
    public void handleDataFetchTaskResult(DataFetchTask.Task task, Object result) {
        switch (task.getTaskType()){
            case INSERT_RECORD : postInsert(); break;
        }
    }

    @Override
    public void handleDataFetchTaskException(DataFetchTask.Task task, Throwable e) {

    }

    private void postInsert(){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
}
