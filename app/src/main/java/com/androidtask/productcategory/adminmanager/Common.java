package com.androidtask.productcategory.adminmanager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.widget.Toast;

import com.androidtask.navigationdrawer.MainActivity;

/**
 * Created by USER on 09-07-2015.
 */
public class Common {

    private static Common instance = null;
   private static Context con;

    public static Common getInstance(){

        if(instance == null){
            instance = new Common();
        }
        return instance;
    }


    public boolean checkNetworkConnection(Context context) {

        ConnectivityManager check = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        try {
            if (check != null)
            {
                NetworkInfo[] info = check.getAllNetworkInfo();

                if (info != null)
                    for (int i = 0; i < info.length; i++)
                        if (info[i].getState() == NetworkInfo.State.CONNECTED)
                        {
                            return true;
                        }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    


	/*public static void toToastVisible(String text, MainActivity mainActivity) {
		Toast t = new Toast(mainActivity);
		t.setText(text);
		t.setGravity(Gravity.CENTER, 0, 0);
		t.show();
	}*/
}
