package com.vibhav.vibhavee.vibhav;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//import com.vibhav.vibhavee.vibhav.registation.registrationPOC;

public class poc extends AppCompatActivity {
    private static Button about;
    String bucks_token ,event="technovation";

    private   AlertDialog.Builder about_builder;
    private   AlertDialog.Builder rules_builder;
    private   AlertDialog.Builder judgingCriteria_builder;
    private   AlertDialog.Builder prizes_builder;
    private   AlertDialog.Builder contactUs_builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_fever);
        next();
        ImageView imageView=(ImageView)findViewById(R.id.titleImage);
        imageView.setImageResource(R.drawable.poctemp);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




    }
    public void next(){
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

    }

    public void about(View view){
        about_builder= new AlertDialog.Builder(poc.this);
        about_builder.setMessage(R.string.aboutt1);
        AlertDialog alert_about = about_builder.create();
        alert_about.setTitle("About");
        alert_about.show();

    }
    public void rules(View view){
        rules_builder= new AlertDialog.Builder(poc.this);
        rules_builder.setMessage(R.string.rulest1);
        AlertDialog alert_about = rules_builder.create();
        alert_about.setTitle("Rules");
        alert_about.show();

    }
    public void judgingCriteria(View view){
        judgingCriteria_builder= new AlertDialog.Builder(poc.this);
        judgingCriteria_builder.setMessage(R.string.judgingCriteriat1);
        AlertDialog alert_about = judgingCriteria_builder.create();
        alert_about.setTitle("Judging Criteria");
        alert_about.show();

    }
    public void prizes(View view){
        prizes_builder= new AlertDialog.Builder(poc.this);
        prizes_builder.setMessage(R.string.prizest1);
        AlertDialog alert_about = prizes_builder.create();
        alert_about.setTitle("Prizes");
        alert_about.show();

    }
    public void contactUs(View view){
        contactUs_builder= new AlertDialog.Builder(poc.this);
        contactUs_builder.setMessage(R.string.contactUst1);
        AlertDialog alert_about = contactUs_builder.create();
        alert_about.setTitle("Contact Us");
        alert_about.show();

    }

}

//public void onButtonClickListener(){
//    about =(Button)findViewById(R.id.about);
//   about.setOnClickListener(
//  new View.OnClickListener(){
//@Override
//public void onClick(View v){
// about_builder= new AlertDialog.Builder(placement_fever.this);
// about_builder.setMessage("ftf");
// AlertDialog alert_about = about_builder.create();
// alert_about.setTitle("About");
// alert_about.show();
//  }

//  }
//  );