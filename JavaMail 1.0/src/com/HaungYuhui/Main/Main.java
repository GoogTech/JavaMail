package com.HaungYuhui.Main;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * @Project JavaMail 1.0
 * @Package com.HaungYuhui.Main
 * @Description Try to send the email to the specified email address.
 * @Author Huang Yuhui
 * @Date Feb 20, 2019-3:11:03 PM
 * @version 1.0
 */
public class Main
{
	/*
	 * Addresser : email address and password,server address of SMTP.
	 */
	public static String myEmailAccount = "3083968068@qq.com";
	public static String myEmailPassword = "xxxxxx";// Authorization code.//IMAG/SMTP : xxxxxx
	public static String myEmailSMTPHost = "smtp.qq.com";

	/*
	 * Addressee : email address.
	 */
	public static String receiveMailAccount = "xxxxxx@qq.com";// Try to send the email to myself.

	// Test the program.
	public static void main(String[] args) throws UnsupportedEncodingException, MessagingException
	{
		/*
		 * 1: Create the parameter due to connect the server of email.
		 */
		Properties properties = new Properties();// Set the parameter.
		properties.setProperty("mail.transport.protocol", "smtp");// standard.
		properties.setProperty("mail.smtp.host", myEmailSMTPHost);// address about SMTP Server.
		properties.setProperty("mail.smtp.auth", "ture");// need to request authentication.

		/*
		 * 2: Create the session by the setting.(Used to interact with the mail server)
		 */
		Session session = Session.getInstance(properties);
		session.setDebug(true);// You can view the detailed send log.

		/*
		 * 3: Create the a email.
		 */
		MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount);

		/*
		 * 4: Gets the mail transfer object based on the Session.
		 */
		Transport transport = session.getTransport();

		/*
		 * 5: (Important step)Connect to the server of email with the email address and
		 * password.
		 */
		try
		{
			transport.connect(myEmailAccount, myEmailPassword);
		} catch (MessagingException e)
		{
			System.err.println("Error : Fail to connect to server of email !\n");
			e.printStackTrace();
		}

		/*
		 * 6: Send the email to the all of Recipients.
		 */
		transport.sendMessage(message, message.getAllRecipients());

		/*
		 * 7: Release the resource.
		 */
		transport.close();
		System.out.println("\n[ TIP : Success to send the email to specified user ! ]\n");

	}

	/**
	 * @Title MimeMessage
	 * @Description Create the MimeMessage.
	 * @param session,send mail and reveive mail.
	 * @return MimeMessage
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 * @date Feb 20, 2019-3:40:06 PM
	 *
	 */
	public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail)
			throws UnsupportedEncodingException, MessagingException
	{

		// 1: Create a email.
		MimeMessage message = new MimeMessage(session);
		// 2: From : addresser.
		message.setFrom(new InternetAddress(sendMail, "Java Program", "UTF-8"));
		// 3: To : recipients.
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "Program", "UTF-8"));
		// 4: Subject : The email theme.
		message.setSubject("Hi Friend", "UTF-8");
		// 5: Content : Some text...
		message.setContent("You have received my email successfully. --- Huang Yuhui", "text/html;charset=UTF-8");
		// 6: Date
		message.setSentDate(new Date());
		// 7: Save the setting.
		message.saveChanges();

		return message;

	}

}
