package com.vibhav.vibhavee.vibhav;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class developers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView imageView=(ImageView)findViewById(R.id.titleImage);
        imageView.setImageResource(R.drawable.develop);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void mssgit(View view){
        String uri = "https://github.com/MadhavShah";
         Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);

    }

    public void mssfb(View view){
        String uri = "https://www.facebook.com/madhavshahsisodiya";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);

    }

    public void argit(View view){
        String uri = "https://github.com/raj21adittya";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);

    }

    public void arfb(View view){
        String uri = "https://www.facebook.com/adittya.raj.165";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);

    }

    public void gpgit(View view){
        String uri = "https://github.com/gauravpandey1998";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);

    }

    public void gpfb(View view){
        String uri = "https://www.facebook.com/profile.php?id=100017292073841";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);

    }

    public void pkgit(View view){
        String uri = "https://github.com/pro-30";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);

    }

    public void pkfb(View view){
        String uri = "https://www.facebook.com/prashant.kr.7140";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);

    }

    public void vbgit(View view){
        String uri = "https://github.com/vishalbh";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);

    }

    public void vbfb(View view){
        String uri = "https://www.facebook.com/vishal.bharti.908";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);

    }

    public void ssgit(View view){
        String uri = "https://github.com/shikhar8";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);

    }

    public void ssfb(View view){
        String uri = "https://www.facebook.com/profile.php?id=100017360448867";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);

    }
}
