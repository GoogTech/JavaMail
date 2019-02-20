package com.HaungYuhui.Main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @Project JavaMail 1.0
 * @Package com.HaungYuhui.Main
 * @Description (Demo)Basic steps.
 * @Author HuangYuhui
 * @Date Feb 20, 2019-1:32:52 PM
 * @version 1.0
 */
public class MainDemo	
{
	public static void main(String[] args) throws MessagingException, IOException
	{
		/*
		 * 1: Create a email file.
		 */
		Properties properties = new Properties();

		// The paramter due to connect to the server.
		Session session = Session.getInstance(properties);

		// Create the object of email.
		MimeMessage message = new MimeMessage(session);

		/*
		 * You can create the MineMessage object by the 'file.eml' also.
		 * 
		 * MineMessage message = new MineMessage(session,new
		 * FileInputStream("MyEmail.eml"));
		 */

		/*
		 * 2: From addresser
		 */
		message.setFrom(new InternetAddress("aa@send.com", "USER_AA", "UTF-8"));

		/*
		 * 3: To addressee
		 */
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("cc-1@receive.com", "USER_CC", "UTF-8"));

		// Second addressee(optional)
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("cc-2@receive.com", "USER_DD", "UTF-8"));

		// Cc : carbon copy(optional)
		message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress("ee@receive.com", "USER_EE", "UTF-8"));

		// Bcc : Secret to send(optional)
		message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress("ff@receive.com", "USER_FF", "UTF-8"));

		/*
		 * 4: Subject : Email themem.
		 */
		message.setSubject("This is email theme", "UTF-8");

		/*
		 * 5: Content : Text.
		 */
		message.setContent("This is the text ...", "text/html;charset=UTF-8");

		/*
		 * 6: Date : Set the time of sending.
		 */
		message.setSentDate(new Date());

		/*
		 * 7: Save the setting about the email.
		 */
		message.saveChanges();

		/*
		 * 8: Save the message to a local file.
		 */
		OutputStream outputStream = new FileOutputStream("MyEmail.eml");
		message.writeTo(outputStream);

		// Release the resource.
		outputStream.flush();
		outputStream.close();

		System.out.println("Success to send the email ~");
	}
}
