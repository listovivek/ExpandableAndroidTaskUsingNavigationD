package com.androidtask.bottomsheet;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.androidtask.R;
import com.androidtask.navigationdrawer.MainActivity;

import java.util.ArrayList;

/**
 * Created by VIVEKS on 10/22/2016.
 */
public class BottomSheetActivity extends BottomSheetDialog {


    private Context context;

    public BottomSheetActivity(MainActivity mainActivity) {
        super(mainActivity);
        this.context = mainActivity;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            View view = getLayoutInflater().inflate(R.layout.bottom_sheet, null);
            setContentView(view);

            final ArrayList<String> items=new ArrayList<>();

            items.add("Android");
            items.add("IOS");
            items.add("Java");
            items.add("Python");
            items.add(".Net");
            items.add("Sqlite");
            items.add("SAP");


            BottomSheetListAdapter adapter = new BottomSheetListAdapter(this.context, items);
            ListView listView = (ListView) view.findViewById(R.id.list_items);
            listView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
