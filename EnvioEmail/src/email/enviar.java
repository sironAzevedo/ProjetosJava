package email;

import java.net.MalformedURLException;
import org.apache.commons.mail.EmailException;

/**
 *
 */
public class enviar {

    public static void main(String[] args) throws EmailException, MalformedURLException {
        CommonsMail commonsMail = new CommonsMail();
        commonsMail.enviaEmailSimples();
    }
}
