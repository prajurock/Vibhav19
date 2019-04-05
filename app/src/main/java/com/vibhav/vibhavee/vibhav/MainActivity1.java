package com.vibhav.vibhavee.vibhav;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity1 extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    //private LinearLayout log_Section;
    //private Button SignOut;
    private SignInButton SignIn;
    private TextView Name,Email;
    //private ImageView Prof_pic;
    private GoogleApiClient googleApiClient;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int REQ_CODE= 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //log_Section=(LinearLayout)findViewById(R.id.log_section);
        //SignOut= (Button)findViewById(R.id.logout);
        SignIn= (SignInButton)findViewById(R.id.go_login);
        Name= (TextView)findViewById(R.id.name);
        Email= (TextView)findViewById(R.id.email);
        //Prof_pic= (ImageView)findViewById(R.id.prof_pic);
        SignIn.setOnClickListener(this);
        //SignOut.setOnClickListener(this);
        //log_Section.setVisibility(View.GONE);
        GoogleSignInOptions signInOptions= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient= new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, signInOptions);
        Task<GoogleSignInAccount> task = mGoogleSignInClient.silentSignIn();
        if (task.isSuccessful()) {
            // There's immediate result available.
            GoogleSignInAccount signInAccount = task.getResult();
            //updateViewWithAccount(account);
        }
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null)
        {
            Intent intent = new Intent("com.vibhav.vibhavee.vibhav.HOME");
            //startActivity(intent);
            signIn();
        }

        Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            if(bundle!=null){
            String signout = bundle.getString("Signout");
                Toast toast = Toast.makeText(getApplicationContext(), "logout () init", Toast.LENGTH_LONG);
                toast.show();
                if(signout=="true"){
                    signOut();}

            }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
                signIn();
                break;
            case R.id.logout:
                signOut();
                break;
        }

    }

    private void signIn()
    {
        Intent intent= mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent, REQ_CODE);

    }
    private void signOut()
    {
//        Auth.GoogleSignInApi.signOut(googleApiClient)
//                .setResultCallback(new ResultCallback<Status>() {
//            @Override
//            public void onResult(@NonNull Status status) {
//                updateUI(false);
//            }
//        });
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        updateUI(false);
                        // [END_EXCLUDE]
                    }
                });

    }
    private void handleResult(
            //GoogleSignInResult result
            Task<GoogleSignInAccount> completedTask)
    {
//        if(result.isSuccess())
//        {
//            GoogleSignInAccount account= result.getSignInAccount();
//            String name = account.getDisplayName();
//            String email = account.getEmail();
//            String img_url= account.getPhotoUrl().toString();
//            Name.setText(name);
//            Email.setText(email);
//            Glide.with(this).load(img_url).into(Prof_pic);
//            updateUI(true);
//        }
//        else
//        {
//            updateUI(false);
//        }

        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            String name = account.getDisplayName();
            String email = account.getEmail();
            //String img_url=account.getPhotoUrl().toString();
            //Name.setText(name);
            //Email.setText(email);
            //Glide.with(this).load(img_url).into(Prof_pic);
            updateUI(true);
            Toast toast = Toast.makeText(this, "Handling Result", Toast.LENGTH_LONG);
            toast.show();
            // 1. create an intent pass class name or intnet action name
            Intent intent = new Intent("com.vibhav.vibhavee.vibhav.HOME");

            // 2. put key/value data
            //intent.putExtra("message", "Hello From MainActivity");

            // 3. or you can add data to a bundle
            Bundle extras = new Bundle();
            extras.putString("Name", name);
            extras.putString("Email", email);

            // 4. add bundle to intent
            intent.putExtras(extras);

            // 5. start the activity
            startActivity(intent);
            this.finish();

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            //Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            Toast toast = Toast.makeText(this, "Catch exp", Toast.LENGTH_LONG);
            toast.show();
            updateUI(false);
        }

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
}

