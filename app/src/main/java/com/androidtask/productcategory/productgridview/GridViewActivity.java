package com.androidtask.productcategory.productgridview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.androidtask.R;
import com.androidtask.bottomsheet.BottomSheetActivity;
import com.androidtask.navigationdrawer.MainActivity;

/**
 * Created by viveks on 10/19/2016.
 */
public class GridViewActivity  extends Fragment {

    View view;
   // protected BottomSheetLayout bottomSheetLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        view = inflater.inflate(R.layout.productlist_gridview, container,false);

        GridView list = (GridView) view.findViewById(R.id.gridView);
        list.setAdapter(MainActivity.gridAdapter);

        Button button = (Button) view.findViewById(R.id.list_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetActivity dialog = new BottomSheetActivity((MainActivity) getActivity());
                dialog.show();

                /*bottomSheetLayout = (BottomSheetLayout) view.findViewById(R.id.bottomsheet);
                bottomSheetLayout.setPeekOnDismiss(true);
                showMenuSheet(MenuSheetView.MenuType.LIST);*/
            }
        });





        return view;
    }

   /* private void showMenuSheet(final MenuSheetView.MenuType menuType) {
        try {
            MenuSheetView menuSheetView = new MenuSheetView(getActivity(), menuType, "Bottom Sheet Static Listview...",
                    new MenuSheetView.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
                    if (bottomSheetLayout.isSheetShowing()) {
                        bottomSheetLayout.dismissSheet();
                    }
                    if (item.getItemId() == R.id.reopen) {
                        showMenuSheet(menuType == MenuSheetView.MenuType.LIST ?
                                MenuSheetView.MenuType.GRID : MenuSheetView.MenuType.LIST);
                    }
                    return true;
                }
            });
            menuSheetView.inflateMenu(R.menu.create);
            bottomSheetLayout.showWithSheetView(menuSheetView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}
