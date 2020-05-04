/*
Paypal API sourced from here:
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
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Owner on 29/04/2020.
 */

public class PaymentDetails extends AppCompatActivity {

    TextView tv_id;
    TextView tv_amount;
    TextView tv_status;
    Button btn_return;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        tv_id = (TextView) findViewById(R.id.textid);
        tv_amount = (TextView) findViewById(R.id.textamount);
        tv_status = (TextView) findViewById(R.id.textstatus);
        btn_return = (Button) findViewById(R.id.returnbtn);

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccountActivity();

                NotificationCompat.Builder nbuilder = new NotificationCompat.Builder(
                        PaymentDetails.this);
                nbuilder.setSmallIcon(R.drawable.ic_notification);
                nbuilder.setContentTitle("MovieBuddy");
                nbuilder.setContentText("Thanks for your donation!");
                nbuilder.setAutoCancel(true);

                Intent intent = new Intent(PaymentDetails.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(PaymentDetails.this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
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

        Intent intent = getIntent();

        try {
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("PaymentDetails"));
            showPayment(jsonObject.getJSONObject("response"), intent.getStringExtra("PaymentAmount"));

        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void openAccountActivity() {
        Intent intent = new Intent(this, com.example.uuj.moviebuddy.AccountActivity.class);
        startActivity(intent);
    }

    private void showPayment(JSONObject response, String paymentAmount) {
        try {
            tv_id.setText(response.getString("id"));
            tv_amount.setText(response.getString("state"));
            tv_status.setText("£" + paymentAmount);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
