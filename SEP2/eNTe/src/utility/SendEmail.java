package utility;

import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;

    public static void main(String[] args) {
//        try {
//            generateAndSendEmail("11michi11@gmail.com", "Your account has been hacked","To unlock, go to nearest shop, buy pizza and deliver it to room 195, Kamtjatka." +
//                    "<br>You have time till 19:00 09.05.2018" +
//                    "<br><br>Have a nice day ;)" +
//                    "~H4k3r");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
    }

    public static void generateAndSendEmail(String to,String subject, String emailBody) throws MessagingException {

        // Step1
        System.out.println("\n 1st ===> setup Mail Server Properties..");
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        System.out.println("Mail Server Properties have been setup successfully..");

        // Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        generateMailMessage.setSubject(subject);
        generateMailMessage.setContent(emailBody, "text/html");
        System.out.println("Mail Session has been created successfully..");

        // Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = getMailSession.getTransport("smtp");

        // Enter your correct gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
        transport.connect("smtp.gmail.com", "enteemailservice@gmail.com", "enteAdmin1");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public static void sendPasswordEmail(String email, String pwd) {
        String body = "This is your temporary password to eNTe system: " + ;
        try {
            generateAndSendEmail(email, "Change your password", body);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
