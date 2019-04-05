package com.vibhav.vibhavee.vibhav;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

//import com.vibhav.vibhavee.vibhav.registation.BullRegistartion;


/**
 * Created by prajwal on 18/10/17.
 */

public class Online extends AppCompatActivity {
    private static Button about;
    private   AlertDialog.Builder about_builder;
    private   AlertDialog.Builder rules_builder;
    private   AlertDialog.Builder judgingCriteria_builder;
    private   AlertDialog.Builder prizes_builder;
    private   AlertDialog.Builder contactUs_builder;
    private   AlertDialog.Builder payment_builder;
    String bucks_token ,event="buffet_money";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aavis);
        next();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ImageView imageView=(ImageView)findViewById(R.id.titleImage);
        imageView.setImageResource(R.drawable.st);





    }public void next(){
        bucks_token=SharedPrefManager.getInstance(getApplicationContext()).getEventToken(event);
        if(bucks_token==null){
            bucks_token="0";
        }
        //setContentView(R.layout.activity_placement_fever);
        TextView text=(TextView)findViewById(R.id.register);
        //Log.d("codeathon", "onCreate: "+ bucks_token);
        if(!bucks_token.equals("0") && !bucks_token.equals("1")){
            text.setText("ENTER COUPON CODE");
            text.setBackgroundColor(Color.rgb(9,151,30));
        }
        else if(bucks_token.equals("1")){
            text.setText("100 BUCKS RECEIVED");
            text.setBackgroundColor(Color.rgb(9,151,30));
            text.setClickable(false);
        }
    }
    public void register(View view) {
        Intent intent=new Intent(this,madhavBhai.class);
        //intent.putExtra("event",event);
        String bucks_token=SharedPrefManager.getInstance(getApplicationContext()).getEventToken(event);
        if(bucks_token==null){
            bucks_token="0";
        }
        intent.putExtra("event", event);
        intent.putExtra("token", bucks_token);
        startActivity(intent);
        finish();

    }
    private void registerEvent() {


        //first we will do the validations
//        if (TextUtils.isEmpty(username)) {
//            editTextUsername.setError("Please enter username");
//            editTextUsername.requestFocus();
//            return;
//        }
//
//        if (TextUtils.isEmpty(email)) {
//            editTextEmail.setError("Please enter your email");
//            editTextEmail.requestFocus();
//            return;
//        }
//
//        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            editTextEmail.setError("Enter a valid email");
//            editTextEmail.requestFocus();
//            return;
//        }
//
//        if (TextUtils.isEmpty(password)) {
//            editTextPassword.setError("Enter a password");
//            editTextPassword.requestFocus();
//            return;
//        }

        //if it passes all the validations

        class RegisterEvent extends AsyncTask<Void, Void, String> {

            //private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();
                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("team_name", "none");
                params.put("adm", "none");
                params.put("event",event);
                //Toast.makeText(getApplicationContext(), "PARAMS   "+ params.toString(), Toast.LENGTH_SHORT).show();
                //returing the response
                String token=SharedPrefManager.getInstance(getApplicationContext()).getToken();
                Log.d("Eventfffff", "doInBackground: "+params.toString()+ "token "+ token);
                return requestHandler.sendPostRequest(URLs.URL_TEAM_EVENT_REGISTER, params,"Token "+token);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //hiding the progressbar after completion
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), "String  "+s, Toast.LENGTH_LONG).show();
                Log.d("Stringaaaa", "onPostExecute: "+s);
                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (obj.getString("message").equals("Registered successfully in event :"+event)) {
                        //Toast.makeText(getApplicationContext(), "Object  "+obj.getString("message"), Toast.LENGTH_LONG).show();

                        Toast.makeText(getApplicationContext(), "User Registered", Toast.LENGTH_LONG).show();
                        //starting the profile activity
                        Log.d("message", "onPostExecute: done");
                        userLogin();
                        confirmInput();
                    } else {
                        //Toast.makeText(getApplicationContext(), "ggugugughughugugug", Toast.LENGTH_SHORT).show();
                        Log.d("no_message", "onPostExecute: zxxz");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        //executing the async task
        RegisterEvent ru = new RegisterEvent();
        ru.execute();
    }

    public void about(View view){
        about_builder= new AlertDialog.Builder(Online.this);
        about_builder.setMessage(R.string.aboutv);
        AlertDialog alert_about = about_builder.create();
        alert_about.setTitle("About");
        alert_about.show();

    }
    public void rules(View view){
        rules_builder= new AlertDialog.Builder(Online.this);
        rules_builder.setMessage(R.string.rulesv);
        AlertDialog alert_about = rules_builder.create();
        alert_about.setTitle("Rules");
        alert_about.show();

    }
    public void judgingCriteria(View view){
        judgingCriteria_builder= new AlertDialog.Builder(Online.this);
        judgingCriteria_builder.setMessage(R.string.judgingCriteriav);
        AlertDialog alert_about = judgingCriteria_builder.create();
        alert_about.setTitle("Judging Criteria");
        alert_about.show();

    }
    public void prizes(View view){
        prizes_builder= new AlertDialog.Builder(Online.this);
        prizes_builder.setMessage(R.string.prizesv);
        AlertDialog alert_about = prizes_builder.create();
        alert_about.setTitle("Prizes");
        alert_about.show();

    }
    public void contactUs(View view){
        contactUs_builder= new AlertDialog.Builder(Online.this);
        contactUs_builder.setMessage(R.string.contactusv);
        AlertDialog alert_about = contactUs_builder.create();
        alert_about.setTitle("Contact Us");
        alert_about.show();

    }
    public void payments(View view){
        payment_builder= new AlertDialog.Builder(Online.this);
        payment_builder.setMessage(R.string.paymentv);
        AlertDialog alert_about = payment_builder.create();
        alert_about.setTitle("Payment Details");
        alert_about.show();

    }
    public void confirmInput() {


        Intent i = new Intent(this, events.class);
        finish();
        startActivity(i);


    }

    private void userLogin() {
        //first getting the values
        final User user;
        user=SharedPrefManager.getInstance(getApplicationContext()).getUser();
        final String username = user.getId();
        final String password = user.getEmail();

        //validating inputs
//        if (TextUtils.isEmpty(username)) {
//            editTextUsername.setError("Please enter your username");
//            editTextUsername.requestFocus();
//            return;
//        }
//
//        if (TextUtils.isEmpty(password)) {
//            editTextPassword.setError("Please enter your password");
//            editTextPassword.requestFocus();
//            return;
//        }

        //if everything is fine

        class UserLogin extends AsyncTask<Void, Void, String> {

            ProgressBar progressBar;
            GetRequestHandler getRequestHandler = new GetRequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);


                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);
                    Log.d("ama",obj.getString("token"));


                    //if no error in response
                    //Log.d("login", "onstExecute: "+ obj.getString("user_tokens"));
                    //obj.getString("user_tokens");

                    if (!obj.getString("token").equals("null")) {
                        //Toast.makeText(getApplicationContext(), obj.getString("token"), Toast.LENGTH_SHORT).show();
                        //GetRequestHandler();
                        //getting the user from the response

                        //Log.d("JSON ", "onPostExecute: " + obj.getString("user_tokens"));
                        JSONObject event_token= obj.getJSONObject("event_tokens");
//                        String ndata = data.substring(1,data.length()-2);
//                        Log.d("JSONnnnn", "onPostExecute: "+ ndata);
//                        J parser =new JsonParser();
//                        JsonObject json=(JsonObject) parser.parse(ndata);
                        //String Data= getRequestHandler.get.execute().toString();

//                        Toast.makeText(getApplicationContext(), "USER DATA"+ Data, Toast.LENGTH_LONG).show();
//                        Log.d("login", "onPostExecute: "+ Data);
                        //dialog();
                        //creating a new user object
                        SharedPrefManager.getInstance(getApplicationContext()).eventTokenSave(event_token);
                        Log.d("dddddd", "onPostExecute: "+ event_token.toString());
                        SharedPrefManager.getInstance(getApplicationContext()).tokenSave(obj.getString("token"));
                        String token=SharedPrefManager.getInstance(getApplicationContext()).getToken();
                       // Toast.makeText(getApplicationContext(), "TOKEn "+ obj.getString("token"), Toast.LENGTH_LONG).show();
                        Log.d("token", "onPostExecute: "+ token);
                        //starting the profile activity
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {
                        //Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                        //dialog();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("admission", username);
                params.put("password", password);

                //returing the response
                Log.d("logingggg", "doInBackground: "+params.toString());
                return requestHandler.sendPostRequest(URLs.URL_LOGIN, params,"null");
            }
        }

        UserLogin ul = new UserLogin();
        ul.execute();
    }

}