package game.dev.Controllers;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import game.dev.Models.*;
import game.dev.Models.StudentAnswers;
import game.dev.Repositories.StudentAnswersRepository;
import game.dev.Services.StudentAnswersService;
import game.dev.Services.StudentAnswersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/studentAnswers")
@CrossOrigin(origins = "*")
public class StudentAnswersController {
    @Autowired
    private StudentAnswersServiceImpl studentAnswersServiceImpl;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<StudentAnswers> getStudentAnswersById(@PathVariable("id") Long id){
        return this.studentAnswersServiceImpl.getStudentAnswersById(id);
    }


    @RequestMapping(method = RequestMethod.GET, value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<List<StudentAnswers>> findAllBySpecification(@RequestParam(name = "id",required=false) Long id
    ) {
        return this.studentAnswersServiceImpl.findAllBySpecification(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<StudentAnswers> saveStudentAnswers(@RequestBody @Valid StudentAnswers studentAnswers){
        return this.studentAnswersServiceImpl.saveStudentAnswers(studentAnswers);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<StudentAnswers> updateStudentAnswers(@PathVariable("id") Long id
            , @RequestBody StudentAnswers studentAnswers){
        return this.studentAnswersServiceImpl.updateStudentAnswers(id, studentAnswers);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> deleteStudentAnswers(@PathVariable("id") Long id){
        return this.studentAnswersServiceImpl.deleteStudentAnswers(id);
    }

}