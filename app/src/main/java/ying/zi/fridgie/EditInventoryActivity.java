package ying.zi.fridgie;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import ying.zi.fridgie.controller.AutoCompleteAdapter;
import ying.zi.fridgie.db.DataFetchTask;
import ying.zi.fridgie.model.InventoryRecord;
import ying.zi.fridgie.model.Item;

public class EditInventoryActivity extends AppCompatActivity
                                   implements DataFetchTask.DataFetchingUIActivity{

    private Toolbar toolbar;

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final String LOG_TAG = EditInventoryActivity.class.getSimpleName();

    private Item item = null;

    private String itemName = null;
    private Date stockDate = null;
    private Date expDate = null;
    private Bitmap photo = null;
    private int count = 1;


    private AutoCompleteTextView itemNameText;
    private EditText quantityText;
    private ImageButton minusButton;
    private ImageButton plusButton;
    private ImageButton naButton;
    private ImageView cameraButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_inventory);
        quantityText = (EditText) findViewById(R.id.quantityInput);
        minusButton = (ImageButton) findViewById(R.id.quantityMinusBtn);
        plusButton = (ImageButton) findViewById(R.id.quantityAddBtn);
        naButton = (ImageButton)findViewById(R.id.quantityNaBtn);
        cameraButton = (ImageView)findViewById(R.id.cameraView);
        itemNameText = (AutoCompleteTextView)findViewById(R.id.edit_inv_item_name);

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        /*Test auto complete values. TODO remove this*/
        Item i1 = new Item();
        i1.setName("abc");
        Item i2 = new Item();
        i2.setName("abcde");
        Item i3 = new Item();
        i3.setName("bcde");

        itemNameText.setAdapter(new AutoCompleteAdapter(this, Arrays.asList(new Item[]{i1,i2,i3})));
        /*Test auto complete values. TODO remove this*/


        quantityText.setText(Integer.toString(1));

        quantityText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s == null || s.toString().length() == 0) {
                    count = 1;
                    refreshUIForm();
                } else {
                    count = Integer.parseInt(s.toString());
                }

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            photo = imageBitmap;
            refreshUIForm();
        }
    }

    public void saveInventoryRecord(MenuItem item) {
        String itemName = itemNameText.getText().toString();
        InventoryRecord record = new InventoryRecord();
        record.setItemName(itemName);
        record.setStockDate(new Date());
        record.setExpDate((new Date()));
        record.setCount(quantityText.isEnabled() ? count : 0);

        DataFetchTask task = new DataFetchTask(this);
        task.execute(new DataFetchTask.Task(DataFetchTask.Task.TaskType.INSERT_RECORD, record));
    }

    public void onMinusButtonClick(View view) {
        quantityText.setEnabled(true);
        if(count > 1){
            count--;
        }
        refreshUIForm();
    }

    public void onAddButtonClick(View view) {
        quantityText.setEnabled(true);
        if(count >= 1){
            count++;
        }
        refreshUIForm();
    }

    public void onNAButtonClick(View view) {
        quantityText.setEnabled(!quantityText.isEnabled());
        refreshUIForm();
    }

    public void onCameraButtonClick(View view) {
        launchCameraIntent();
    }

    private void launchCameraIntent(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void refreshUIForm(){
        quantityText.setText(Integer.toString(count));
        minusButton.setImageResource(quantityText.isEnabled() && count > 1 ? R.drawable.ic_remove_dark : R.drawable.ic_remove_gray);
        plusButton.setImageResource(quantityText.isEnabled() ? R.drawable.ic_add_dark : R.drawable.ic_add_gray);
        naButton.setImageResource(quantityText.isEnabled() ? R.drawable.na_gray : R.drawable.na_dark);
        if(photo != null) {
            cameraButton.setImageBitmap(photo);
            cameraButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }

}
