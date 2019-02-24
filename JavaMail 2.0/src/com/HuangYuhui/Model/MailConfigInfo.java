package com.HuangYuhui.Model;

/**
 * 
 * @Project JavaMail 2.0
 * @Package com.HuangYuhui.Model
 * @Description Store the information about the configuration for mail.
 * @Author HuangYuhui
 * @Date Feb 23, 2019-9:13:31 AM
 * @version 2.0
 */
public class MailConfigInfo
{
	private String EmailSMTPHost;
	private String UserMailAccount;
	private String UserMailPassword;
	private String EmailFrom;
	private String EmailRecipient;
	private String EmailSubject;
	private String EmailContent;

	public String getEmailContent()
	{
		return EmailContent;
	}

	public void setEmailContent(String emailContent)
	{
		EmailContent = emailContent;
	}

	public String getEmailSMTPHost()
	{
		return EmailSMTPHost;
	}

	public void setEmailSMTPHost(String emailSMTPHost)
	{
		EmailSMTPHost = emailSMTPHost;
	}

	public String getUserMailAccount()
	{
		return UserMailAccount;
	}

	public void setUserMailAccount(String userMailAccount)
	{
		UserMailAccount = userMailAccount;
	}

	public String getUserMailPassword()
	{
		return UserMailPassword;
	}

	public void setUserMailPassword(String userMailPassword)
	{
		UserMailPassword = userMailPassword;
	}

	public String getEmailFrom()
	{
		return EmailFrom;
	}

	public void setEmailFrom(String emailFrom)
	{
		EmailFrom = emailFrom;
	}

	public String getEmailRecipient()
	{
		return EmailRecipient;
	}

	public void setEmailRecipient(String emailRecipient)
	{
		EmailRecipient = emailRecipient;
	}

	public String getEmailSubject()
	{
		return EmailSubject;
	}

	public void setEmailSubject(String emailSubject)
	{
		EmailSubject = emailSubject;
	}
}
