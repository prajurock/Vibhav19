package com.vibhav.vibhavee.vibhav;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FAQs extends AppCompatActivity {
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);

        listView=(ExpandableListView)findViewById(R.id.lvExp);
        initData();
        listAdapter=new ExpandableListAdapter(this,listDataHeader,listHash);
        listView.setAdapter(listAdapter);
    }

    private void initData() {

        listDataHeader=new ArrayList<>();
        listHash=new HashMap<>();


        listDataHeader.add("Q: One member from my group is missing. Can I participate in the event?");
        listDataHeader.add("Q: Can I attend the workshop without ant prior knowledge about it?");
        listDataHeader.add("Q: Will I get certificate for any event?");
        listDataHeader.add("Q: Can I get help any time if I have some doubts?");
        listDataHeader.add("Q: What is prerequisites for codeathon?");
        listDataHeader.add("Q: What are requirements to participate in Gaming Event?");
        listDataHeader.add("Q: Can anyone other than electrical Engineering student participate in the TechFest?");
        listDataHeader.add("Q: In how many events one can participate?");


        List<String> qone= new ArrayList<>();
        qone.add("A: Yes,if the particular event has the flexibility in number of participants in a group or if you can arrange someone else in his/her place.");

        List<String> qtwo= new ArrayList<>();
        qtwo.add("A: Yes, workshops are carefully designed to help you learn from the very basic.");

        List<String> qthree= new ArrayList<>();
        qthree.add("A: Yes, winners will be given certificates.");

        List<String> qfour= new ArrayList<>();
        qfour.add("A: Yes, organizers can be contacted any time in case of a doubt arises.");

        List<String> q5= new ArrayList<>();
        q5.add("A: Your interest in the event is the only requirement. Questions are designed keeping FRESHERS in consideration.");

        List<String> q6= new ArrayList<>();
        q6.add("A: Each participants must bring their own Mouse, Headphones and Keyboard(optional). Laptops will be provided on spot.");

        List<String> q7= new ArrayList<>();
        q7.add("A: Yes, any department students can participate in it.");

        List<String> q8= new ArrayList<>();
        q8.add("A: There is no such limitation.");


        listHash.put(listDataHeader.get(0),qone);
        listHash.put(listDataHeader.get(1),qtwo);
        listHash.put(listDataHeader.get(2),qthree);
        listHash.put(listDataHeader.get(3),qfour);
        listHash.put(listDataHeader.get(4),q5);
        listHash.put(listDataHeader.get(5),q6);
        listHash.put(listDataHeader.get(6),q7);
        listHash.put(listDataHeader.get(7),q8);
    }
}
