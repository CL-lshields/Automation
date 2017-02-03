package qa.utility.emailutility;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import qa.SeleniumTest;
import qa.utility.reportutility.ReportList;

import java.util.Properties;

public class EmailTool {
	
	// Recipient's email ID needs to be mentioned.
    private String recipient = "abcd@gmail.com";

    // Sender's email ID needs to be mentioned
    private String googleSender = "lanceisgame@gmail.com";//"web@gmail.com";
    
    private String officeSender = "lshields@bizjournals.com";//"web@gmail.com";
    
    
    // Assuming you are sending email from localhost
    private String googleHost = "smtp.gmail.com";//"smtp.office365.com";////"localhost";
    
    private String officeHost = "smtp.office365.com";

    // Get system properties
    private Properties properties = System.getProperties();    

    // Get the default Session object.
    private Session session;
    
    SMTPAuthenticator auth;
	
	public EmailTool(String recipient){
		this.recipient = recipient;		
		
		// Setup mail server
		properties.put("mail.smtp.host", officeHost);
		//properties.put("mail.smtp.user", this.sender);
		properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.debug", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", "587");
        //properties.setProperty("mail.smtp.ssl.enable", "true");
        //properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "true");  
        //properties.setProperty("mail.transport.protocol", "smtps");  
     
        //authenticate email
	    auth = new SMTPAuthenticator();
	    
		// Get the default Session object.
	    session = Session.getInstance(properties, auth);
	    session.setDebug(false);		 
	    
	    
	}		
	
	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getGoogleSender() {
		return googleSender;
	}

	public void setSender(String sender) {
		this.googleSender = sender;
	}

	public String getGoogleHost() {
		return googleHost;
	}

	public void setHost(String host) {
		this.googleHost = host;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
	public void sendEmail(){
		try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(this.officeSender));

	         // Set To: header field of the header.
	         message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(this.recipient));

	         // Set Subject: header field
	         message.setSubject("Automated QA notification...");

	         // Send the actual HTML message, as big as you like
	         message.setContent("<h1>The automated regression test '"+ReportList.reportName+"' has experienced an error. You can review the test results at the link below.</h1><a href='http://qa-reports.bizj-internal.com.s3.amazonaws.com/Nightly%20Regression"
	         +ReportList.reportName.replace(":","%3A")+".html' id='LPlnk418648'>Thank you!</a>", "text/html" );

	         // Send message - google port is 465 - office 365 port is 587
	         Transport transport = session.getTransport("smtp");
	         transport.connect(this.officeHost, 587, this.officeSender, "zfdhxsnnsfvzgsxf");
	         transport.sendMessage(message, message.getAllRecipients());
	         transport.close();	       
	         SeleniumTest.logger.info("message sent succesfully!" + System.lineSeparator());
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	         SeleniumTest.logger.severe(mex.getMessage() + System.lineSeparator());
	      }
	}
	
	public void sendEmail(MimeMessage mail){
		try{
	         // Create a default MimeMessage object.
	         MimeMessage message = mail;

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(this.officeSender));

	         // Set To: header field of the header.
	         message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(this.recipient));

	         // Set Subject: header field
	         message.setSubject("Automated QA notification...");

	         // Send the actual HTML message, as big as you like
	         //message.setContent("<h1>The automated regression test '"+ReportList.reportName+"' has experienced an error. You can review the test results at the link below.</h1><a href='http://qa-reports.bizj-internal.com/Nightly%20Regression"+ReportList.reportName+"' id='LPlnk418648'>Thank you!</a>", "text/html" );

	         // Send message - google port is 465 - office 365 port is 587
	         Transport transport = session.getTransport("smtp");
	         transport.connect(this.officeHost, 587, this.officeSender, "zfdhxsnnsfvzgsxf");
	         transport.sendMessage(message, message.getAllRecipients());
	         transport.close();	       
	         SeleniumTest.logger.info("message sent succesfully!" + System.lineSeparator());
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	         SeleniumTest.logger.severe(mex.getMessage() + System.lineSeparator());
	      }
	}
}
