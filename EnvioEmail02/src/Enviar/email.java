/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enviar;

import java.util.Properties;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author siron
 */
public class email {

    private String mailSMTPServer;
    private String mailSMTPServerPort;
    private String mailSenha;

    public void sendEmail(String from, String to, String sbject, String message) {
        Properties properties = new Properties();
        mailSMTPServer = "smtp.googlemail.com";
        mailSMTPServerPort = "465";
        mailSenha = "edvaldo08121990";

        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", mailSMTPServer);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.user", from);
        properties.put("mail.debug", "true");
        properties.put("mail.smtp.port", mailSMTPServerPort);
        properties.put("mail.smtp.socketFactory.port", mailSMTPServerPort);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");

        SimpleAuth auth = null;
        auth = new SimpleAuth(from, mailSenha);

        Session session = Session.getDefaultInstance(properties, auth);
        session.setDebug(true);

        Message msg = new MimeMessage(session);

        try {
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setFrom(new InternetAddress(from));
            msg.setSubject(sbject);
            msg.setContent(message, "text/area");
        } catch (Exception e) {
            System.out.println("erro de envio " + e);
        }

        Transport transport;
        try {
            transport = session.getTransport("smtp");
            transport.connect(mailSMTPServer, from, mailSenha);
            msg.saveChanges();
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            System.out.println("erro de envio " + e);
        }
    }
}

