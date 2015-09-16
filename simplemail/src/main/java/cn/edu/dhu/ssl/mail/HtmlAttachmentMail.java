package cn.edu.dhu.ssl.mail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * @author ssl on Sep 16, 2015
 *
 */
public class HtmlAttachmentMail extends HtmlMail
{
	protected List<File> attachments=new ArrayList<File>();
	
	public HtmlAttachmentMail(String charset)
	{
		super(charset);
	}

	/***
	 * 处理附件型邮件时，需要为邮件体和附件体分别创建BodyPort对象
	 * 然后将其置入MimeMultipart对象中作为一个整体进行发送
	 */
	@Override
	protected void setContent(MimeMessage message) throws MailException
	{
		try
		{
			Multipart multipart=new MimeMultipart();
			//文本
			BodyPart textBodyPart=new MimeBodyPart();
			multipart.addBodyPart(textBodyPart);
			textBodyPart.setContent(text, "text/html;charset=" + charset);
			
			//附件
			for (File attachment : attachments)
			{
				BodyPart fileBodyPart=new MimeBodyPart();
				multipart.addBodyPart(fileBodyPart);
				FileDataSource fds=new FileDataSource(attachment);
				fileBodyPart.setDataHandler(new DataHandler(fds));
				//中文乱码
				String attachmentName=fds.getName();
				fileBodyPart.setFileName(MimeUtility.encodeText(attachmentName));
			}
			//内容
			message.setContent(multipart);
		}
		catch (Exception e)
		{
			new MailException(e.getMessage());
		}	
	}
	
	public void addAttachment(File file) throws FileNotFoundException
	{
		if (file!=null)
		{
		  attachments.add(file);
		}
		else
		{
			throw new FileNotFoundException();
		}
	}
	
}
