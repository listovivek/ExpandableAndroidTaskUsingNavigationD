package com.androidtask.navigationdrawer;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.androidtask.R;

import java.util.HashMap;
import java.util.List;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private MainActivity mContext;
    private final List<String> listParent;
    private final HashMap<String, List<String>> listchild;
    private LayoutInflater mLayoutInflater;

    public CustomExpandableListAdapter(MainActivity context, List<String> paramList,
                                       HashMap<String, List<String>> paramHashMap) {
        this.mContext = context;
        this.listParent = paramList;
        this.listchild = paramHashMap;

        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {

            return listchild.get(this.listParent.get(listPosition)).get(expandedListPosition);

    }

    @Override
    public int getChildrenCount(int listPosition) {
        return listchild.get(this.listParent.get(listPosition)).size();
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.expandable_listitem, null);
        }
        TextView expandedListTextView = (TextView) convertView
            .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);

        return convertView;
    }

    @Override
    public Object getGroup(int listPosition) {
        return listParent.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return listParent.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.expandable_listgroup, null);
        }
        TextView listTitleTextView = (TextView) convertView
            .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }


}
