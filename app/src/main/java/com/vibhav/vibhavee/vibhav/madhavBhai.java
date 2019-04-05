package com.vibhav.vibhavee.vibhav;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class madhavBhai extends AppCompatActivity implements View.OnClickListener {
    EditText team_name_field, adm_field,coupon;
    CheckBox check;
    String team_name, admission, event,token;
    TextView note,single;
    boolean singl=false;
    Button submit;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_madhav_bhai);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent intent=this.getIntent();
        token=intent.getStringExtra("token");
        Log.d("Bhaiii", "onCreate: "+token);
        this.setFinishOnTouchOutside(false);

        team_name_field= (EditText) findViewById(R.id.editText);
        adm_field= (EditText) findViewById(R.id.editText2);
        coupon= (EditText) findViewById(R.id.editText3);
        submit= (Button)findViewById(R.id.submit);
        check= (CheckBox) findViewById(R.id.checkbox_meat);
        note=(TextView)findViewById(R.id.note);
        single=findViewById(R.id.single);
        event=this.getIntent().getStringExtra("event");
        submit.setOnClickListener(this);
        singl=(event.equals("placement_fever")||event.equals("buffet_money")||event.equals("marketing_roadies"))&&token.equals("0");
        if(singl){
            findViewById(R.id.register).setVisibility(View.GONE);
            findViewById(R.id.bucks).setVisibility(View.GONE);
            note.setVisibility(View.GONE);
            single.setVisibility(View.VISIBLE);
        }else {
            updateUI(token.equals("0"));
        }
    }
    public void updateUI(boolean x){
        if(!x){
            findViewById(R.id.register).setVisibility(View.GONE);
            findViewById(R.id.bucks).setVisibility(View.VISIBLE);
            note.setVisibility(View.INVISIBLE);
        }
        else {
            findViewById(R.id.register).setVisibility(View.VISIBLE);
            findViewById(R.id.bucks).setVisibility(View.GONE);
        }

    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_meat:
                if (checked){
                    adm_field.setVisibility(View.VISIBLE);
                }
                // Put some meat on the sandwich

            else{
                    adm_field.setVisibility(View.GONE);
                    adm_field.setText("");
                }
                // Remove the meat
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.submit:
                if(token.equals("0"))
                    registerEvent();
                else
                    verify_token();
                break;
            case R.id.close:
                confirmInput();
                break;
        }

    }


    private void registerEvent() {

        admission=adm_field.getText().toString().trim();
        submit.setClickable(false);
        if(!singl){
        team_name=team_name_field.getText().toString().trim();}
        else {
            team_name="none";
        }
        if(admission.equals("")){
            admission="none";
        }

        //first we will do the validations
        //Toast toast = Toast.makeText(getApplicationContext(), admission+" "+team_name, Toast.LENGTH_LONG);
        Log.d("register Event", "registerEvent: "+admission+" "+team_name);
               // toast.show();
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


            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();
                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("team_name", team_name);
                params.put("adm", admission);
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
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //hiding the progressbar after completion
                progressBar.setVisibility(View.GONE);
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), "String  "+s, Toast.LENGTH_LONG).show();
                Log.d("Stringaaaa", "onPostExecute: "+s);
                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (obj.getString("message").equals("Registered successfully in event :"+event)) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();

                        //Toast.makeText(getApplicationContext(), "User Registered", Toast.LENGTH_LONG).show();
                        //starting the profile activity
                        Log.d("message", "onPostExecute: done");
                        userLogin();
                        confirmInput();
                    } else {
                        //Toast.makeText(getApplicationContext(), "ggugugughughugugug", Toast.LENGTH_SHORT).show();
                        Log.d("no_message", "onPostExecute: zxxz");
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                        submit.setClickable(true);
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
    private void verify_token() {

        submit.setClickable(false);
        final String coupon_code=coupon.getText().toString().trim();
        event=this.getIntent().getStringExtra("event");
        //first we will do the validations
        //Toast toast = Toast.makeText(getApplicationContext(), event+" "+coupon_code, Toast.LENGTH_LONG);
        //toast.show();
//        if (TextUtils.isEmpty(username)) {
//            editTextUsername.setError("Please enter username");
//            editTextUsername.requestFocus();
//            return;
//        }
//
//        if (TextUtils.isEmpty(email)) {
//            editTextEmail.setError("Please enter your email");
//            editTextEmail.requestFocus();
//           return;
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

        class verify extends AsyncTask<Void, Void, String> {

            //private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();
                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("coupon", coupon_code);
                params.put("event", event);
                //Toast.makeText(getApplicationContext(), "PARAMS   "+ params.toString(), Toast.LENGTH_SHORT).show();
                //returing the response
                String token=SharedPrefManager.getInstance(getApplicationContext()).getToken();
                Log.d("Eventfffff", "doInBackground: "+params.toString()+ "token "+ token);
                return requestHandler.sendPostRequest(URLs.URL_COUPON, params,"Token "+token);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar.setVisibility(View.VISIBLE);

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //hiding the progressbar after completion
                progressBar.setVisibility(View.GONE);

                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
               // Toast.makeText(getApplicationContext(), "String  "+s, Toast.LENGTH_LONG).show();
                Log.d("Stringaaaa", "onPostExecute: "+s);
                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (obj.getString("details").equals("Success")) {
                       // Toast.makeText(getApplicationContext(), "Object  "+obj.getString("details"), Toast.LENGTH_LONG).show();

                        Toast.makeText(getApplicationContext(), "Coupon Verified", Toast.LENGTH_LONG).show();
                        //starting the profile activity
                        Log.d("message", "onPostExecute: done");
                        userLogin();
                        confirmInput();
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong Coupon", Toast.LENGTH_SHORT).show();
                        submit.setClickable(true);
                        Log.d("no_message", "onPostExecute: zxxz");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        //executing the async task
        verify ru = new verify();
        ru.execute();
    }


    public void confirmInput() {
        submit.setClickable(true);
        Intent i = new Intent(this, events.class);
        finish();
        startActivity(i);
    }
    public void close(View view) {

        finish();

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
                        JSONObject user1= obj.getJSONObject("user_info");
//                        String ndata = data.substring(1,data.length()-2);
//                        Log.d("JSONnnnn", "onPostExecute: "+ ndata);
//                        J parser =new JsonParser();
//                        JsonObject json=(JsonObject) parser.parse(ndata);
                        //String Data= getRequestHandler.get.execute().toString();

//                        Toast.makeText(getApplicationContext(), "USER DATA"+ Data, Toast.LENGTH_LONG).show();
//                        Log.d("login", "onPostExecute: "+ Data);
                        //dialog();
                        //creating a new user object
                        Log.d("uuuuuuuuuuuuuuuuuuuuuu", "onPostExecute: "+ user1.getInt("coins"));
                        SharedPrefManager.getInstance(getApplicationContext()).coinSave(user1.getInt("coins"));
                        SharedPrefManager.getInstance(getApplicationContext()).eventTokenSave(event_token);
                        Log.d("dddddd", "onPostExecute: "+ event_token.toString());
                        SharedPrefManager.getInstance(getApplicationContext()).tokenSave(obj.getString("token"));
                        String token=SharedPrefManager.getInstance(getApplicationContext()).getToken();
                        //Toast.makeText(getApplicationContext(), "TOKEn "+ obj.getString("token"), Toast.LENGTH_LONG).show();
                        Log.d("token", "onPostExecute: "+ token);
                        //starting the profile activity
                        //finish();
                        //startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
