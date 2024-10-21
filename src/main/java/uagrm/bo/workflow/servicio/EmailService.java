package uagrm.bo.workflow.servicio;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarReporteEmail(String to, String subject, String body, byte[] attachment, String fileName) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);

            // Agregar archivo adjunto
            helper.addAttachment(fileName, new ByteArrayResource(attachment));

            mailSender.send(message);
            System.out.println("Correo enviado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
