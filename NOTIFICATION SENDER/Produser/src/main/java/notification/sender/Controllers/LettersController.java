package notification.sender.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import notification.sender.Services.RabbitMQProducer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/letters")
@CrossOrigin(origins = "*")
public class LettersController {

    private RabbitMQProducer producer;

    public LettersController(RabbitMQProducer producer) {
        this.producer = producer;
    }


    @RequestMapping(value = "/{transactionId}/verifyMQ", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> sendMessage(@PathVariable("transactionId") UUID transactionId,
                                              @RequestBody String letters
    ) throws JsonProcessingException {
        producer.sendMessage(letters);
        return ResponseEntity.ok("Message sent to RabbitMQ ...");
    }


}