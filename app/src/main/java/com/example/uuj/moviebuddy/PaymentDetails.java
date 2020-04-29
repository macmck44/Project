package com.example.uuj.moviebuddy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Owner on 29/04/2020.
 */

public class PaymentDetails extends AppCompatActivity {

    TextView tv_id;
    TextView tv_amount;
    TextView tv_status;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        tv_id = (TextView) findViewById(R.id.textid);
        tv_amount = (TextView) findViewById(R.id.textamount);
        tv_status = (TextView) findViewById(R.id.textstatus);
    }
}
