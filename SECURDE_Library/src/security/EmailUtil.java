package security;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {

	/**
	   Outgoing Mail (SMTP) Server
	   requires TLS or SSL: smtp.gmail.com (use authentication)
	   Use Authentication: Yes
	   Port for SSL: 465
	 */
	
	final static String fromEmail = "email.dummy.tester@gmail.com"; //requires valid gmail id
	final static String password = "EmailDummyTesting"; // correct password for gmail id
	final static Session session;
	static {

		System.out.println("SSLEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
		props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); //SMTP Port
		
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		
		session = Session.getDefaultInstance(props, auth);
		System.out.println("Session created");
	}
	
	/**
	 * Utility method to send simple HTML email
	 * 
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	public static void sendEmail(String toEmail, String subject, String body) {
		try {
			MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			msg.setFrom(new InternetAddress(fromEmail, "SHS Library No-reply"));

			msg.setReplyTo(InternetAddress.parse(fromEmail, false));

			msg.setSubject(subject, "UTF-8");

			msg.setText(body, "UTF-8");

			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			System.out.println("Message is ready");
			Transport.send(msg);

			System.out.println("EMail Sent Successfully!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//
//	/**
//	 * Utility method to send email with attachment
//	 * 
//	 * @param session
//	 * @param toEmail
//	 * @param subject
//	 * @param body
//	 */
//	public static void sendAttachmentEmail(Session session, String toEmail, String fromEmail, String subject,
//			String body) {
//		try {
//			MimeMessage msg = new MimeMessage(session);
//			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
//			msg.addHeader("format", "flowed");
//			msg.addHeader("Content-Transfer-Encoding", "8bit");
//
//			msg.setFrom(new InternetAddress("no_reply@journaldev.com", "NoReply-JD"));
//
//			msg.setReplyTo(InternetAddress.parse("no_reply@journaldev.com", false));
//
//			msg.setSubject(subject, "UTF-8");
//
//			msg.setSentDate(new Date());
//
//			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
//
//			// Create the message body part
//			BodyPart messageBodyPart = new MimeBodyPart();
//
//			// Fill the message
//			messageBodyPart.setText(body);
//
//			// Create a multipart message for attachment
//			Multipart multipart = new MimeMultipart();
//
//			// Set text message part
//			multipart.addBodyPart(messageBodyPart);
//
//			// Second part is attachment
//			messageBodyPart = new MimeBodyPart();
//			String filename = "abc.txt";
//			DataSource source = new FileDataSource(filename);
//			messageBodyPart.setDataHandler(new DataHandler(source));
//			messageBodyPart.setFileName(filename);
//			multipart.addBodyPart(messageBodyPart);
//
//			// Send the complete message parts
//			msg.setContent(multipart);
//
//			// Send message
//			Transport.send(msg);
//			System.out.println("EMail Sent Successfully with attachment!!");
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Utility method to send image in email body
//	 * 
//	 * @param session
//	 * @param toEmail
//	 * @param subject
//	 * @param body
//	 */
//	public static void sendImageEmail(Session session, String toEmail, String fromEmail, String subject, String body) {
//		try {
//			MimeMessage msg = new MimeMessage(session);
//			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
//			msg.addHeader("format", "flowed");
//			msg.addHeader("Content-Transfer-Encoding", "8bit");
//
//			msg.setFrom(new InternetAddress(fromEmail, "SHS Library No-reply"));
//
//			msg.setReplyTo(InternetAddress.parse(fromEmail, false));
//
//			msg.setSubject(subject, "UTF-8");
//
//			msg.setSentDate(new Date());
//
//			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
//
//			// Create the message body part
//			BodyPart messageBodyPart = new MimeBodyPart();
//
//			messageBodyPart.setText(body);
//
//			// Create a multipart message for attachment
//			Multipart multipart = new MimeMultipart();
//
//			// Set text message part
//			multipart.addBodyPart(messageBodyPart);
//
//			// Second part is image attachment
//			messageBodyPart = new MimeBodyPart();
//			String filename = "image.png";
//			DataSource source = new FileDataSource(filename);
//			messageBodyPart.setDataHandler(new DataHandler(source));
//			messageBodyPart.setFileName(filename);
//			// Trick is to add the content-id header here
//			messageBodyPart.setHeader("Content-ID", "image_id");
//			multipart.addBodyPart(messageBodyPart);
//
//			// third part for displaying image in the email body
//			messageBodyPart = new MimeBodyPart();
//			messageBodyPart.setContent("<h1>Attached Image</h1>" + "<img src='cid:image_id'>", "text/html");
//			multipart.addBodyPart(messageBodyPart);
//
//			// Set the multipart message to the email message
//			msg.setContent(multipart);
//
//			// Send message
//			Transport.send(msg);
//			System.out.println("EMail Sent Successfully with image!!");
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//	}
}