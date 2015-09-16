package cn.edu.dhu.ssl.mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public abstract class Mail
{
	protected String			subject;									// 主题
	protected String			text;										// 内容
	protected List<Recipient>	recipients	= new ArrayList<Recipient>();	// 收件人
	protected String			charset;									// 字符编码

	public Mail()
	{
	}

	public Message toMessage(Session session) throws MailException
	{
		if (Validator.isEmpty(subject))
		{
			throw new MailException("mail subject can not be empty");
		}
		if (Validator.isEmpty(text))
		{
			throw new MailException("mail text can not be empty");
		}
		if (recipients.isEmpty())
		{
			throw new MailException("mail recipient can not be empty");
		}
		MimeMessage message = null;
		try
		{
			// 创建消息
			message = new MimeMessage(session);
			// 指定主题
			message.setSubject(subject);
			// 指定收件人
			for (Recipient recipient : recipients)
			{
				message.addRecipient(recipient.getType(),
						new InternetAddress(recipient.getEmail()));
			}
			// 指定邮件内容
			setContent(message);
			// 指定邮件发送日期
			message.setSentDate(new Date());
			message.saveChanges();
		}
		catch (Exception e)
		{
			throw new MailException(e.getMessage());
		}
		return message;
	}

	protected abstract void setContent(MimeMessage message)
			throws MailException;

	/****
	 * setters and getters
	 */
	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public void setText(File file) throws MailException
	{
		if (file == null || !file.exists() || !file.canRead())
		{
			throw new MailException("file does not exist or can read");
		}
		try
		{
			setText(new FileReader(file));
		}
		catch (FileNotFoundException e)
		{
			
		}
	}

	public void setText(Reader in) throws MailException
	{
		BufferedReader reader = null;
		try
		{
			reader=new BufferedReader(in);
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line=reader.readLine())!=null)
			{
	            builder.append(line);
			}
			setText(builder.toString());
		}
		catch (Exception e)
		{
            throw new MailException(e.getMessage());
		}
		finally
		{
			if (reader != null)
			{
				try
				{
					reader.close();
				}
				catch (IOException e)
				{

				}
				finally
				{
					reader=null;
				}
			}
		}
		
	}

	public void addRecipient(Recipient recipient)
	{
		recipients.add(recipient);
	}

	public List<Recipient> getRecipients()
	{
		return recipients;
	}

	public void setRecipients(List<Recipient> recipients)
	{
		this.recipients = recipients;
	}

	public String getCharset()
	{
		return charset;
	}

	public void setCharset(String charset)
	{
		this.charset = charset;
	}

}
