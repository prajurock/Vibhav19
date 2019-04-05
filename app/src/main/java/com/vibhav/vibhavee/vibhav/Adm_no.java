package com.vibhav.vibhavee.vibhav;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Adm_no extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.activity_adm_no, null))
                // Add action buttons
                .setPositiveButton("sign in", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Adm_no.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_adm_no);
//        TextView adm_no= (TextView)findViewById(R.id.adm_no);
//        TextView phone= (TextView)findViewById(R.id.mobile);
//        Toast toast = Toast.makeText(getApplicationContext(), adm_no+" "+phone, Toast.LENGTH_LONG);
//        toast.show();
//    }
//    @Override
//    public void onClick(View view) {
//        switch (view.getId())
//        {
//            case R.id.button2:
//                TextView adm_no= (TextView)findViewById(R.id.adm_no);
//                TextView phone= (TextView)findViewById(R.id.mobile);
//                Toast toast = Toast.makeText(getApplicationContext(), adm_no+" "+phone, Toast.LENGTH_LONG);
//                break;
//        }
//
//    }
}
