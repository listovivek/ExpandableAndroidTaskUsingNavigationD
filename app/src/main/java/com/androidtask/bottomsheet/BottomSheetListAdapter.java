package com.androidtask.bottomsheet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidtask.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by VIVEKS on 10/22/2016.
 */
public class BottomSheetListAdapter extends BaseAdapter {

    private List<String> bsItems;
    private LayoutInflater inflater;

    public BottomSheetListAdapter(Context context, List<String> items) {

        this.bsItems = items;
        this.inflater = LayoutInflater.from(context);


    }

    @Override
    public int getCount() {
        return bsItems.size();
    }

    @Override
    public Object getItem(int position) {
        return bsItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if( convertView == null ) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.bottom_sheet_adapter, parent, false);
            convertView.setTag(holder);

            holder.image = (ImageView) convertView.findViewById(R.id.imageView);
            holder.text = (TextView) convertView.findViewById(R.id.textView);

        }

        else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.image.setImageResource(R.mipmap.ic_launcher);
        holder.text.setText(bsItems.get(position));

        return convertView;
    }


    static class ViewHolder{
        ImageView image;
        TextView text;
    }
}
