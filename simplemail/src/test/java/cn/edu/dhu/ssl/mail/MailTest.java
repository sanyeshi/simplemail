package cn.edu.dhu.ssl.mail;

import java.io.File;

import javax.mail.Message;

import org.junit.Before;
import org.junit.Test;

/***
 * 
 * @author ssl on Sep 16, 2015
 *
 */
public class MailTest
{
	private MailContext context;

	@Before
	public void setup()
	{
		context = new MailContext();
	}

//	@Test
//	public void textMailTest()
//	{
//		try
//		{
//			TextMail mail = new TextMail();
//			mail.addRecipient(new Recipient("1325166099@qq.com",
//					Message.RecipientType.TO));
//			mail.setSubject("相关事宜");
//			mail.setText("你好");
//			context.send(mail);
//		}
//		catch (MailException e)
//		{
//			e.printStackTrace();
//		}
//	}

	@Test
	public void htmlMailTest()
	{
		try
		{
			HtmlMail mail = new HtmlMail("UTF-8");
			mail.addRecipient(new Recipient("1325166099@qq.com",
					Message.RecipientType.TO));
			mail.setSubject("相关事宜");
			mail.setText(new File("template.html"));
			mail.setCharset("utf-8");
			context.sendSyn(mail);
		}
		catch (MailException e)
		{
			e.printStackTrace();
		}
	}

//	@Test
//	public void htmlAttachmentMailTest()
//	{
//		try
//		{
//			HtmlAttachmentMail mail = new HtmlAttachmentMail("UTF-8");
//			mail.addRecipient(new Recipient("1325166099@qq.com",
//					Message.RecipientType.TO));
//			mail.setSubject("相关事宜");
//			mail.setText("你好");
//			mail.addAttachment(new File("/Users/ssl/Pictures/中国.png"));
//			mail.addAttachment(new File("/Users/ssl/Pictures/02.png"));
//			context.send(mail);
//		}
//		catch (MailException e)
//		{
//			e.printStackTrace();
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
//
//	public void destroy()
//	{
//		context = null;
//	}

}
