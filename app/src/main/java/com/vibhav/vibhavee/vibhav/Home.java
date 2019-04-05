package com.vibhav.vibhavee.vibhav;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
public class Home extends AppCompatActivity {
    private LinearLayout log_Section;
    private Button SignOut;
    private TextView Name,Email;
    private GoogleSignInClient mGoogleSignInClient;
    //private GoogleSignInCli

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        log_Section=(LinearLayout)findViewById(R.id.log_section);
        SignOut= (Button)findViewById(R.id.logout);
        Name= (TextView)findViewById(R.id.name);
        Email= (TextView)findViewById(R.id.email);
        log_Section.setVisibility(View.GONE);
        log_Section.setVisibility(View.VISIBLE);

        // 1. get passed intent
        Intent intent = getIntent();
        // 2. get message value from intent
        //String message = intent.getStringExtra("message");
        // 3. show message on textView
        //((TextView)findViewById(R.id.tvMessage)).setText(message);
        // 4. get bundle from intent
        Bundle bundle = intent.getExtras();
        GoogleSignInOptions signInOptions= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        //googleApiClient= new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, signInOptions);

        // 5. get status value from bundle
        String name = bundle.getString("Name");
        String email = bundle.getString("Email");
        Name.setText(name);
        Email.setText(email);

        // 6. show status on Toast
        Toast toast = Toast.makeText(this, name, Toast.LENGTH_LONG);
        toast.show();
        SignOut = (Button) findViewById(R.id.logout);
        SignOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Toast toast = Toast.makeText(getApplicationContext(), "view", Toast.LENGTH_LONG);
                toast.show();
                signOut();
            }
        });
        dialog();
//        Intent intent1 = new Intent(this, Adm_no.class);
//        startActivity(intent1);

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
        //Toast toast = Toast.makeText(getApplicationContext(), "logout () init", Toast.LENGTH_LONG);
        //toast.show();


//        mGoogleSignInClient.signOut()
//                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        //Toast toast = Toast.makeText(getApplicationContext(), "Logout exp", Toast.LENGTH_LONG);
//                        //toast.show();
//                    }
//                });


        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        // 2. put key/value data
        //intent.putExtra("Signout", "true");

        // 3. or you can add data to a bundle
        Bundle extras = new Bundle();
        extras.putString("Signout", "true");
//        extras.putString("Email", email);

        // 4. add bundle to intent
        //intent.putExtras(extras);
        //startActivity(intent);

    }

    private void dialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        final LayoutInflater inflater = this.getLayoutInflater();
        final View view= this.getLayoutInflater().inflate(R.layout.activity_adm_no, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout

        final TextView adm_no= (TextView)view.findViewById(R.id.adm_no);
        final TextView phone= (TextView)view.findViewById(R.id.mobile);
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("signin", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                        String text= adm_no.getText().toString();
                        String mobile= phone.getText().toString();
                        Toast toast = Toast.makeText(getApplicationContext(), text+" "+mobile, Toast.LENGTH_LONG);
                        toast.show();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //LoginDialogFragment.this.getDialog().cancel();
                    }
                }).show();
    }

}

//public class FireMissilesDialogFragment extends DialogFragment {
//
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        // Use the Builder class for convenient dialog construction
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setMessage("Dialog Box")
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // FIRE ZE MISSILES!
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // User cancelled the dialog
//                    }
//                });
//        // Create the AlertDialog object and return it
//        return builder.create();
//    }
//}
