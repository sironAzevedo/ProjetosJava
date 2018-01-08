package enviarEmail;

import java.util.logging.Logger;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;


/**
 *
 * @author siron
 */
public class EnviarEmail {

    public EnviarEmail() {
    }

    public void enviarEmail() {
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setDebug(true);
//            email.setAuthenticator(new DefaultAuthenticator('));
            email.setAuthentication("sirondba@gmail.com", "edvaldo08121990");
            email.setSSLOnConnect(true);
            email.setFrom("sirondba@gmail.com");
            email.setSubject("TestMail");
            email.setMsg("Primeiro email teste");
            email.addTo("sirondba@gmail.com");
            email.send();
        } catch (Exception e) {
            Logger.getLogger(null);
        }
    }
}
