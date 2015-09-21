package ying.zi.fridgie.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import ying.zi.fridgie.R;
import ying.zi.fridgie.model.InventoryRecord;
import ying.zi.fridgie.util.FridgieUtil;
import ying.zi.fridgie.widget.SwipeListener;

/**
 * An adapter that populates the list view containing Inventory Record
 */
public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryRecordViewHolder>
                                implements SwipeListener.SwipableAdapter {

    private List<InventoryRecord> records;
    private Context context;
    private InventoryAdapterActivity activity;


    public InventoryAdapter(Context context, List<InventoryRecord> records, InventoryAdapterActivity activity){
        //super(context,0, records);
        this.records = records;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public InventoryRecordViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inventory_list_item, viewGroup, false);
        return new InventoryRecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final InventoryRecordViewHolder inventoryRecordViewHolder, final int position) {
        InventoryRecord record = records.get(position);
        inventoryRecordViewHolder.recordName.setText(record.getItemName() + "/" + record.getCount());

        inventoryRecordViewHolder.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = inventoryRecordViewHolder.getAdapterPosition();
                InventoryRecord record = records.get(position);
                activity.reduceInventoryRecord(record);
            }
        });

        inventoryRecordViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = inventoryRecordViewHolder.getAdapterPosition();
                InventoryRecord record = records.get(position);
                activity.deleteInventoryRecord(record);
            }
        });
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    @Override
    public void onItemSwipe(int position, int direction) {
        FridgieUtil.intentShort(context, position + "/" + records.size());
        if(direction == ItemTouchHelper.END){
            InventoryRecord record = records.get(position);
            activity.deleteInventoryRecord(record);
        }
        else if(direction == ItemTouchHelper.START){
            InventoryRecord record = records.get(position);
            activity.reduceInventoryRecord(record);
        }
    }


    public interface InventoryAdapterActivity{

        void deleteInventoryRecord(InventoryRecord record);
        void reduceInventoryRecord(InventoryRecord record);

    }

    public void remove(InventoryRecord record){
        int position = records.indexOf(record);
        records.remove(position);
        FridgieUtil.intentShort(context, position + "");
        notifyItemRemoved(position);
        //notifyItemRangeRemoved(position,1);
        //notifyDataSetChanged();
    }

    public void update(InventoryRecord oldRecord, InventoryRecord newRecord){
        int position = records.indexOf(oldRecord);
        if(position != -1){
            records.set(position, newRecord);
            notifyItemChanged(position);
        }
    }

//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        InventoryRecord record = records.get(position);
//        InventoryRecordViewHolder vh = null;
//        if(convertView == null){
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.inventory_list_item, parent, false);
//            vh = new InventoryRecordViewHolder();
//            vh.recordName = (TextView)convertView.findViewById(R.id.inv_list_item_text);
//            vh.minusButton = (Button)convertView.findViewById(R.id.minus_button);
//            vh.deleteButton = (Button)convertView.findViewById(R.id.delete_button);
//            convertView.setTag(R.id.item_list, vh);
//        }
//        else{
//            vh = (InventoryRecordViewHolder) convertView.getTag(R.id.item_list);
//        }
//
//        vh.recordName.setText(record.toString());
//        vh.minusButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //activity.reduceInventoryRecord(getItem(position));
//            }
//        });
//
//        vh.deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //activity.deleteInventoryRecord(getItem(position));
//            }
//        });
//
//        return convertView;
//    }

    public static class InventoryRecordViewHolder extends RecyclerView.ViewHolder{

        TextView recordName;
        Button minusButton;
        Button deleteButton;

        public InventoryRecordViewHolder(View view){
            super(view);
            recordName = (TextView)view.findViewById(R.id.inv_list_item_text);
            minusButton = (Button)view.findViewById(R.id.minus_button);
            deleteButton = (Button)view.findViewById(R.id.delete_button);
        }


    }



}
