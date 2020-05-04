/*
Bottom navigation view sourced from here:
https://developer.android.com/guide/navigation/navigation-ui#java
https://code.tutsplus.com/tutorials/how-to-code-a-bottom-navigation-bar-for-an-android-app--cms-30305

Displaying username and email address sourced from here:
https://firebase.google.com/docs/auth/android/custom-auth

Sending email sourced from here:
http://www.simplifiedcoding.net/android-email-app-using-javamail-api-in-android-studio/

Paypal SDK sourced from here:
https://developer.paypal.com/docs/wlw/android-sdk/
http://technxt.net/how-to-integrate-paypal-payment-in-android-app-using-android-studio/

Notifications sourced from here:
https://developer.android.com/training/notify-user/build-notification#java
*/

package com.example.uuj.moviebuddy;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.NotificationCompat;
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
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

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
    Button btn_donate;
    EditText et_donate;
    String amount = "";

    @Override
    protected void onDestroy()  {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    private static final int PAYPAL_REQUEST_CODE = 7171;
    private static PayPalConfiguration paypalconfig = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PaypalConfig.PAYPAL_CLIENT_ID);

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
        btn_donate = (Button) findViewById(R.id.button_options1_1);
        et_donate = (EditText) findViewById(R.id.donateamount);

        /*Firebase library implemented in the app gradle
          user_auth is part of the Firebase library to connect to FirebaseAuth and get the instance and current user.
         *textViews displaying username and email address by using the FirebaseAuth.getInstance().getCurrentUser() method, part of Firebase library */

        BottomNavigationView bottomNavView = (BottomNavigationView) findViewById(R.id.bottomNavView);
        Menu menu = bottomNavView.getMenu();
        MenuItem menuitem = menu.getItem(1);
        menuitem.setChecked(true);

        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalconfig);
        startService(intent);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Toast.makeText(getApplicationContext(), "Logout successful",Toast.LENGTH_LONG).show();
                    user_auth.signOut();
                    finish();
                    openLoginActivity();

                NotificationCompat.Builder nbuilder = new NotificationCompat.Builder(
                        AccountActivity.this);
                nbuilder.setSmallIcon(R.drawable.ic_notification);
                nbuilder.setContentTitle("MovieBuddy");
                nbuilder.setContentText("Logout successful");
                nbuilder.setAutoCancel(true);

                Intent intent = new Intent(AccountActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(AccountActivity.this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
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
        });

        btn_send.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                sendEmail();

                NotificationCompat.Builder nbuilder = new NotificationCompat.Builder(
                        AccountActivity.this);
                nbuilder.setSmallIcon(R.drawable.ic_notification);
                nbuilder.setContentTitle("MovieBuddy");
                nbuilder.setContentText("Thanks for your email! We will look into your issue ASAP.");
                nbuilder.setAutoCancel(true);

                Intent intent = new Intent(AccountActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(AccountActivity.this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
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
        });

        btn_donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processPayPalPayment();
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

    private void processPayPalPayment() {
        amount = et_donate.getText().toString();
        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(amount)),
                "GBP", "Donate", PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalconfig);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)   {
        if(requestCode == PAYPAL_REQUEST_CODE)  {
            if(resultCode == RESULT_OK) {
                PaymentConfirmation paymentConfirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if(paymentConfirmation != null) {
                    try {
                        String paymentDetails = paymentConfirmation.toJSONObject().toString(4);
                        startActivity(new Intent(this, com.example.uuj.moviebuddy.PaymentDetails.class)
                                .putExtra("PaymentDetails", paymentDetails)
                                .putExtra("PaymentAmount", amount));
                    } catch(JSONException e) {
                        e.printStackTrace();
                    }
                }
            } else  {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        } else  {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
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
