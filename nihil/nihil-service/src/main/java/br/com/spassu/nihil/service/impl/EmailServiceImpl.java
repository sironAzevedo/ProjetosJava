package br.com.spassu.nihil.service.impl;

import br.com.spassu.nihil.common.entity.Monitor;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

/**
 *
 */
@Component("EmailServiceImpl")
public class EmailServiceImpl {

    @Autowired
    private SimpleMailMessage mailMessage;
    
    @Autowired
    private MailSender mailSender;
    
    private @Value("${email.adm}") String emailAdm;
    private @Value("${email.naoresponda}") String naoresponda;
    private @Value("${email.monitores}") String emailMonitores;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    private void enviarEmail(Email email) {
        try {
            logger.info("Enviando email...");
            mailMessage.setFrom(email.getFrom());
            mailMessage.setTo(email.getTo());
            mailMessage.setBcc(email.getBcc());
            mailMessage.setSubject(email.getSubject());
            mailMessage.setText(email.getText());
            mailSender.send(mailMessage);
            logger.info("Email enviado com sucesso.");
        } catch (Exception e) {
            logger.error("Erro na tentativa enviar email", e);
        }    
    }
    
    private void sendMimeMail(Email email) {
        try {
            logger.info("Enviando email...");
            
            String[] tos = email.getTo().split(",");
            
            MimeMessage message = ((JavaMailSender)mailSender).createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, email.isMultipart());
            helper.setFrom(email.getFrom());
            helper.setTo(tos);
            helper.addBcc(email.getBcc());
            helper.setSubject(email.getSubject());
            helper.setText(email.getText(), email.isHtml());
            
            if (email.isMultipart()) {
                for (Anexo anexo : email.getAnexos()) {
                    DataSource source = new ByteArrayDataSource(anexo.getBytes(), anexo.getType());
                    helper.addAttachment(anexo.getName(), source);
                }
            }
            
            ((JavaMailSender)mailSender).send(message);
            
            logger.info("Email enviado com sucesso.");
        } catch (Exception e) {
            logger.error("Erro na tentativa enviar email", e);
        }    
        
    }
    
    public void notificarMudancaStatusTargets(List<Monitor> monitores) {

        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
        
        StringBuilder builder = new StringBuilder();
        builder.append("<table>");
        builder.append("    <thead>");
        builder.append("        <tr>");
        builder.append("            <th>Data</th>");
        builder.append("            <th>Alvo</th>");
        builder.append("            <th>Descrição</th>");
        builder.append("            <th>Status</th>");
        builder.append("        </tr>");
        builder.append("    </thead>");
        builder.append("    <tbody>");
        
        for (Monitor monitor : monitores) {
            builder.append("        <tr>");
            builder.append("            <td>").append(fmt.format(monitor.getData())).append("/td>");
            builder.append("            <td>").append(monitor.getTarget().getNome()).append("/td>");
            builder.append("            <td>").append(monitor.getTarget().getDescricao()).append("/td>");
            builder.append("            <td>").append(monitor.getStatus().getDescricao()).append("/td>");
            builder.append("        </tr>");
        }
        
        builder.append("    </tbody>");
        builder.append("</table>");
        
        Email email = new Email();
        email.setFrom(naoresponda);
        email.setTo(emailMonitores);
        email.setBcc(emailAdm);
        email.setSubject("SPASSU / CDS-BA - Os targets relacionados sofreram alterações de status.");
        email.setText(builder.toString());
        email.setHtml(true);
        sendMimeMail(email);
    }   
    
    private class Email {
        private String to;
        private String from;
        private String subject;
        private String bcc;
        private String text;
        private boolean multipart;
        private boolean html;
        private List<Anexo> anexos;

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getBcc() {
            return bcc;
        }

        public void setBcc(String bcc) {
            this.bcc = bcc;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public boolean isMultipart() {
            return multipart;
        }

        public void setMultipart(boolean multipart) {
            this.multipart = multipart;
        }

        public boolean isHtml() {
            return html;
        }

        public void setHtml(boolean html) {
            this.html = html;
        }

        public List<Anexo> getAnexos() {
            return anexos;
        }

        public void setAnexos(List<Anexo> anexos) {
            this.anexos = anexos;
        }
        
    }

    
    private class Anexo {
        //Nome do anexo com extensão.
        private String name;
        private byte[] bytes;
        private String type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public byte[] getBytes() {
            return bytes;
        }

        public void setBytes(byte[] bytes) {
            this.bytes = bytes;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
        
        
        
    }
}
