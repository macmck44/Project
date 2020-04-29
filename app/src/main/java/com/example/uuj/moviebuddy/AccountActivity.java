/*Developer documentation sources below used for bottom navigation view
https://developer.android.com/guide/navigation/navigation-ui#java
https://code.tutsplus.com/tutorials/how-to-code-a-bottom-navigation-bar-for-an-android-app--cms-30305

Displaying username and email address sourced from here:
https://firebase.google.com/docs/auth/android/custom-auth

Sending email sourced from here:
http://www.simplifiedcoding.net/android-email-app-using-javamail-api-in-android-studio/
*/

package com.example.uuj.moviebuddy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class AccountActivity extends AppCompatActivity{

    FirebaseUser current_user;
    FirebaseAuth user_auth;
    TextView tv_username;
    TextView tv_email;
    Button btn_logout;
    TextView tv_emailaddress;
    EditText et_subject;
    EditText et_message;
    Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        user_auth = FirebaseAuth.getInstance();
        current_user = user_auth.getCurrentUser();

        tv_username = (TextView) findViewById(R.id.TextView_username);
        tv_email = (TextView) findViewById(R.id.TextView_email);
        tv_username.setText(current_user.getDisplayName());
        tv_email.setText(current_user.getEmail());
        btn_logout = (Button) findViewById(R.id.button_logout);
        tv_emailaddress = (TextView) findViewById(R.id.emailaddress1);
        et_subject = (EditText) findViewById(R.id.subject1);
        et_message = (EditText) findViewById(R.id.message1);
        btn_send = (Button) findViewById(R.id.button_send);

        /*Firebase library implemented in the app gradle
          user_auth is part of the Firebase library to connect to FirebaseAuth and get the instance and current user.
         *textViews displaying username and email address by using the FirebaseAuth.getInstance().getCurrentUser() method, part of Firebase library */

        BottomNavigationView bottomNavView = (BottomNavigationView) findViewById(R.id.bottomNavView);
        Menu menu = bottomNavView.getMenu();
        MenuItem menuitem = menu.getItem(1);
        menuitem.setChecked(true);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Toast.makeText(getApplicationContext(), "Logout successful",Toast.LENGTH_LONG).show();
                    user_auth.signOut();
                    finish();
                    openLoginActivity();

            }
        });

        btn_send.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });


        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())   {

                    case R.id.ic_home:
                        Intent intent = new Intent(AccountActivity.this, com.example.uuj.moviebuddy.HomeActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.ic_account:
                        break;

                    case R.id.ic_news:
                        Intent intent1 = new Intent(AccountActivity.this, com.example.uuj.moviebuddy.NewsActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_rec:
                        Intent intent2 = new Intent(AccountActivity.this, com.example.uuj.moviebuddy.RecActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_maps:
                        Intent intent3 = new Intent(AccountActivity.this, com.example.uuj.moviebuddy.MapsActivity.class);
                        startActivity(intent3);
                        break;

                }

                return false;
            }
        });

    }

    public void openLoginActivity() {
        Intent intent = new Intent(this, com.example.uuj.moviebuddy.LoginActivity.class);
        startActivity(intent);
    }

    public void sendEmail() {

        String email = tv_emailaddress.getText().toString().trim();
        String subject = et_subject.getText().toString().trim();
        String message = et_message.getText().toString().trim();

        Email theEmail = new Email(this, email, subject, message);
        theEmail.execute();
    }
}
