package notification.sender.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import notification.sender.Models.Letters;
import notification.sender.Repositories.LettersRepository;
import notification.sender.Utils.MailStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static notification.sender.Utils.TLSEmail.sendEmail;

@Service
@Slf4j
@Transactional
public class LettersServiceImpl implements LettersService {

    @Autowired
    LettersRepository lettersRepository;

    @Autowired
    private Environment environment;

    @Override
    public Letters getById(Long id) {
        log.info("IN LettersServiceImpl getById {}", id);
        return lettersRepository.findById(id).get();
    }

    @Override
    public void save(Letters letters) {
        log.info("IN LettersServiceImpl save {}", letters);
        lettersRepository.save(letters);
    }

    public ResponseEntity<Letters> verifyLetters(String message ) throws JsonProcessingException {

        JSONObject jsonObject = new JSONObject(message);
        String transactionId = jsonObject.getString("transactionId");
        String body = jsonObject.getString("body");
        JSONArray addressTo = jsonObject.getJSONArray("addressTo");

        Letters inLetters = new Letters();
        inLetters.setTransactionId(UUID.fromString(transactionId));
        inLetters.setBody(body);
        inLetters.setAddressTo(addressTo.toString());

        HttpHeaders headers = new HttpHeaders();
        Letters lettersModif =new Letters();
        lettersModif.setAddressTo(inLetters.getAddressTo());
        lettersModif.setBody(inLetters.getBody());
        lettersModif.setTransactionId(inLetters.getTransactionId());
        lettersModif.setStatus(MailStatus.New);
        lettersModif.setSentDate( new Date(System.currentTimeMillis()));
        save(lettersModif);

        String emails = inLetters.getAddressTo();
        JSONArray jsonArray = new JSONArray(emails);
        ObjectMapper mapper = new ObjectMapper();
        List<String> listAddress = mapper.readValue(String.valueOf(jsonArray), List.class);
        Object obj = listAddress.get(0);
        Map mapAddress = (Map) obj;
        final String[][] mailMsg = {new String[2]};

        mapAddress.forEach((key, value) -> {
            String[] arg = new String[5];
            arg[0] = inLetters.getBody();
            arg[1] = value.toString(); //inLetters.getAddressTo();
            arg[2] = inLetters.getTransactionId().toString();
            arg[3] = environment.getProperty("mailFrom");
            arg[4] = environment.getProperty("mailPass");
            mailMsg[0] = sendEmail(arg);
        });
        lettersModif.setResponseMsg(mailMsg[0][0]);
        lettersModif.setSentDate( new Date(System.currentTimeMillis()));
        lettersModif.setStatus(MailStatus.Sent);
        save(lettersModif);
        return new ResponseEntity<>(lettersModif, headers, HttpStatus.OK);
    }
}
