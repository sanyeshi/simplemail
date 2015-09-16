package cn.edu.dhu.ssl.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class TextMail extends Mail
{
	
	@Override
	 protected void setContent(MimeMessage message) throws MailException
	 {
	    	try
			{
	    		if (Validator.isEmpty(charset))
	    		{
	    			message.setText(text);
	    		}
	    		else
	    		{
	    			message.setContent(text, "text/plain;charset=" + charset);
	    		}
			}
			catch (MessagingException e)
			{
				throw new MailException(e.getMessage());
			}
		}
}
