package com.vibhav.vibhavee.vibhav;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Belal on 9/5/2017.
 */

public class GetRequestHandler {


    //this method will send a post request to the specified url
    //in this app we are using only post request
    //in the hashmap we have the data to be sent to the server in keyvalue pairs
    class HttpGetRequest extends AsyncTask<Void, Void, String> {
        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final String requestURL = URLs.URL_GET_DATA;
        public static final int CONNECTION_TIMEOUT = 15000;
    @Override
    protected String doInBackground(Void... params){
        URL url;
        String result;
        String inputLine;
        try {
            //Create a URL object holding our url
            url = new URL(requestURL);
            //Create a connection
            HttpURLConnection connection =(HttpURLConnection) url.openConnection();
            //Set methods and timeouts
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

            //Connect to our url
            connection.connect();
            //Create a new InputStreamReader
            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
            //Create a new buffered reader and String Builder
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            //Check if the line we are reading is not null
            while((inputLine = reader.readLine()) != null){
                stringBuilder.append(inputLine);
            }
            //Close our InputStream and Buffered reader
            reader.close();
            streamReader.close();
            //Set our result equal to our stringBuilder
            result = stringBuilder.toString();

            Log.d("message", "onPostExecute: "+ result);
        }
        catch(Exception e){
            e.printStackTrace();
            result = null;
        }
        return result;
    }
    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
    }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //displaying the progress bar while user registers on the server
            //progressBar = (ProgressBar) findViewById(R.id.prog r                                                        essBar);
            //progressBar.setVisibility(View.VISIBLE);
        }
}
    HttpGetRequest get = new HttpGetRequest();


    }





    //this method is converting keyvalue pairs data into a query string as needed to send to the server



//public String sendGetRequest(String requestURL){
//        public class HttpGetRequest extends AsyncTask<String, Void, String> {
//    public static final String REQUEST_METHOD = "GET";
//    public static final int READ_TIMEOUT = 15000;
//    public static final int CONNECTION_TIMEOUT = 15000;
//    @Override
//    protected String doInBackground(String... params){
//        String stringUrl = params[0];
//        String result;
//        String inputLine;
//        try {
//            //Create a URL object holding our url
//            URL myUrl = new URL();
//            //Create a connection
//            HttpURLConnection connection =(HttpURLConnection) myUrl.openConnection();
//            //Set methods and timeouts
//            connection.setRequestMethod(REQUEST_METHOD);
//            connection.setReadTimeout(READ_TIMEOUT);
//            connection.setConnectTimeout(CONNECTION_TIMEOUT);
//
//            //Connect to our url
//            connection.connect();
//            //Create a new InputStreamReader
//            InputStreamReader streamReader = new
//                    InputStreamReader(connection.getInputStream());
//            //Create a new buffered reader and String Builder
//            BufferedReader reader = new BufferedReader(streamReader);
//            StringBuilder stringBuilder = new StringBuilder();
//            //Check if the line we are reading is not null
//            while((inputLine = reader.readLine()) != null){
//                stringBuilder.append(inputLine);
//            }
//            //Close our InputStream and Buffered reader
//            reader.close();
//            streamReader.close();
//            //Set our result equal to our stringBuilder
//            result = stringBuilder.toString();
//        }
//        catch(IOException e){
//            e.printStackTrace();
//            result = null;
//        }
//        return result;
//    }
//    protected void onPostExecute(String result){
//        super.onPostExecute(result);
//    }
//}
//        }
