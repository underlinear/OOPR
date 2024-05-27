package accountretrieval;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class TLSEmail {

	/**
	   Outgoing Mail (SMTP) Server
	   requires TLS or SSL: smtp.gmail.com (use authentication)
	   Use Authentication: Yes
	   Port for TLS/STARTTLS: 587
	 */
	public void generateEmail(String email, String first_name, String code) {
		final String fromEmail = "email"; // gmail
		final String password = rot13("password");           // password
		final String toEmail = email;
		final String message = 
			    "<html>"
			    + "    <body>"
			    + "        <div style='background-color: #f5f5dc; text-align: center; padding: 50px;'>"
			    + "            <div style='background-color: #99cc32; padding: 40px; text-align: center; width: 200px; margin: 0 auto; border-radius: 20px;'>"
			    + "                <img src='https://imgur.com/BOVgK1K.png' style='width: 100px; height: 100px;' />"
			    + "                <p style='font-family: Helvetica;'>Hello " + first_name + "! Your code for password retrieval is:</p>"
			    + "                <h1 style='font-family: Helvetica;'>" + code + "</h1>"
			    + "            </div>"
			    + "        </div>"
			    + "    </body>"
			    + "</html>";
		
		System.out.println("Please wait...");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
                //create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		
		EmailUtil.sendEmail(session, toEmail,"Password Retrieval Code from ToadInOne", message);
	}
	
	public static String rot13(String input) {
		   StringBuilder sb = new StringBuilder();
		   for (int i = 0; i < input.length(); i++) {
		       char c = input.charAt(i);
		       if       (c >= 'a' && c <= 'm') c += 13;
		       else if  (c >= 'A' && c <= 'M') c += 13;
		       else if  (c >= 'n' && c <= 'z') c -= 13;
		       else if  (c >= 'N' && c <= 'Z') c -= 13;
		       sb.append(c);
		   }
		   return sb.toString();
		}

	
}