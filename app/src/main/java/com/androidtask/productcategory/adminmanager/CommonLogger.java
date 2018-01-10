package com.androidtask.productcategory.adminmanager;

import android.util.Log;


public class CommonLogger
{
	private static final boolean SHOULD_LOG = false;

	public static void d(String tag, String message)
	{
		if (SHOULD_LOG)
		{
			Log.d(tag, message);
		}
	}

	public static void v(String tag, String message)
	{
		if (SHOULD_LOG)
		{
			Log.v(tag, message);
		}
	}
	public static void e(String tag, String message)
	{
		if (SHOULD_LOG)
		{
			Log.e(tag, message);
		}
	}
}
