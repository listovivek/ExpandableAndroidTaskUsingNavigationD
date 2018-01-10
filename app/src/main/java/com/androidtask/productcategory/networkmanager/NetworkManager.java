package com.androidtask.productcategory.networkmanager;

import com.androidtask.navigationdrawer.MainActivity;
import com.androidtask.productcategory.UserProductManaager.UserProductManager;
import com.androidtask.productcategory.adminmanager.Config;

import org.apache.http.NameValuePair;

import java.util.HashMap;
import java.util.List;


/**
 * Created by viveks on 4/18/2016.
 */

public class NetworkManager {

    private static NetworkManager instance = null;

    public static NetworkManager getInstance() {

        if(instance == null){
            instance = new NetworkManager();
        }
        return instance;
    }

    public void productDetails(List<NameValuePair> requestParams, NetworkResponseListener listener,
                               HashMap<String, String> o, MainActivity mainActivity, String getById) {
        NetworkService.getInstance().httpPost(Config.WEBserverURL+getById,
                requestParams, o, listener);
    }

    public void mainCategory(NetworkResponseListener listener, HashMap<String, String> o) {
        NetworkService.getInstance().httpPost(Config.WEBserverURL+"getMainCategory" ,
                null, o, listener);

    }
}
