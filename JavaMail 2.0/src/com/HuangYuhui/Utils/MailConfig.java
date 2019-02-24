package com.HuangYuhui.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.HuangYuhui.Model.MailConfigInfo;

/**
 *
 * @Project JavaMail 2.0
 * @Package com.HuangYuhui.Utils
 * @Description Configure about the mail information.
 * @Author HuangYuhui
 * @Date Feb 23, 2019-8:43:56 AM
 * @version 2.0
 */
public class MailConfig
{
	public static MailConfigInfo mailConfigInfo = new MailConfigInfo();
	static
	{
		try
		{
			Properties proper = new Properties();
			proper.load(new FileInputStream("mailConfig.properties"));

			mailConfigInfo.setEmailSMTPHost(proper.getProperty("EmailSMTPHost"));
			mailConfigInfo.setEmailRecipient(proper.getProperty("EmailRecipient"));
			mailConfigInfo.setUserMailAccount(proper.getProperty("UserMailAccount"));
			mailConfigInfo.setUserMailPassword(proper.getProperty("UserMailPassword"));
			mailConfigInfo.setEmailSubject(proper.getProperty("EmailSubject"));
			mailConfigInfo.setEmailContent(proper.getProperty("EmailContent"));

		} catch (FileNotFoundException e)
		{
			System.err.println("error : not found the file !");
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}