/* Used the following to help with Firebase authentication
https://firebase.google.com/docs/auth/android/custom-auth */

package com.example.uuj.moviebuddy;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private Button btn_reg;
    private Button btn_login;
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
    }

    private void login(String email, String password) {
        user_auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if( task.isSuccessful() )   {
                    Toast.makeText(getApplicationContext(), "Login successful",Toast.LENGTH_LONG).show();
                    openHomeActivity();
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
