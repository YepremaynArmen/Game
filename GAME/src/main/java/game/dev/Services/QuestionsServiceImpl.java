package game.dev.Services;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import game.dev.Models.QQuestions;
import game.dev.Models.Questions;
import game.dev.Repositories.QuestionsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import jakarta.persistence.EntityManager;
import java.util.List;

@Service
@Slf4j
public class QuestionsServiceImpl implements QuestionsService {

    private final EntityManager entityManager;
    @Autowired
    QuestionsRepository questionsRepository;

    private JPAQueryFactory queryFactory;

    public QuestionsServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Questions getById(Long questionId) {
        log.info("IN questionsRepository getById {}", questionId);
        return questionsRepository.findById(questionId).get();
    }

    @Override
    public void save(Questions questions) {
        log.info("IN questionsRepository save {}", questions);
        questionsRepository.save(questions);
    }


    public Questions patchQuestions(Long questionId, Questions questions) {

        Questions questionsModif = getById(questionId);


        if (StringUtils.hasLength(questions.getDescription())) {
            questionsModif.setDescription(questions.getDescription());
        }
        if (StringUtils.hasLength(questions.getGroups())) {
            questionsModif.setGroups(questions.getGroups());
        }
        if (questions.getCreatedBy()!= null) {
            questionsModif.setCreatedBy(questions.getCreatedBy());
        }
        if (questions.getComplexity()!= null) {
            questionsModif.setComplexity(questions.getComplexity());
        }
        if (questions.getCreatedAt()!= null) {
            questionsModif.setCreatedAt(questions.getCreatedAt());
        }
        if (questions.getAprovedBy()!= null) {
            questionsModif.setAprovedBy(questions.getAprovedBy());
        }
        if (questions.getAprovedAt()!= null) {
            questionsModif.setAprovedAt(questions.getAprovedAt());
        }
        if (questions.getChangeAt()!= null) {
            questionsModif.setChangeAt(questions.getChangeAt());
        }
        if (StringUtils.hasLength(questions.getStatus())) {
            questionsModif.setStatus(questions.getStatus());
        }
        if (StringUtils.hasLength(questions.getTask())) {
            questionsModif.setTask(questions.getTask());
        }

        questionsRepository.save(questionsModif);

        return questionsModif;
    }    
    
    

    @Override
    public void deleteById(Long questionId) {
        log.info("IN questionsRepository delete {}", questionId);
        questionsRepository.deleteById(questionId);
    }

    @Override
    public List<Questions> getAll() {
        log.info("IN questionsRepository getAll");
        return questionsRepository.findAll();
    }

    public ResponseEntity<Questions> getQuestionsById(Long questionId){
        if (questionId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Questions questions = getById(questionId);
        if (questions == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<Questions>> findAllBySpecification(Long questionId) {
        queryFactory = new JPAQueryFactory(entityManager);
        QQuestions questions = QQuestions.questions;
        JPAQuery query = new JPAQuery(entityManager);
        if (questionId != null) {
            query.where(questions.questionId.eq(questionId));
        }
        List<Questions> rows =  query.from(questions).stream().toList();
        ResponseEntity<List<Questions>> response;
        response = new ResponseEntity<>(rows, HttpStatus.OK);
        return response ;
    }

    public ResponseEntity<Questions> saveQuestions(Questions questions){
        HttpHeaders headers = new HttpHeaders();
        if (questions == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.questionsRepository.save(questions);
        return new ResponseEntity<>(questions, headers, HttpStatus.CREATED);
    }

    public ResponseEntity<Questions> updateQuestions(Long questionId, Questions questions){
        HttpHeaders headers = new HttpHeaders();
        if (questions == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Questions questionsModif =  patchQuestions(questionId, questions);

        return new ResponseEntity<>(questionsModif, headers, HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteQuestions(Long questionId){

        Questions questions = getById(questionId);
        ResponseEntity<Object> response;
        if (questions == null){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            deleteById(questionId);
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }


}
