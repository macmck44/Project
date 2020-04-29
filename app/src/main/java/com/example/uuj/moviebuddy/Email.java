/*I know how to send an email using an intent, i wanted my app to not have to use an intent and open
an external email app to send the email. The following source was used for the below code
http://www.simplifiedcoding.net/android-email-app-using-javamail-api-in-android-studio/
*/

package com.example.uuj.moviebuddy;

import android.content.Context;
import android.os.AsyncTask;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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

        Properties pr = new Properties();
        pr.put("mail.smtp.host", "smtp.gmail.com");
        pr.put("mail.smtp.socketFactory.port", "465");
        pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        pr.put("mail.smtp.auth", "true");
        pr.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(pr,
                new javax.mail.Authenticator()  {
                    protected PasswordAuthentication thePasswordAuthentication()    {
                        return new PasswordAuthentication(EmailConfig.EMAIL, EmailConfig.PASSWORD);
                    }
                });

        try {

            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(EmailConfig.EMAIL));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);
            Transport.send(mimeMessage);


        }   catch(MessagingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
