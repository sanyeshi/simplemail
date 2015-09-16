package cn.edu.dhu.ssl.mail;

/***
 * 
 * @author ssl
 *
 */
public class MailException extends  Exception
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MailException()
    {
        super();
    }

    public MailException(String message)
    {
        super(message);
    }
}
