package com.androidtask.productcategory.adminmanager;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.androidtask.R;


public class ProgressBarStyle {

	private static ProgressBarStyle mInstance = null;

	public static ProgressBarStyle getInstance()
	{
		if (mInstance == null)
		{
			mInstance = new ProgressBarStyle();
		}
		return mInstance;
	}

	public Dialog createProgressDialog(Context con)
	{
		Dialog loadingDialog = new Dialog(con);
		loadingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		loadingDialog.setContentView(R.layout.progressbar_dialog);
		loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		loadingDialog.setCancelable(false);
		return loadingDialog;
	}

	public void dismissProgressDialog(Dialog dialog)
	{
		if (dialog != null)
		{
			if (dialog.isShowing())
			{
				dialog.dismiss();
			}
		}
	}


}
