package cn.edu.dhu.ssl.mail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

/**
 * 
 * @author ssl on Sep 16, 2015
 *
 */
public class MailContext
{
	private Properties prop;
	public MailContext()
	{
		try
		{
			prop = new Properties();
			prop.load(this.getClass().getClassLoader().getResourceAsStream("mail.properties"));
		}
		catch (IOException e)
		{
		
		}
	}

	private void send(Mail mail) throws MailException
	{
		if (prop==null)
		{
			throw new MailException("mail.properties does not exist or can not load in classpath");
		}
		String host=prop.getProperty("mail.smtp.host");
		String user=prop.getProperty("mail.smtp.user");
		String password=prop.getProperty("mail.smtp.password");
		String from=prop.getProperty("mail.from");
		String fromAlias=prop.getProperty("mail.from.alias");
		String debug=prop.getProperty("mail.debug");
		boolean isDebug=false;
		
		if (Validator.isEmpty(host))
		{
			throw new MailException("smtp host can not be empty");
		}
		if (Validator.isEmpty(user) ||Validator.isEmpty(password))
		{
			throw new MailException(
					"smtp host need to be authenticated,username or password can not be empty");
		}
		if (Validator.isEmpty(from))
		{
			throw new MailException("from can not be empty");
		}
		if (mail == null)
		{
			throw new MailException("mail can not be null");
		}
		if (debug!=null&&"true".equals(debug.toLowerCase().trim()))
		{
			isDebug=true;
		}
		try
		{
			
			SmtpAuth auth = new SmtpAuth(user, password);
			// session
			Session session = Session.getDefaultInstance(prop, auth);
		    session.setDebug(isDebug);
			// message
			Message message = mail.toMessage(session);
			// form
			if (Validator.isEmpty(fromAlias))
			{
				message.setFrom(new InternetAddress(from));
			}
			else
			{
				message.setFrom(new InternetAddress(from, fromAlias));
			}
			// transport
			Transport transport = session.getTransport("smtp");
			transport.connect(host, user, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		}
		catch (Exception e)
		{
			throw new MailException(e.getMessage());
		}
	}
	
	
	public void sendSyn(Mail mail) throws MailException
	{
		send(mail);
	}
	public void sendAsyn(Mail mail) throws MailException
	{
	   send(mail);	
	}
}
