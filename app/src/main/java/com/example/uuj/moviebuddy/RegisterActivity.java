/*
Used the following to help with Firebase authentication
https://firebase.google.com/docs/auth/android/custom-auth
*/

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {
    private Button btn_return;
    private Button btn_reg;
    private EditText userName,userEmail,userPassword;
    private FirebaseAuth user_auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = (EditText) findViewById(R.id.editText_Username1);
        userEmail = (EditText) findViewById(R.id.editText_Email1);
        userPassword = (EditText) findViewById(R.id.editText_Password1);
        btn_return = (Button) findViewById(R.id.button_Register1);
        btn_reg = (Button) findViewById(R.id.button_Register);

        user_auth = FirebaseAuth.getInstance();

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                final String username = userName.getText().toString();
                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();

                if( username.isEmpty() || email.isEmpty() || password.isEmpty() )   {
                    Toast.makeText(getApplicationContext(), "Please fill all fields",Toast.LENGTH_LONG).show();
                }
                else    {
                    createUserAccount(username,email,password);
                    openLoginActivity();
                }
            }

        });
    }

    private void createUserAccount(final String username, String email, String password) {
        user_auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Account Created",Toast.LENGTH_LONG).show();
                            updateUserInfo(username, user_auth.getCurrentUser());
                        }
                        else    {
                            Toast.makeText(getApplicationContext(), "Account Creation Failed",Toast.LENGTH_LONG).show();
                        }

                    }
                });

        /*Firebase library implemented in the app gradle
          user_auth is part of the Firebase library to connect to FirebaseAuth and get the instance and current user.
         *createUser... is part of the Firebase library that allows us to register a user in the database.
         *updateUserInfo calls a change request method to assign the values typed and updating the user's profile with these values,
         *by getting the currentUser and updateProfile through Firebase. */

    }

    private void updateUserInfo(String username, FirebaseUser currentUser) {
        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder().setDisplayName(username).build();
        currentUser.updateProfile(profileUpdate);
    }

    public void openLoginActivity() {
        Intent intent = new Intent(this, com.example.uuj.moviebuddy.LoginActivity.class);
        startActivity(intent);
    }
}
