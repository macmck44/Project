/* Used the following to help with Firebase authentication
https://firebase.google.com/docs/auth/android/custom-auth */

package com.example.uuj.moviebuddy;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private Button btn_reg;
    private Button btn_login;
    private Button btn_recover;
    private EditText loginEmail, loginPassword;
    private FirebaseAuth user_auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = (EditText) findViewById(R.id.editText_Email);
        loginPassword = (EditText) findViewById(R.id.editText_Password);
        btn_reg = (Button) findViewById(R.id.button_Login1);
        btn_login = (Button) findViewById(R.id.button_Login);
        btn_recover = (Button) findViewById(R.id.button_recover);

        user_auth = FirebaseAuth.getInstance();

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = loginEmail.getText().toString();
                final String password = loginPassword.getText().toString();

                if( email.isEmpty() || password.isEmpty() ) {
                    Toast.makeText(getApplicationContext(), "Please fill all fields",Toast.LENGTH_LONG).show();
                }
                else    {
                    login(email,password);
                }

            }
        });

        btn_recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecoverOption();
            }
        });
    }

    private void openRecoverOption() {
        AlertDialog.Builder abuilder = new AlertDialog.Builder(this);
        LinearLayout llayout = new LinearLayout(this);
        abuilder.setTitle("Recover Password");
        final EditText etEmail = new EditText(this);
        etEmail.setHint("Email Address");
        llayout.addView(etEmail);
        abuilder.setView(llayout);

        abuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        abuilder.setPositiveButton("Recover", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String emailaddress = etEmail.getText().toString().trim();
                startRecover(emailaddress);
            }
        });

        abuilder.create().show();
    }

    private void startRecover(String emailaddress) {
        user_auth.sendPasswordResetEmail(emailaddress);
        Toast.makeText(this, "Email sent", Toast.LENGTH_SHORT).show();
    }

    private void login(String email, String password) {
        user_auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if( task.isSuccessful() )   {
                    Toast.makeText(getApplicationContext(), "Login successful",Toast.LENGTH_LONG).show();
                    openHomeActivity();

                    NotificationCompat.Builder nbuilder = new NotificationCompat.Builder(
                            LoginActivity.this);
                    nbuilder.setSmallIcon(R.drawable.ic_notification);
                    nbuilder.setContentTitle("MovieBuddy");
                    nbuilder.setContentText("Login successful");
                    nbuilder.setAutoCancel(true);

                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    PendingIntent pendingIntent = PendingIntent.getActivity(LoginActivity.this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
                    nbuilder.setContentIntent(pendingIntent);

                    NotificationManager nmanager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

                /* Notifications changed with API26+ and need a channelId to be added to work. Used this source for help.
                   https://stackoverflow.com/questions/16045722/android-notification-is-not-showing */

                    String channelId = "Notification";
                    NotificationChannel nchannel = new NotificationChannel(channelId, "Channel Title", NotificationManager.IMPORTANCE_HIGH);
                    nmanager.createNotificationChannel(nchannel);
                    nbuilder.setChannelId(channelId);

                    nmanager.notify(0, nbuilder.build());
                }

                else    {
                    Toast.makeText(getApplicationContext(), "Login Failed",Toast.LENGTH_LONG).show();
                }

            }
        });

        /*Firebase library implemented in the app gradle
          user_auth is part of the Firebase library to connect to FirebaseAuth and get the instance and current user.
         *login method just takes the email and password we just created in the register page and calls the
         *signInWith... method to sign in to the app */

    }

    public void openRegisterActivity() {
        Intent intent1 = new Intent(this, com.example.uuj.moviebuddy.RegisterActivity.class);
        startActivity(intent1);
    }

    public void openHomeActivity() {
        Intent intent = new Intent(this, com.example.uuj.moviebuddy.HomeActivity.class);
        startActivity(intent);
    }
}
