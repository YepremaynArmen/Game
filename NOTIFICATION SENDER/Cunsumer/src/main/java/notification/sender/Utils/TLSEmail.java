package notification.sender.Utils;

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

    public static String[] sendEmail(String[] args) {
        final String fromEmail = args[3];
        final String password = args[4]; // correct password for gmail id
        final String toEmail = args[1];//"earmen@mail.ru"; // can be any email id

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.yandex.ru"); //SMTP Host
        props.put("mail.smtp.port", "465"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        props.put("mail.smtp.ssl.enable", "true"); //enable STARTTLS
        props.put("mail.smtp.ssl.trust", "*"); //enable STARTTLS
        props.put("mail.debug", "true"); //enable STARTTLS
        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);
        return EmailUtil.sendEmail(session, args[3], toEmail,"Регистрация в TalentID", args[0]);
    }


}