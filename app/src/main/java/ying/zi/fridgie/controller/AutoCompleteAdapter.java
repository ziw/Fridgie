package ying.zi.fridgie.controller;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import ying.zi.fridgie.R;
import ying.zi.fridgie.model.Item;

public class AutoCompleteAdapter extends ArrayAdapter<Item> {

    public AutoCompleteAdapter(Context context, List<Item> items){
        super(context, 0 , items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.auto_complete_item, parent, false);
        }
        TextView autoCompleteText = (TextView) convertView.findViewById(R.id.auto_complete_text_view);
        autoCompleteText.setText( ((Item)getItem(position)).toString() );

        return convertView;
    }
}
