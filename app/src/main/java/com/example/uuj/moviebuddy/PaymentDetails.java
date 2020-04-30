package com.example.uuj.moviebuddy;

import android.content.Intent;
import android.os.Bundle;
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
