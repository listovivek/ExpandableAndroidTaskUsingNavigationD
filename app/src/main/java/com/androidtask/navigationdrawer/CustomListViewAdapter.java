package com.androidtask.navigationdrawer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidtask.R;
import com.androidtask.productcategory.gsonvalues.GetMainCategory;

/**
 * Created by viveks on 10/20/2016.
 */
public class CustomListViewAdapter extends BaseAdapter{

    private MainActivity context;
    private GetMainCategory category;
    private LayoutInflater inflater;

    public CustomListViewAdapter(MainActivity mainActivity, GetMainCategory userD) {
        this.context = mainActivity;
        this.category = userD;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return category.mainCatgArrayLists.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
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

            convertView = inflater.inflate(R.layout.listview_adapter, null, true);
            convertView.setTag(holder);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.txt_name);


        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtTitle.setText(category.mainCatgArrayLists.get(position).mainProductName);

        return convertView;
    }

    static class ViewHolder{
        TextView txtTitle;
    }
}
