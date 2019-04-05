package com.vibhav.vibhavee.vibhav;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class PersonProfile extends AppCompatActivity {
    private   AlertDialog.Builder Dialog1, Dialog2;
    private String events[]={"sherlocked", "placement_fever",
    "auction_villa",
    "robo_soccer",
    "codee",
    "guest_lecture",
    "pubg",
    "marketing_roadies",
    "aaviskar",
    "laser_maze",
    "buffet_money",
    "technovation",
    "cs_go",
    "treasure_hunt"};
    User user;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user=SharedPrefManager.getInstance(getApplicationContext()).getUser();
        setContentView(R.layout.activity_person_profile);
        TextView text1=(TextView)findViewById(R.id.personname);
        TextView email= findViewById(R.id.personemail);
        TextView text2=(TextView)findViewById(R.id.personid);
        ImageView Prof_pic=(ImageView)findViewById(R.id.profilepic);
        text1.setText(user.getUsername());
        TextView coins=(TextView)findViewById(R.id.personwallet);
        Integer s=SharedPrefManager.getInstance(getApplicationContext()).getCoins();
        coins.setText(s.toString());
        //Toast.makeText(getApplicationContext(), "TOKEn "+ user.getAdm_no(), Toast.LENGTH_LONG).show();
        text2.setText(user.getAdm_no());
        String pic=SharedPrefManager.getInstance(getApplicationContext()).getKeyPic();
        email.setText(user.getEmail());
        if(pic!=null){
        Glide.with(this).load(pic).into(Prof_pic);}
        GoogleSignInOptions signInOptions= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, signInOptions);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void openregisterdevents(View view){
        Dialog1= new AlertDialog.Builder(this);
        String message = "";
        for(int i=0;i<13;i++){
            if(!SharedPrefManager.getInstance(getApplicationContext()).getEventToken(events[i]).equals("0")){
//                message.concat(events[i]+"\n");
                message=message+events[i]+"\n";
            }
        }
        Dialog1.setMessage(message);
//        Dialog1.
        AlertDialog alert_about = Dialog1.create();
        alert_about.setTitle("Events Registered");
        alert_about.show();

    }
    public void openbucksinfo(View view){
        Dialog1= new AlertDialog.Builder(this);
        String message = "";
        for(int i=0;i<13;i++){
            if(SharedPrefManager.getInstance(getApplicationContext()).getEventToken(events[i]).equals("1")){
//                message.concat(events[i]+"\n");
                message=message+events[i]+"\n";
            }
        }
        Dialog1.setMessage(message);
//        Dialog1.
        AlertDialog alert_about = Dialog1.create();
        alert_about.setTitle("Bucks Info");
        alert_about.show();

    }
    public void signout(View view){
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        SharedPrefManager.getInstance(getApplicationContext()).logout();
                        startActivity(new Intent(PersonProfile.this,Splash.class));
                    }
                });
        this.finish();

    }

//    public void (View view){
////        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
////        startActivity(intent);
//
//    }
}
