package com.androidtask.productcategory.networkmanager;

import android.os.AsyncTask;

import com.androidtask.productcategory.adminmanager.CommonLogger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by VIVEKS on 10/18/2016.
 */
public class NetworkService {

    private static NetworkService instance = null;
    String result = null;
    InputStream inputStream = null;
    private static String TAG = NetworkService.class.getSimpleName();

    public static NetworkService getInstance() {

        if(instance == null){
            instance = new NetworkService();
        }
        return instance;
    }

    public void httpPost(String maincatergory, List<NameValuePair> requestParams,
                         HashMap<String, String> requestHeader, NetworkResponseListener listener) {

        HttpPOSTHandler hander = new HttpPOSTHandler(maincatergory, requestParams, (HashMap<String, String>) requestHeader, listener);
        hander.execute("");

    }

    private class HttpPOSTHandler extends AsyncTask<String, String, String> {
        private String mURL;
        private NetworkResponseListener mListener;
        private List<NameValuePair> mRequestParams = new ArrayList<NameValuePair>();
        private HashMap<String, String> mRequestHeader = new HashMap<>();

        public HttpPOSTHandler(String mainCategory, List<NameValuePair> requestParams,
                               HashMap<String, String> requestHeader, NetworkResponseListener listener) {
            this.mURL = mainCategory;
            this.mListener = listener;
            this.mRequestParams = requestParams;

            if(requestHeader != null){
                this.mRequestHeader = requestHeader;
            }
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                HttpResponse httpResponse = null;
            if(mRequestParams != null){
                HttpPost httpGet = new HttpPost(mURL);

                    httpGet.setEntity(new UrlEncodedFormEntity(mRequestParams));

                    httpGet.setHeader("Accept", "application/json");
                    DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());

                    httpResponse = httpClient.execute(httpGet);
            }else{
                HttpGet httpGet = new HttpGet(mURL);

                httpGet.setHeader("Accept", "application/json");
                DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());

                httpResponse = httpClient.execute(httpGet);
            }

            int statusCode = httpResponse.getStatusLine().getStatusCode();

            String reason = httpResponse.getStatusLine().getReasonPhrase();

            StringBuilder sb = new StringBuilder();
            if (statusCode == 200) {
                HttpEntity entity = httpResponse.getEntity();
                inputStream = entity.getContent();
                BufferedReader bReader = new BufferedReader(
                        new InputStreamReader(inputStream, "UTF-8"), 8);
                String line = null;
                while ((line = bReader.readLine()) != null) {
                    sb.append(line);
                }
            } else {
                sb.append(reason);
            }
            result = sb.toString();

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            CommonLogger.d(TAG, result);
            if(mRequestParams != null) {
                mListener.onSuccess(result, "subCategory");
           }else{
                mListener.onSuccess(result, "mainMenu");
            }
        }
    }
}
