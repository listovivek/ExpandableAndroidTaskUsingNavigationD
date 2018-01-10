package com.androidtask.productcategory.UserProductManaager;

import android.content.Context;
import android.widget.Toast;

import com.androidtask.navigationdrawer.MainActivity;
import com.androidtask.productcategory.adminmanager.CommonLogger;
import com.androidtask.productcategory.networkmanager.NetworkManager;
import com.androidtask.productcategory.networkmanager.NetworkResponseListener;
import com.androidtask.productcategory.gsonvalues.GetMainCategory;
import com.androidtask.productcategory.gsonvalues.GetCategoryDetails;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VIVEKS on 10/18/2016.
 */
public class UserProductManager implements NetworkResponseListener {

    private static UserProductManager instance = null;
    private UserProductResponseListener mListener;
    private Context con;

    private static String TAG = UserProductManager.class.getSimpleName();

    public static UserProductManager getInstance() {

        if(instance == null){
            instance = new UserProductManager();
        }
        return instance;
    }

    public void serviceSubCategroy(MainActivity mainActivity, UserProductResponseListener mainActivity1, String categoryID, String getById) {

        try {
            this.mListener = mainActivity1;
            this.con = mainActivity;
            List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
            requestParams.add(new BasicNameValuePair("category_id", categoryID));

            NetworkManager.getInstance().productDetails(requestParams, this, null, mainActivity, getById);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void serviceMainCategory(MainActivity mainActivity, UserProductResponseListener mainActivity1) {
        this.mListener = mainActivity1;
        this.con = mainActivity;
        NetworkManager.getInstance().mainCategory(this, null);
    }

    @Override
    public void onSuccess(String result, String subcate) {


        try {
            if(subcate.equalsIgnoreCase("subCategory")){
                GetCategoryDetails details = new GetCategoryDetails();
                Gson gsonInstance = new Gson();

                GetCategoryDetails userD = gsonInstance.fromJson(result, GetCategoryDetails.class);

                if(userD != null){
                    mListener.onSuccessSubCategory(userD, subcate);
                }else{
                    mListener.onError("No response");
                }
            }else{
                GetMainCategory mainCategory = new GetMainCategory();

                Gson gsonInstance = new Gson();

                GetMainCategory userD = gsonInstance.fromJson(result, GetMainCategory.class);

                if(userD != null){
                    mListener.onSuccessMainCategory(userD);
                }else{
                    mListener.onError("No response");
                }
            }

        } catch (JsonSyntaxException e) {
            CommonLogger.d(TAG, e.toString());
            MainActivity.mprProgressDialog.dismiss();
            Toast.makeText(con, "Service give fatal error", Toast.LENGTH_SHORT).show();
        }
    }


}
