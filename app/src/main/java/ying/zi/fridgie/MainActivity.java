package ying.zi.fridgie;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ying.zi.fridgie.db.DataFetchTask;
import ying.zi.fridgie.controller.InventoryAdapter;
import ying.zi.fridgie.model.InventoryRecord;
import ying.zi.fridgie.util.FridgieUtil;

public class MainActivity extends AppCompatActivity
        implements DataFetchTask.DataFetchingUIActivity, InventoryAdapter.InventoryAdapterActivity{

    private InventoryAdapter adapter;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new InventoryAdapter(this, new ArrayList<InventoryRecord>(), this);
        ((ListView) findViewById(R.id.item_list)).setAdapter(adapter);

        DataFetchTask task = new DataFetchTask(this);
        task.execute( new DataFetchTask.Task( DataFetchTask.Task.TaskType.GET_ALL_RECORDS, null));

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

    @Override
    public void handleDataFetchTaskResult(DataFetchTask.Task task, Object result) {
        if(task == null){
            //log error
            return;
        }
        switch (task.getTaskType()){
            case GET_ALL_RECORDS: updateRecordList((List<InventoryRecord>) result); break;
            case DELETE_RECORD :  postDelete(task, result); break;
            case REDUCE_RECORD :  postReduce(task, result); break;
        }

    }

    @Override
    public void handleDataFetchTaskException(DataFetchTask.Task task, Throwable e) {

    }

    @Override
    public Context getUIContext() {
        return this;
    }

    @Override
    public void deleteInventoryRecord(InventoryRecord record) {
        DataFetchTask task = new DataFetchTask(this);
        task.execute(new DataFetchTask.Task(DataFetchTask.Task.TaskType.DELETE_RECORD, record));
    }

    @Override
    public void reduceInventoryRecord(InventoryRecord record) {
        DataFetchTask task = new DataFetchTask(this);
        task.execute(new DataFetchTask.Task(DataFetchTask.Task.TaskType.REDUCE_RECORD, record));

    }

    private void updateRecordList(List<InventoryRecord> records){
        adapter.clear();
        adapter.addAll(records);
    }

    private void postDelete(DataFetchTask.Task task, Object result){
        //FridgieUtil.intentShort(getApplicationContext(),"Post delete");
        InventoryRecord record = (InventoryRecord)task.getParam();//the record deleted
        adapter.remove(record);
    }

    private void postReduce(DataFetchTask.Task task, Object result){
        //FridgieUtil.intentShort(getApplicationContext(),"Post reduce");
        InventoryRecord r = (InventoryRecord)task.getParam();
        if(result == null){
            //record removed
            adapter.remove(r);
        }
        else{
            adapter.getItem( adapter.getPosition(r) ).setCount(r.getCount());
            adapter.notifyDataSetChanged();
        }
    }
}
