package com.HaungYuhui.Main;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.HuangYuhui.Utils.ManipulatingEmail;

/**
 * 
 * @Project JavaMail 1.0
 * @Package com.HaungYuhui.Main
 * @Description Try to send the email to the specified mailbox.
 * @Author Huang Yuhui
 * @Date Feb 20, 2019-3:11:03 PM
 * @version 1.0
 */
public class SendEmail
{
	// Addresser
	public final static String MY_EMAILHOST = "smtp.qq.com";
	public final static String MY_EMAILACCOUNT = "xxxxxx@qq.com";
	public final static String MY_EMAILPASSWORD = "xxxxxx";// Authorization code.//IMAG/SMTP : xxxxxx
	public final static String MY_EMAILSUBJECT = "xxxxxx";
	public final static String MY_EMAILCONTENT = "xxxxxx";
	// Addressee
	public static String RECIPIENTS_MAILACCOUNT = "xxxxxx@qq.com";// Try to send the email to myself.
	// Email file
	public static String EMAIL_NAME = "SendEmail.eml";

	// Test the program.
	public static void main(String[] args) throws MessagingException, IOException
	{
		/*
		 * 1: Create the parameter due to connect the server of email.
		 */
		Properties properties = new Properties();// Set the parameter.
		properties.setProperty("mail.transport.protocol", "smtp");// standard.
		properties.setProperty("mail.smtp.host", MY_EMAILHOST);// address about SMTP Server.
		properties.setProperty("mail.smtp.auth", "ture");// need to request authentication.

		/*
		 * 2: Create the session by the setting.(Used to interact with the mail server)
		 */
		Session session = Session.getInstance(properties);
		session.setDebug(true);// You can view the detailed send log.

		/*
		 * 3: Create the a email.
		 */
		MimeMessage message = createMimeMessage(session, MY_EMAILACCOUNT, RECIPIENTS_MAILACCOUNT);

		/*
		 * (optional)Save the email to a local.
		 */
		ManipulatingEmail.saveEmail(EMAIL_NAME, message);

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
			transport.connect(MY_EMAILACCOUNT, MY_EMAILPASSWORD);
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
		System.out.println("\n[ TIP : The E-mail was sent to the specified mailbox successfully ! ]\n");

	}

	/**
	 * @Title MimeMessage
	 * @Description Create a email.
	 * @param session, sendEmail and reveiveEmail.
	 * @return MimeMessage
	 * @throws MessagingException
	 * @throws IOException
	 * @date Feb 20, 2019-3:40:06 PM
	 *
	 */
	public static MimeMessage createMimeMessage(Session session, String sendEMail, String receiveEMail)
			throws MessagingException, IOException
	{

		// 1: Create a email.
		MimeMessage message = new MimeMessage(session);
		// 2: From : addresser.
		message.setFrom(new InternetAddress(sendEMail, "Java Program", "UTF-8"));
		// 3: To : recipients.
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveEMail, "Program", "UTF-8"));
		// 4: Subject : The email theme.
		message.setSubject(MY_EMAILSUBJECT, "UTF-8");
		// 5: Content : Some text...
		message.setContent(MY_EMAILCONTENT, "text/html;charset=UTF-8");
		// 6: Date
		message.setSentDate(new Date());
		// 7: Save the setting.
		message.saveChanges();

		return message;
	}
}
