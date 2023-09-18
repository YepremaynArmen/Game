package game.dev.Controllers;

import game.dev.Models.Questions;
import game.dev.Services.QuestionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/questions")
@CrossOrigin(origins = "*")
public class QuestionsController {

    @Autowired
    private QuestionsServiceImpl questionsServiceImpl;

    @RequestMapping(value = "{questionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Questions> getQuestions(@PathVariable("questionId") Long questionId){
        return this.questionsServiceImpl.getQuestionsById(questionId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<List<Questions>> findAllBySpecification(@RequestParam(name = "questionId",required=false) Long questionId
    ) {
        return this.questionsServiceImpl.findAllBySpecification(questionId);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Questions> saveQuestions(@RequestBody @Valid Questions questions){
        return this.questionsServiceImpl.saveQuestions(questions);
    }

    @RequestMapping(value = "{questionId}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Questions> updateQuestions(@PathVariable("questionId") Long questionId
                                                   , @RequestBody Questions questions){
        return this.questionsServiceImpl.updateQuestions(questionId, questions);
    }

    @RequestMapping(value = "/{questionId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> deleteQuestions(@PathVariable("questionId") Long questionId){
        return this.questionsServiceImpl.deleteQuestions(questionId);
    }
    
    
}
