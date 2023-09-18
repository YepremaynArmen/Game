package game.dev.Services;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import game.dev.Models.QStudentAnswers;
import game.dev.Models.StudentAnswers;
import game.dev.Repositories.StudentAnswersRepository;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@Slf4j
public class StudentAnswersServiceImpl implements StudentAnswersService {

    private final EntityManager entityManager;
    @Autowired
    StudentAnswersRepository studentAnswersRepository;

    private JPAQueryFactory queryFactory;

    public StudentAnswersServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public StudentAnswers getById(Long id) {
        log.info("IN studentAnswersRepository getById {}", id);
        return studentAnswersRepository.findById(id).get();
    }

    @Override
    public void save(StudentAnswers studentAnswers) {
        log.info("IN studentAnswersRepository save {}", studentAnswers);
        studentAnswersRepository.save(studentAnswers);
    }

    public StudentAnswers patchStudentAnswers(Long id, StudentAnswers studentAnswers) {

        StudentAnswers studentAnswersModif = studentAnswersRepository.getById(id);

        if (studentAnswers.getUserId()!= null) {
            studentAnswersModif.setUserId(studentAnswers.getUserId());
        }
        if (StringUtils.hasLength(studentAnswers.getAnswer())) {
            studentAnswersModif.setAnswer(studentAnswers.getAnswer());
        }
        if (studentAnswers.getCreatedAt()!= null) {
            studentAnswersModif.setCreatedAt(studentAnswers.getCreatedAt());
        }
        if (studentAnswers.getAprovedBy()!= null) {
            studentAnswersModif.setAprovedBy(studentAnswers.getAprovedBy());
        }
        if (studentAnswers.getAprovedAt()!= null) {
            studentAnswersModif.setAprovedAt(studentAnswers.getAprovedAt());
        }
        if (studentAnswers.getResult()!= null) {
            studentAnswersModif.setResult(studentAnswers.getResult());
        }
        if (studentAnswers.getProgress()!= null) {
            studentAnswersModif.setProgress(studentAnswers.getProgress());
        }
        if (StringUtils.hasLength(studentAnswers.getStatus())) {
            studentAnswersModif.setStatus(studentAnswers.getStatus());
        }
        if (studentAnswers.getQuestionId()!= null) {
            studentAnswersModif.setQuestionId(studentAnswers.getQuestionId());
        }

        studentAnswersRepository.save(studentAnswersModif);
        log.info("IN studentAnswersRepository save {}", studentAnswersModif);
        return studentAnswersModif;
    }
    
    
    
    @Override
    public void deleteById(Long id) {
        log.info("IN studentAnswersRepository delete {}", id);
        studentAnswersRepository.deleteById(id);
    }

    @Override
    public List<StudentAnswers> getAll() {
        log.info("IN studentAnswersRepository getAll");
        return studentAnswersRepository.findAll();
    }

    public ResponseEntity<StudentAnswers> getStudentAnswersById(@PathVariable("id") Long id){
        if (id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        StudentAnswers studentAnswers = getById(id);
        if (studentAnswers == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentAnswers, HttpStatus.OK);
    }

    public ResponseEntity<List<StudentAnswers>> findAllBySpecification(Long id) {
        queryFactory = new JPAQueryFactory(entityManager);
        QStudentAnswers studentAnswers = QStudentAnswers.studentAnswers;
        JPAQuery query = new JPAQuery(entityManager);
        if (id != null) {
            query.where(studentAnswers.id.eq(id));
        }
        List<StudentAnswers> rows =  query.from(studentAnswers).stream().toList();
        ResponseEntity<List<StudentAnswers>> response;
        response = new ResponseEntity<>(rows, HttpStatus.OK);
        return response ;
    }

    public ResponseEntity<StudentAnswers> saveStudentAnswers(StudentAnswers studentAnswers){
        HttpHeaders headers = new HttpHeaders();
        if (studentAnswers == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        save(studentAnswers);
        return new ResponseEntity<>(studentAnswers, headers, HttpStatus.CREATED);
    }

    public ResponseEntity<StudentAnswers> updateStudentAnswers(Long id, StudentAnswers studentAnswers){
        HttpHeaders headers = new HttpHeaders();
        if (studentAnswers == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        StudentAnswers studentAnswersModif =  patchStudentAnswers(id, studentAnswers);

        return new ResponseEntity<>(studentAnswersModif, headers, HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteStudentAnswers(Long id){

        StudentAnswers studentAnswers = getById(id);
        ResponseEntity<Object> response;
        if (studentAnswers == null){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            deleteById(id);
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

}
