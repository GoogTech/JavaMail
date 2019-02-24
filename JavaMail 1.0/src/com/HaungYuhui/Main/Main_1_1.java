package com.HaungYuhui.Main;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.HuangYuhui.Utils.MailConfig;
import com.HuangYuhui.Utils.ManipulatingEmail;

/**
 * 
 * @Project JavaMail 1.1
 * @Package com.HaungYuhui.Main
 * @Description Try to send the email to the specified email address.
 * @Author Huang Yuhui
 * @Date Feb 21, 2019-11:02:10 AM
 * @version 1.1
 */
public class Main_1_1
{
	// Addresser : email address and password,server address of SMTP.
	final static String MY_EMAIL_STMPHOST = MailConfig.mailConfigInfo.getEmailSMTPHost();
	final static String MY_EMAIL_ACCOUNT = MailConfig.mailConfigInfo.getUserMailAccount();
	final static String MY_EMAIL_PASSWORD = MailConfig.mailConfigInfo.getUserMailPassword();// Authorization code.
	final static String MY_EMAIL_SUBJECT = MailConfig.mailConfigInfo.getEmailSubject();
	final static String MY_EMAIL_CONTENT = MailConfig.mailConfigInfo.getEmailContent();
	// Addressee : email address.
	final static String RECIPIENTS_EMAIL_ACCOUNT = MailConfig.mailConfigInfo.getEmailRecipient(); 

	/**
	 * @Title Main
	 * @Description Test the program.
	 * @return void
	 * @date Feb 24, 2019-7:56:27 PM
	 *
	 */
	public static void main(String[] args) throws MessagingException, IOException
	{
		// 1: Create the parameter due to connect the server of email.
		Properties properties = new Properties();// Set the parameter.
		properties.setProperty("mail.transport.protocol", "smtp");// standard.
		properties.setProperty("mail.smtp.host", MY_EMAIL_STMPHOST);// address about SMTP Server.
		properties.setProperty("mail.smtp.auth", "ture");// need to request authentication.

		// 2: Create the session by the setting.(Used to interact with the mail server).
		Session session = Session.getInstance(properties);
		session.setDebug(true);// You can view the detailed send log.

		// 3: Create the a email.
		MimeMessage message = createMimeMessage(session, MY_EMAIL_ACCOUNT, RECIPIENTS_EMAIL_ACCOUNT);

		// (optional)Save the email to a local.
		ManipulatingEmail.saveEmail("MyEmail_1.1.eml", message);

		// 4: Gets the mail transfer object based on the Session.
		Transport transport = session.getTransport();

		// 5: (Important step)Connect to the server of email with the email address and
		// password.
		try
		{
			transport.connect(MY_EMAIL_ACCOUNT, MY_EMAIL_PASSWORD);
		} catch (MessagingException e)
		{
			System.err.println("Error : Fail to connect to server of email !\n");
			e.printStackTrace();
		}

		// 6: Send the email to the all of Recipients.
		transport.sendMessage(message, message.getAllRecipients());

		// 7: Release the resource.
		transport.close();
		System.out.println("\n[ TIP : Success to send the email to specified user ! ]\n");

	}

	/**
	 * @Title MimeMessage
	 * @Description Create a email.
	 * @param session,send mail and reveive mail.
	 * @return MimeMessage
	 * @throws MessagingException
	 * @throws IOException
	 * @date Feb 21, 2019-11:02:10 AM
	 *
	 */
	public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail)
			throws MessagingException, IOException
	{

		// 1: Create a email.
		MimeMessage message = new MimeMessage(session);
		// 2: From : addresser.
		message.setFrom(new InternetAddress(sendMail, "Java Program", "UTF-8"));
		// 3: To : recipients.
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "Program", "UTF-8"));
		// 4: Subject : The email theme.
		message.setSubject(MY_EMAIL_SUBJECT, "UTF-8");

		/*
		 * Create the content of the email as followed .
		 */
		// 5: Create the node of image.
		MimeBodyPart image = new MimeBodyPart();
		DataHandler dataHandler = new DataHandler(new FileDataSource("resource/Cute_pig.jpg"));// read the local file.
		image.setDataHandler(dataHandler);// Add the data of image to the node.
		image.setContentID("image_Cut_pig_id");// Set a unique number for the node and his ID is referenced in the text "node".

		// 6: Create the node of text.
		MimeBodyPart text = new MimeBodyPart();
		// You can actually add web images by HTTP links as well.
		text.setContent(MY_EMAIL_CONTENT, "text/html;charset=UTF-8");

		// 7: Combine text and image "nodes" into a hybrid "node".
		MimeMultipart mm_text_image = new MimeMultipart();
		mm_text_image.addBodyPart(text);
		mm_text_image.addBodyPart(image);
		mm_text_image.setSubType("related");// incidence relation.

		// 8: Encapsulate the text + image hybrid "node" as a normal "node". Attention:
		// The "Content" that is eventually added to the emailis a Multipart composed of
		// "bodyparts".
		MimeBodyPart text_image = new MimeBodyPart();
		text_image.setContent(mm_text_image);

		// 9: Create the node of attachment.
		MimeBodyPart attachment = new MimeBodyPart();
		DataHandler dataHandler2 = new DataHandler(new FileDataSource("resource/Love_Yourself.mp3"));
		attachment.setDataHandler(dataHandler2);
		// Set the file name of the attachment (encoding required).
		attachment.setFileName(MimeUtility.encodeText(dataHandler2.getName()));

		// 10: Set the relationship between ('text' + 'image') and the
		// 'attachment'.(compositing a large mixed "node"/Multipart).
		MimeMultipart mm_text_image_att = new MimeMultipart();
		mm_text_image_att.addBodyPart(text_image);// reference: 8.
		mm_text_image_att.addBodyPart(attachment);
		mm_text_image_att.setSubType("mixed");// mixed relations.

		// 11: Set the relationship for the entire email. (add the final mixed "node" as
		// the content of the email to the message object).
		message.setContent(mm_text_image_att);

		// 12: Set the date.
		message.setSentDate(new Date());
		// 13: Save the setting.
		message.saveChanges();

		return message;

	}
}
