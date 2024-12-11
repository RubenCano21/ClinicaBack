package uagrm.bo.workflow.controller;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@PropertySource("classpath:application.properties")
@RestController
@RequestMapping("/api/chatbot")
public class ChatbotController {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String phoneNumber;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody Map<String, String> request) {

        String to = request.get("to");
        String messageBody = request.get("message");

        if (!to.startsWith("+")){
            return ResponseEntity.badRequest().body("Invalid phone number format. Please include country code.");
        }

        Twilio.init(accountSid, authToken);

        Message message = Message.creator(
                new PhoneNumber("whatsapp:"+to),
                new PhoneNumber("whatsapp:"+phoneNumber),
                messageBody
        ).create();

        return ResponseEntity.ok("Message sent: " + message.getSid());
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> receiveMessage(@RequestBody Map<String, String> webhookData) {
        String from = webhookData.get("from");
        String body = webhookData.get("body");

        String reply = handleChatLogic(body);

        Twilio.init(accountSid, authToken);
        Message.creator(
                new PhoneNumber("whatsapp:"+from),
                new PhoneNumber("whatsapp:"+phoneNumber),
                reply
        ).create();

        return ResponseEntity.ok("Reply sent");
    }

    private String handleChatLogic(String userInput) {
        // Aquí puedes agregar lógica de chatbot (e.g., respuestas basadas en palabras clave).
        if (userInput.contains("horario")) {
            return "Nuestro horario es de 9 AM a 6 PM, de lunes a viernes.";
        } else if (userInput.contains("dirección")) {
            return "Estamos ubicados en Av. Principal 123, Ciudad.";
        } else {
            return "Lo siento, no entendí tu consulta. Por favor intenta nuevamente.";
        }
    }
}
