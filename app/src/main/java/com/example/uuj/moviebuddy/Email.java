package com.example.uuj.moviebuddy;

import android.content.Context;
import android.os.AsyncTask;
import javax.mail.Session;

/**
 * Created by Owner on 27/04/2020.
 */

public class Email extends AsyncTask<Void, Void, Void>{

    private Context context;
    private Session session;
    private String email;
    private String subject;
    private String message;



    public Email(Context context, String email, String subject, String message)  {

        this.context = context;
        this.email = email;
        this.subject = subject;
        this.message = message;

    }

    public Void doInBackground(Void... params)  {



        return null;
    }
}
