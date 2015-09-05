package ying.zi.fridgie.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ying.zi.fridgie.R;
import ying.zi.fridgie.model.InventoryRecord;

public class InventoryAdapter extends ArrayAdapter<InventoryRecord> {

    private List<InventoryRecord> records;

    public InventoryAdapter(Context context, List<InventoryRecord> records){
        super(context,0, records);
        this.records = records;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InventoryRecord record = records.get(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.inventory_list_item, parent, false);
        }
        ((TextView)convertView.findViewById(R.id.inv_list_item_text)).setText(record.toString());
        return convertView;
    }

}
