package com.androidtask.navigationdrawer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.androidtask.R;
import com.androidtask.bottomsheet.BottomSheetActivity;
import com.androidtask.productcategory.UserProductManaager.UserProductManager;
import com.androidtask.productcategory.UserProductManaager.UserProductResponseListener;
import com.androidtask.productcategory.adminmanager.Common;
import com.androidtask.productcategory.adminmanager.CommonLogger;
import com.androidtask.productcategory.adminmanager.ProgressBarStyle;
import com.androidtask.productcategory.productgridview.GridViewActivity;
import com.androidtask.productcategory.productgridview.GridviewAdapter;
import com.androidtask.productcategory.gsonvalues.GetMainCategory;
import com.androidtask.productcategory.gsonvalues.GetCategoryDetails;
import com.androidtask.productcategory.gsonvalues.ChildCategory;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity implements UserProductResponseListener, View.OnClickListener{

    public static Dialog mprProgressDialog;
    public static GridviewAdapter gridAdapter;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private ListView listView;
    private ArrayList ArrayList1, ArrayList2;

    private ExpandableListView mExpandableListView;
    private ExpandableListAdapter mExpandableListAdapter;

   // protected BottomSheetLayout bottomSheetLayout;

    private Button bottomButton, navButton;

    private static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        bottomButton = (Button) findViewById(R.id.list_button);
        bottomButton.setOnClickListener(this);

        mprProgressDialog = ProgressBarStyle.getInstance().createProgressDialog(this);

        View includedLayout = this.findViewById(R.id.navList);

        mExpandableListView = (ExpandableListView) includedLayout.findViewById(R.id.nav_expandList);
        listView = (ListView) includedLayout.findViewById(R.id.nav_listview);

        LayoutInflater inflater = this.getLayoutInflater();
        View listHeaderView = inflater.inflate(R.layout.nav_header, null, false);
        this.listView.addHeaderView(listHeaderView);
        this.mExpandableListView.addHeaderView(listHeaderView);

        navButton = (Button) listHeaderView.findViewById(R.id.nav_button);
        navButton.setOnClickListener(this);

        callToService();
        setupDrawer();

        try {
            if (savedInstanceState == null) {
                selectFirstItemAsDefault();
            }

            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            this.getSupportActionBar().setHomeButtonEnabled(true);
        } catch (Exception e) {
            CommonLogger.d(TAG, e.toString());
        }
    }

   /* private void showMenuSheet(final MenuSheetView.MenuType menuType) {
        try {
            MenuSheetView menuSheetView = new MenuSheetView(MainActivity.this, menuType, "Create...", new MenuSheetView.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
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


    private void callToService() {

        try {
            if(Common.getInstance().checkNetworkConnection(getApplicationContext())){
                this.mprProgressDialog.show();
                UserProductManager.getInstance().serviceMainCategory(this, this);
            }else {
                Toast.makeText(MainActivity.this,
                        "Kindly check your network connection", Toast.LENGTH_LONG).show();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void selectFirstItemAsDefault() {
           // mNavigationManager.showFragmentAction(firstActionMovie);
        try {
            this.getSupportActionBar().setTitle(getResources().getStringArray(R.array.items)[0]);
        } catch (Resources.NotFoundException e) {
            CommonLogger.d(TAG, e.toString());
        }

    }


    private void callChildData(String s, String getByID) {
        try {
            if(Common.getInstance().checkNetworkConnection(getApplicationContext())){
                this.mprProgressDialog.show();
                UserProductManager.getInstance().serviceSubCategroy(this, this, s, getByID);
            }else {
                Toast.makeText(MainActivity.this,
                        "Kindly check your network connection", Toast.LENGTH_LONG).show();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setupDrawer() {
        this.mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu();
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
               // getSupportActionBar().setTitle(getResources().getStringArray(R.array.items)[0]);
                invalidateOptionsMenu();
            }
        };

        this.mDrawerToggle.setDrawerIndicatorEnabled(true);
        this.mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        this.mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSuccessSubCategory(final GetCategoryDetails userD, final String subCategroy) {

        //subcategory
        try {

        if(userD.categoryList.get(0).price == null && userD.categoryList.get(0).productcode == null){

            this.listView.setVisibility(View.GONE);
            this.mExpandableListView.setVisibility(View.VISIBLE);

            ArrayList1 = new ArrayList();
            final HashMap localHashMap = new HashMap();

            for(int t=0; t<userD.categoryList.size(); t++) {

                ArrayList1.add(userD.categoryList.get(t).parentProductName);
                List<ChildCategory> subBcategory = userD.categoryList.get(t).subCategoryList;
                CommonLogger.d(TAG, subBcategory.toString());
                ArrayList2 = new ArrayList();
                if(subBcategory != null){
                    for (int j = 0; j < subBcategory.size(); j++) {
                        ArrayList2.add(subBcategory.get(j).childProductName);
                    }
                }
                localHashMap.put(ArrayList1.get(t).toString(), ArrayList2);
                CommonLogger.d(TAG, localHashMap.toString());
            }
            this.mprProgressDialog.dismiss();



                mExpandableListAdapter = new CustomExpandableListAdapter(MainActivity.this, ArrayList1,
                        localHashMap);
                mExpandableListView.setAdapter(mExpandableListAdapter);

                mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                        try {
                            if (userD.categoryList.get(groupPosition).subCategoryList.size() == 0) {
                                callChildData(userD.categoryList.get(groupPosition).parentCategoryID, "getProductById");
                                Log.d("parentcategory", userD.categoryList.get(groupPosition).parentCategoryID);
                                mDrawerLayout.closeDrawers();
                            }

                            getSupportActionBar().setTitle(ArrayList1.get(groupPosition).toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return false;
                    }
                });

                mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                        try {
                            buttonGone();

                            callChildData(userD.categoryList.get(groupPosition).subCategoryList.get(childPosition).childCategoryID, "getProductById");
                            Log.d("subCategory", userD.categoryList.get(groupPosition).subCategoryList.get(childPosition).childCategoryID);
                            mDrawerLayout.closeDrawers();
                            getSupportActionBar().setTitle((String) parent.getExpandableListAdapter().getChild(groupPosition, childPosition));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return false;
                    }
                });


        }else{

            //childcategory

            try {
                gridAdapter = new GridviewAdapter(this, R.layout.gridview_adpapter, userD);
                FragmentManager fm = getSupportFragmentManager();
                this.bottomButton.setVisibility(View.GONE);
                GridViewActivity act = new GridViewActivity();

                @SuppressLint("CommitTransaction")
                FragmentTransaction ft = fm.beginTransaction().replace(R.id.container, act);

                ft.addToBackStack(null);

                ft.commit();

                fm.executePendingTransactions();

                this.mprProgressDialog.dismiss();
            } catch (Exception e) {
                CommonLogger.d(TAG, e.toString());
            }

        }

        } catch (Exception e) {
            CommonLogger.d(TAG, e.toString());
        }

    }

    private void buttonGone() {
        this.bottomButton.setVisibility(View.GONE);
    }

    @Override
    public void onError(String s) {
        Toast.makeText(MainActivity.this,
                s, Toast.LENGTH_LONG).show();
    }

           @Override
           public void onSuccessMainCategory(final GetMainCategory userD) {


               try {
                   this.mprProgressDialog.dismiss();

                   CustomListViewAdapter adapter = new CustomListViewAdapter(this, userD);
                   listView.setAdapter(adapter);


                   listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                       @Override
                       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                           try {

                                   getSupportActionBar().setTitle(userD.mainCatgArrayLists.get(position-1).mainProductName);
                               CommonLogger.d(TAG, userD.mainCatgArrayLists.get(position - 1).mainProductName);
                              // String nd = userD.result.get(position-1).categoryID;
                               callChildData(userD.mainCatgArrayLists.get(position - 1).mainCategoryID, "getCategoryById");
                               CommonLogger.d(TAG, userD.mainCatgArrayLists.get(position - 1).mainCategoryID);
                           } catch (Exception e) {
                               e.printStackTrace();
                           }
                       }
                   });
               } catch (Exception e) {
                   CommonLogger.d(TAG, e.toString());
               }

           }


    @Override
    public void onClick(View v) {
        try {
            if(bottomButton == v){
                /*bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomsheet);
                bottomSheetLayout.setPeekOnDismiss(true);
                showMenuSheet(MenuSheetView.MenuType.LIST);*/


                BottomSheetActivity dialog = new BottomSheetActivity(MainActivity.this);
                dialog.show();

            }else if(navButton == v){

                    getSupportActionBar().setTitle(getResources().getStringArray(R.array.items)[0]);

                this.listView.setVisibility(View.VISIBLE);
                this.mExpandableListView.setVisibility(View.GONE);
            }
        } catch (Resources.NotFoundException e) {
            CommonLogger.d(TAG, e.toString());
        }
    }
}
