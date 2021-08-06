package resu.resumaker.services;
import java.io.File;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
public class EmailService {

    public EmailService(File resume, String to) throws NoSuchProviderException, AddressException {
        final String user="resuteam.codelabs@gmail.com";
        final String password="bluesky2020";
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.host", "smtp.gmail.com");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.debug", "true");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getDefaultInstance(properties,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(user,password);
                        }
                    });
        Transport transport = session.getTransport();
            try{
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(user));
                message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
                message.setSubject("Your PDF is ready for use - ResuTeam");

                BodyPart messageBodyPart1 = new MimeBodyPart();
                messageBodyPart1.setText("Hello!\n\nYour professionally made resume is ready for use.\n\nGood luck in your job hunt,\n\nResuTeam");

                MimeBodyPart messageBodyPart2 = new MimeBodyPart();

                DataSource source = new FileDataSource(String.valueOf(resume));
                messageBodyPart2.setDataHandler(new DataHandler(source));
                messageBodyPart2.setFileName("resume.pdf");

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart1);
                multipart.addBodyPart(messageBodyPart2);

                message.setContent(multipart );

                transport.connect();
                Transport.send(message);
                transport.close();

                System.out.println("message sent....");
            }catch (MessagingException ex) {ex.printStackTrace();}
        }
    }
