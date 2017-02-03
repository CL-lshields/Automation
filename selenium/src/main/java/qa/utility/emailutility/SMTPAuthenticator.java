package qa.utility.emailutility;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {

	public PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication("lshields@bizjournals.com","zfdhxsnnsfvzgsxf");
	}
}
