package cn.edu.dhu.ssl.mail;

import javax.mail.internet.MimeMessage;

public class HtmlMail extends TextMail
{

	public HtmlMail(String charset)
	{
	     super.charset=charset;
	}
	
	@Override
	protected void setContent(MimeMessage message) throws MailException
	{
		try
		{
			message.setContent(text, "text/html;charset=" + charset);
		}
		catch (Exception e)
		{
			throw new MailException(e.getMessage());
		}
	}
	
	
   
}
