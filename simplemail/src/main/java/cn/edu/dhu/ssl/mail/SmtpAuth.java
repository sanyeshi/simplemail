package cn.edu.dhu.ssl.mail;


import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


/**
 * Created by ssl on 9/15/15.
 */
public class SmtpAuth extends Authenticator
{
    String username;
    String password;

    public SmtpAuth(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(username,password);
    }
}
