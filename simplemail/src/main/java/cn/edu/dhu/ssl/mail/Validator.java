package cn.edu.dhu.ssl.mail;

public class Validator
{
    public static boolean isEmpty(String str)
    {
    	if (str==null||str.length()==0)
		{
			return true;
		}
    	return false;
    }
}
