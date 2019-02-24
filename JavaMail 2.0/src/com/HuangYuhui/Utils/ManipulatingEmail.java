package com.HuangYuhui.Utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 
 * @Project JavaMail 2.0
 * @Package com.HuangYuhui.Utils
 * @Description Manipulating email.
 * @Author HuangYuhui
 * @Date Feb 21, 2019-10:40:25 AM
 * @version 2.0
 */
public class ManipulatingEmail
{
	/**
	 * @Title email
	 * @Description Save the email to a local.
	 * @param file name and MimeMessage.
	 * @return void
	 * @date Feb 21, 2019-10:06:51 AM
	 *
	 */
	public static void saveEmail(String filename, MimeMessage message)
	{
		String tip = "\n############################################ Email ############################################\n";
		try
		{
			FileOutputStream fileOutputStream = new FileOutputStream(filename, true);
			fileOutputStream.write("\r\n".getBytes());// getBytes:The resultant byte array.
			fileOutputStream.write(tip.getBytes());
			message.writeTo(fileOutputStream);
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (FileNotFoundException e)
		{
			System.err.println("Error : not found the file !\n");
			e.printStackTrace();
		} catch (IOException e)
		{
			System.err.println("Error : fail to write the email information to the file !\n");
			e.printStackTrace();
		} catch (MessagingException e)
		{
			e.printStackTrace();
		}
	}
}
