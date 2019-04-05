package com.vibhav.vibhavee.vibhav;


import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;

public class Splash extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{

    /* Duration of wait */
    private final int SPLASH_DISPLAY_LENGTH = 300;
    private SignInButton SignIn;
    private TextView Name,Email;
    private  ProgressBar mProgress;
    private GoogleApiClient googleApiClient;
     private GoogleSignInClient mGoogleSignInClient;
    private static final int REQ_CODE= 9001;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private Intent intent;
    private String name,id,img_url,email,adm_no,mobile, idauth,branch,data;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.screensplash);
        SignIn= (SignInButton)findViewById(R.id.go_login);
        mProgress=findViewById(R.id.progressBar);
        SignIn.setOnClickListener(this);

            GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestProfile()
                    .requestEmail()
                    .build();

            googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();

            mGoogleSignInClient = GoogleSignIn.getClient(this, signInOptions);
            //dialog();
            final Task<GoogleSignInAccount> task = mGoogleSignInClient.silentSignIn();
            if (task.isSuccessful()) {
                // There's immediate result available.
                GoogleSignInAccount signInAccount = task.getResult();
                name = signInAccount.getDisplayName();
                id = signInAccount.getId();
                email = signInAccount.getEmail();
                idauth = signInAccount.getEmail();
                img_url = signInAccount.getPhotoUrl() != null ? signInAccount.getPhotoUrl().toString() : null;
            //    Toast toast = Toast.makeText(this, name + "  " + id + "  " + img_url + "  " + email + "  " + idauth + "   " + signInAccount.getEmail(), Toast.LENGTH_LONG);
              //  toast.show();
                if (!InternetConnection.checkConnection(getApplicationContext())) {
                    Toast.makeText(this,"Turn On Internet",Toast.LENGTH_LONG).show();
                    splash();
                }
                else {
                    userLogin();
                    updateUI(true);
                }
            } else {
                // There's no immediate result ready, displays some progress indicator and waits for the
                // async callback.
                task.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(Task task1) {
                        try {
                            ///hideProgressIndicator();
                            GoogleSignInAccount signInAccount = task.getResult(ApiException.class);
                            if (!InternetConnection.checkConnection(getApplicationContext())) {
                                Toast.makeText(getApplicationContext(),"Turn On Internet",Toast.LENGTH_LONG).show();
                                splash();
                            }
                            else {
                                userLogin();
                            }
                            updateUI(true);

                            //dialog();
                        } catch (ApiException apiException) {
                            // You can get from apiException.getStatusCode() the detailed error code
                            // e.g. GoogleSignInStatusCodes.SIGN_IN_REQUIRED means user needs to take
                            // explicit action to finish sign-in;
                            updateUI(false);
                            // Please refer to GoogleSignInStatusCodes Javadoc for details
                            // updateButtonsAndStatusFromErrorCode(apiException.getStatusCode());
                        }
                    }
                });
            }
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        if(account!=null)
//        {
//            signIn();
//        }





    }
    private void splash(){
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.go_login:
                if (InternetConnection.checkConnection(getApplicationContext())) {
                    signIn();}
                    else{
                    Toast.makeText(this,"Check Network Connection",Toast.LENGTH_SHORT).show();
                }
                break;
//            case R.id.logout:
//                signOut();
//                break;
        }

    }

    private void signIn()
    {
//        Intent intent= Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
//        startActivityForResult(intent, REQ_CODE);
        Intent intent= mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent, REQ_CODE);

    }




    private void handleResult(Task<GoogleSignInAccount> completedTask)
    {

        try {
            mProgress.setVisibility(View.VISIBLE);
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            name = account.getDisplayName();
            id =account.getId();
            email = account.getEmail();
            idauth = account.getEmail();
            img_url=account.getPhotoUrl() != null ? account.getPhotoUrl().toString() : null;
            SharedPrefManager.getInstance(getApplicationContext()).saveProfPic(img_url);
            //Name.setText(name);
            //Email.setText(email);
            //Glide.with(this).load(img_url).into(Prof_pic);
            updateUI(true);
                    //toast.show();
            Log.d("dssssssssss", "handleResult: "+name+ "  "+id+"  "+img_url+"  "+email+"  "+ idauth+"   "+ account.getEmail());
//            mPreferences= getSharedPreferences("tempDatabase",Context.MODE_PRIVATE);
//            mEditor = mPreferences.edit();
//            mEditor.putString("Name", name);
//            mEditor.putString("Email", email);
//            mEditor.putString("Profile", img_url);
//            mEditor.putString("Id",id);
//            mEditor.apply();

            //intent = new Intent(this, MainActivity.class);

            // 2. put key/value data
            //intent.putExtra("message", "Hello From MainActivity");

//            // 3. or you can add data to a bundle
//            Bundle extras = new Bundle();
//            extras.putString("Name", name);
//            extras.putString("Email", email);
            Toast toast1 = Toast.makeText(this, "User login called ", Toast.LENGTH_LONG);
            //toast1.show();
            userLogin();

            // 4. add bundle to intent
            //intent.putExtras(extras);

            // 5. start the activity
            //activityChange(intent);
            //this.finish();

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            //Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
//            Toast toast = Toast.makeText(this, "Catch exp"+e.toString(), Toast.LENGTH_LONG);
//            toast.show();

            Log.d("Api", "Api Exception: "+e.toString());
            updateUI(false);
        }

    }
    private void activityChange(Intent intent){
        Intent intent1 = new Intent(this, MainActivity.class);

        startActivity(intent1);
        this.finish();


    }
    private void updateUI(boolean isLogin)
    {
        if(isLogin)
        {
            //log_Section.setVisibility(View.VISIBLE);
            SignIn.setVisibility(View.GONE);
        }
        else
        {
            //log_Section.setVisibility(View.GONE);
            SignIn.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode== REQ_CODE)
        {
//            GoogleSignInResult result= Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            handleResult(result);
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleResult(task);
        }

    }
    private void dialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        final View view= this.getLayoutInflater().inflate(R.layout.signup_details, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        //Intent intent1= new Intent(this, MainActivity.class);
        final TextView adm_no_input= (TextView)view.findViewById(R.id.adm_no);
        final TextView phone_input= (TextView)view.findViewById(R.id.mobile);
        final TextView branch_input= (TextView)view.findViewById(R.id.branch);
        builder.setView(view).setCancelable(false)
                // Add action buttons
                .setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                        adm_no= adm_no_input.getText().toString().trim();
                        mobile= phone_input.getText().toString().trim();
                        branch= branch_input.getText().toString().trim();
                        registerUser();
                        //activityChange(intent);
                    }
                });
                builder.show();
    }

    private void registerUser() {

        //first we will do the validations
//        Toast toast = Toast.makeText(getApplicationContext(), adm_no+" "+mobile+name+id+img_url+email, Toast.LENGTH_LONG);
//                toast.show();

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

        class RegisterUser extends AsyncTask<Void, Void, String> {

            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();
                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("admission", adm_no);
                params.put("branch", branch);
                params.put("phone", mobile);
                params.put("password",idauth);
                params.put("google_id",id);

                //Toast.makeText(getApplicationContext(), "PARAMS   "+ params.toString(), Toast.LENGTH_SHORT).show();
                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_REGISTER, params,"null");
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server
                //progressBar = (ProgressBar) findViewById(R.id.prog r                                                        essBar);
                //progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //hiding the progressbar after completion
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);
                    Log.d("obj",obj.toString());

                    //if no error in response
                    if (obj.getString("error").equals("false")) {
                        ///Toast.makeText(getApplicationContext(), "Object  "+obj.getString("message"), Toast.LENGTH_LONG).show();

                        User user = new User(
                                name,
                                email,
                                id,
                                mobile,
                                adm_no,
                                branch,
                                500
                        );
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                   Log.d("vvvvvvvvvvv", "onPostExecute: "+obj.toString());
                        SharedPrefManager.getInstance(getApplicationContext()).tokenSave(obj.getString("token"));
                        Toast.makeText(getApplicationContext(), "User Registered", Toast.LENGTH_LONG).show();
                        //starting the profile activity
                        userLogin();
                    } else {
                        Toast.makeText(getApplicationContext(), "User Already register", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        //executing the async task
        RegisterUser ru = new RegisterUser();
        ru.execute();
    }

    private void userLogin() {
        //first getting the values
        final String username = id;
        final String password = idauth;

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
                   // Log.d("login", "onstExecute: "+ obj.getString("user_tokens"));
                    //obj.getString("user_tokens");

                    if (!obj.getString("token").equals("null")) {
                /*   Toast.makeText(getApplicationContext(), obj.getString("token"), Toast.LENGTH_SHORT).show();*/
                        // GetRequestHandler();
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
                        User user = new User(
                                user1.getString("name"),
                                email,
                                id,
                                user1.getString("phone"),
                                user1.getString("admission"),
                                user1.getString("branch"),
                                user1.getInt("coins")
                        );
                        Log.d("uuuuuuuuuuuuuuuuuuuuuu", "onPostExecute: "+ user1.getInt("coins"));
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                        SharedPrefManager.getInstance(getApplicationContext()).eventTokenSave(event_token);
                        Log.d("dddddd", "onPostExecute: "+ event_token.toString());
                        SharedPrefManager.getInstance(getApplicationContext()).tokenSave(obj.getString("token"));
                        String token=SharedPrefManager.getInstance(getApplicationContext()).getToken();
                      //  Toast.makeText(getApplicationContext(), "TOKEn "+ obj.getString("token"), Toast.LENGTH_LONG).show();
                        Log.d("token", "onPostExecute: "+ token);
                        //starting the profile activity
                        finish();
                        mProgress.setVisibility(View.INVISIBLE);
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {
//                        Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                        dialog();
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
                params.put("admission", id);
                params.put("password", email);

                //returing the response
                Log.d("logingggg", "doInBackground: "+params.toString());
                return requestHandler.sendPostRequest(URLs.URL_LOGIN, params,"null");
            }
        }

        UserLogin ul = new UserLogin();
        ul.execute();
    }
//    public void GetRequestHandler() {
//
//
//        //this method will send a post request to the specified url
//        //in this app we are using only post request
//        //in the hashmap we have the data to be sent to the server in keyvalue pairs
//        class HttpGetRequest extends AsyncTask<Void, Void, String> {
//            public static final String REQUEST_METHOD = "GET";
//            public static final int READ_TIMEOUT = 15000;
//            public static final String requestURL = URLs.URL_GET_DATA;
//            public static final int CONNECTION_TIMEOUT = 15000;
//            @Override
//            protected String doInBackground(Void... params){
//                URL url;
//                String result;
//                String inputLine;
//                try {
//                    //Create a URL object holding our url
//                    url = new URL(requestURL);
//                    //Create a connection
//                    HttpURLConnection connection =(HttpURLConnection) url.openConnection();
//                    //Set methods and timeouts
//                    connection.setRequestMethod(REQUEST_METHOD);
//                    connection.setReadTimeout(READ_TIMEOUT);
//                    connection.setConnectTimeout(CONNECTION_TIMEOUT);
//
//                    //Connect to our url
//                    connection.connect();
//                    //Create a new InputStreamReader
//                    InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
//                    //Create a new buffered reader and String Builder
//                    BufferedReader reader = new BufferedReader(streamReader);
//                    StringBuilder stringBuilder = new StringBuilder();
//                    //Check if the line we are reading is not null
//                    while((inputLine = reader.readLine()) != null){
//                        stringBuilder.append(inputLine);
//                    }
//                    //Close our InputStream and Buffered reader
//                    reader.close();
//                    streamReader.close();
//                    //Set our result equal to our stringBuilder
//                    result = stringBuilder.toString();
//                    Toast.makeText(getApplicationContext(), "GET   "+ result, Toast.LENGTH_SHORT).show();
//                    Log.d("message", "onPostExecute: "+ result);
//                }
//                catch(Exception e){
//                    e.printStackTrace();
//                    result = null;
//                }
//                return result;
//            }
//            @Override
//            protected void onPostExecute(String result){
//                super.onPostExecute(result);
//                Toast.makeText(getApplicationContext(), "result   "+ result, Toast.LENGTH_SHORT).show();
//                Log.d("result", "onPostExecute: "+ result);
//            }
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                //displaying the progress bar while user registers on the server
//                //progressBar = (ProgressBar) findViewById(R.id.progressBar);
//                //progressBar.setVisibility(View.VISIBLE);
//            }
//        }
//        HttpGetRequest hp= new HttpGetRequest();
//        hp.execute();
//    }



//    private void getData() {
//
//        /*
//         * Creating a String Request
//         * The request type is GET defined by first parameter
//         * The URL is defined in the second parameter
//         * Then we have a Response Listener and a Error Listener
//         * In response listener we will get the JSON response as a String
//         * */
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            //converting the string to json array object
//                            JSONArray array = new JSONArray(response);
//
//                            //traversing through all the object
//                            for (int i = 0; i < array.length(); i++) {
//
//                                //getting product object from json array
//                                JSONObject product = array.getJSONObject(i);
//
//                                //adding the product to product list
//                                productList.add(new Product(
//                                        product.getInt("id"),
//                                        product.getString("title"),
//                                        product.getString("shortdesc"),
//                                        product.getDouble("rating"),
//                                        product.getDouble("price"),
//                                        product.getString("image")
//                                ));
//                            }
//
//                            //creating adapter object and setting it to recyclerview
//                            ProductsAdapter adapter = new ProductsAdapter(MainActivity.this, productList);
//                            recyclerView.setAdapter(adapter);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                });
//
//        //adding our stringrequest to queue
//        Volley.newRequestQueue(this).add(stringRequest);
//    }


}



