package com.androidtask.productcategory.networkmanager;

/**
 * Created by viveks on 4/18/2016.
 */
public interface NetworkResponseListener {

    static int PROTOCOL_EXCEPTION = 5551,
            IO_EXCEPTION = 5552,
            CONNECTION_TIMEOUT_EXCEPTION = 5553,
            INVALID_HTTP_STATUS_CODE = 5554,
            INVALID_URL = 5555;

    public void onSuccess(String s, String result);

}
