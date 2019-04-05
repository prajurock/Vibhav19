package com.vibhav.vibhavee.vibhav;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class events extends AppCompatActivity implements AdapterView.OnItemClickListener {
    GridView myGrid;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        myGrid = (GridView) findViewById(R.id.gridview);
        myGrid.setAdapter(new VivzAdapter(events.this));
        myGrid.setOnItemClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }




    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (i==0) {
            Intent intent = new Intent(this, technical.class);
            startActivity(intent);
        }
        else if (i==1) {
            Intent intent = new Intent(this,codeathon.class);
            startActivity(intent);
        }
        else if (i==2) {
            Intent intent = new Intent(this, Online.class);
            startActivity(intent);
        }
        else if(i==3){
            Intent intent = new Intent(this, Robo.class);
            startActivity(intent);
        }
        else if(i==4){
            Intent intent = new Intent(this,placement_fever.class);
            startActivity(intent);
        }
        else if(i==5){
            Intent intent = new Intent(this,Games.class);
            startActivity(intent);
        }

        else if(i==6){
            Intent intent = new Intent(this,Paper.class);
            startActivity(intent);
        }
        else if(i==7){
            Intent intent = new Intent(this, Treasure.class);
            startActivity(intent);
        }
        else if(i==8){
            Intent intent=new Intent(this,Guest.class);
            startActivity(intent);
        }
        else if(i==9){
            Intent intent=new Intent(this,Sherlocked.class);
            startActivity(intent);
        }

    }
}
class VivzAdapter extends BaseAdapter {
    ArrayList<events1> list;
    Context context;

    VivzAdapter(Context context) {
        this.context = context;
        list = new ArrayList<events1>();
        Resources res = context.getResources();
        String[] temp = res.getStringArray(R.array.fest);
        int[] image = {R.drawable.technicals, R.drawable.codings, R.drawable.bullstock, R.drawable.robotic, R.drawable.placementfevers,R.drawable.gaming,R.drawable.paperpresentation,R.drawable.tresurehunts,R.drawable.guest,R.drawable.sherlocked};
        for (int ii = 0; ii < 10; ii++) {
            events1 tempevent = new events1(image[ii], temp[ii]);
            list.add(tempevent);
        }


    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class viewHolder {
        ImageView iv;
        TextView tv;

        viewHolder(View v) {
            iv = (ImageView) v.findViewById(R.id.imageView);
            tv = (TextView) v.findViewById(R.id.textView);

        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        viewHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.singlegrid, viewGroup, false);
            holder = new viewHolder(row);
            row.setTag(holder);
        } else {
            holder = (viewHolder) row.getTag();
        }
        events1 dummy = list.get(i);
        holder.iv.setImageResource(dummy.imageid);
        holder.tv.setText(dummy.eventName);

        return row;
    }
}

class events1 {
    int imageid;
    String eventName;

    events1(int imageid, String eventName) {
        this.imageid = imageid;
        this.eventName = eventName;
    }

}