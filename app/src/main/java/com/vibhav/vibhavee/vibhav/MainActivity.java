package com.vibhav.vibhavee.vibhav;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.events.OnBannerClickListener;
import ss.com.bannerslider.views.BannerSlider;
import ss.com.bannerslider.views.indicators.IndicatorShape;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    GridLayout maingrid;
    ImageView imageView;
    private GoogleSignInClient mGoogleSignInClient;

    private ViewFlipper mViewFlipper;
    private Context mContext;
    private float initialX;

    private GestureDetector mDetector;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mContext = this;
        mViewFlipper = (ViewFlipper) this.findViewById(R.id.flipper1);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        String profpic=SharedPrefManager.getInstance(getApplicationContext()).getKeyPic();
        View hview=navigationView.getHeaderView(0);
        Log.d("prof", "onCreate: "+profpic);
        ImageView prof=hview.findViewById(R.id.imageView);
        if(profpic!=null)
        {Glide.with(this).load(profpic).into(prof);}
        SharedPreferences mPreferences= getSharedPreferences("tempDatabase",Context.MODE_PRIVATE);
//        //Toast toast = Toast.makeText(this, mPreferences.getString("name","name")
//                        +mPreferences.getString("email","email")+
//                        mPreferences.getString("id","Id")+
//                        mPreferences.getString("adm_no","adm")+
//                        mPreferences.getString("mobile","phone")
//                , Toast.LENGTH_LONG);
//        toast.show();


        maingrid=(GridLayout) findViewById(R.id.maingridlayout);
        setSingleEvent(maingrid);


        GoogleSignInOptions signInOptions= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestServerAuthCode("556889702293-djsueji3g46994afgk9t9jdfe28n1lp9.apps.googleusercontent.com")
                .requestProfile()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, signInOptions);


        mViewFlipper.setFlipInterval(3000);
        mViewFlipper.setInAnimation(this,R.anim.slide_in_left);
        mViewFlipper.setOutAnimation(this,R.anim.slide_out_right);




        mDetector= new GestureDetector(this, new MyGesture());


        ImageView iv=findViewById(R.id.flip1);
        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mDetector.onTouchEvent(motionEvent);
                return true;
            }

        });



    }


    public void fvfv(View view){
        String uri = "https://www.facebook.com/vibhav.iitismdhanbad";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }
    public void webliboxweb(View view){
        String uri = "http://weblibox.com";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);

    }





    public void setSingleEvent(GridLayout maingrid){
        for(int i=0;i<maingrid.getChildCount();i++){
            CardView cardview =(CardView) maingrid.getChildAt(i);
            final int finalI=i;
            cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(finalI==0){
                        Intent intent = new Intent(MainActivity.this,events.class);
                        startActivity(intent);
                    }

                    if(finalI==1){
                        Intent intent = new Intent(MainActivity.this,timetable.class);
                        startActivity(intent);
                    }

                    if(finalI==2){
                        Intent intent = new Intent(MainActivity.this,GuestLec.class);
                        startActivity(intent);
                    }

                    if(finalI==3){
                        Intent intent = new Intent(MainActivity.this,NewsFeed.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }










    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }





    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        }
        else if (id == R.id.nav_contact) {
            startActivity(new Intent(MainActivity.this,contactsUs.class));

        }
        else if(id==R.id.nav_registration){
            startActivity(new Intent(MainActivity.this,PersonProfile.class));

        }
        else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.vibhav.vibhavee.vibhav");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        } else if (id == R.id.nav_developers) {
            startActivity(new Intent(MainActivity.this,developers.class
            ));

        }
        else  if(id==R.id.nav_query){
            startActivity(new Intent(MainActivity.this,FAQs.class));
        }
        else  if(id==R.id.signout){
            signOut();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void signOut() {
//        Auth.GoogleSignInApi.signOut(googleApiClient)
//                .setResultCallback(new ResultCallback<Status>() {
//                    @Override
//                    public void onResult(@NonNull Status status) {
//                        updateUI(false);
//                    }
//                });
//    }
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(MainActivity.this,Splash.class));
                        SharedPrefManager.getInstance(getApplicationContext()).logout();
                    }
                });
        this.finish();
    }
}