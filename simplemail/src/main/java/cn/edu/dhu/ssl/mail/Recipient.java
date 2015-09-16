package cn.edu.dhu.ssl.mail;

import javax.mail.Message.RecipientType;

public class Recipient
{
	private String			email;
	private RecipientType	type;

	public Recipient()
	{
		
	}
	
	public Recipient(String email, RecipientType type)
	{
		super();
		this.email = email;
		this.type = type;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public RecipientType getType()
	{
		return type;
	}

	public void setType(RecipientType type)
	{
		this.type = type;
	}

}
