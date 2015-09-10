package ying.zi.fridgie.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import ying.zi.fridgie.R;
import ying.zi.fridgie.model.InventoryRecord;
import ying.zi.fridgie.util.FridgieUtil;

/**
 * An adapter that populates the list view containing Inventory Record
 */
public class InventoryAdapter extends ArrayAdapter<InventoryRecord> {

    private List<InventoryRecord> records;
    private Context context;
    private InventoryAdapterActivity activity;


    public InventoryAdapter(Context context, List<InventoryRecord> records, InventoryAdapterActivity activity){
        super(context,0, records);
        this.records = records;
        this.context = context;
        this.activity = activity;
    }


    public interface InventoryAdapterActivity{

        void deleteInventoryRecord(InventoryRecord record);
        void reduceInventoryRecord(InventoryRecord record);

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        InventoryRecord record = records.get(position);
        InventoryRecordViewHolder vh = null;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.inventory_list_item, parent, false);
            vh = new InventoryRecordViewHolder();
            vh.recordName = (TextView)convertView.findViewById(R.id.inv_list_item_text);
            vh.minusButton = (Button)convertView.findViewById(R.id.minus_button);
            vh.deleteButton = (Button)convertView.findViewById(R.id.delete_button);
            convertView.setTag(R.id.item_list, vh);
        }
        else{
            vh = (InventoryRecordViewHolder) convertView.getTag(R.id.item_list);
        }

        vh.recordName.setText(record.toString());
        vh.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.reduceInventoryRecord(getItem(position));
            }
        });

        vh.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.deleteInventoryRecord(getItem(position));
            }
        });

        return convertView;
    }

    private class InventoryRecordViewHolder{
        TextView recordName;
        Button minusButton;
        Button deleteButton;
    }


}
