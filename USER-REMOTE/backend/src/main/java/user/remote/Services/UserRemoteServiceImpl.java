package user.remote.Services;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import user.remote.Models.QUserRemote;
import user.remote.Models.UserRemote;
import user.remote.Repositories.UserRemoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@Transactional
public class UserRemoteServiceImpl implements UserRemoteService {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    UserRemoteRepository userRemoteRepository;

    private JPAQueryFactory queryFactory;
    private UserRemote userRemoteModif;

/*    @Autowired
    private UserRemoteMapper userRemoteMapper;*/



    @Override
    public UserRemote getById(UUID userId) {
        log.info("IN UserRemoteServiceImpl getById {}", userId);
        return userRemoteRepository.findById(userId).get();
    }

    @Override
    public void save(UserRemote userRemote) {
        log.info("IN UserRemoteServiceImpl save {}", userRemote);
        userRemoteRepository.save(userRemote);
    }


    public UserRemote patchUser(UUID userId, UserRemote userRemote ) {

        UserRemote userRemoteModif =getById(userId);
        //userRemoteMapper.updateCustomerFromDto(userRemoteModif, userRemote);

        if (StringUtils.hasLength(userRemote.getFirstName())) {
            userRemoteModif.setFirstName(userRemote.getFirstName());
        }
        if (StringUtils.hasLength(userRemote.getLastName())) {
            userRemoteModif.setLastName(userRemote.getLastName());
        }
        if (StringUtils.hasLength(userRemote.getPatronymicName())) {
            userRemoteModif.setPatronymicName(userRemote.getPatronymicName());
        }
        if (StringUtils.hasLength(userRemote.getPatronymicName())) {
            userRemoteModif.setPatronymicName(userRemote.getPatronymicName());
        }
        if (userRemote.getBirthDay() != null) {
            userRemoteModif.setBirthDay(userRemote.getBirthDay());
        }
        if (userRemote.getSex() != null) {
            userRemoteModif.setSex(userRemote.getSex());
        }
        if (userRemote.getCreateDate() != null) {
            userRemoteModif.setCreateDate(userRemote.getCreateDate());
        }
        if (userRemote.getChangeDate() != null) {
            userRemoteModif.setChangeDate(userRemote.getChangeDate());
        }
        if (userRemote.getRegisterDate() != null) {
            userRemoteModif.setRegisterDate(userRemote.getRegisterDate());
        }
        if (StringUtils.hasLength(userRemote.getPhone())) {
            userRemoteModif.setPhone(userRemote.getPhone());
        }
        if (StringUtils.hasLength(userRemote.getMail())) {
            userRemoteModif.setMail(userRemote.getMail());
        }
        if (userRemote.getStatus() != null) {
            userRemoteModif.setStatus(userRemote.getStatus());
        }

        save(userRemoteModif);
        return userRemoteModif;
    }

    @Override
    public void deleteById(UUID userId) {
        log.info("IN UserRemoteServiceImpl delete {}", userId);
        userRemoteRepository.deleteById(userId);
    }

    @Override
    public List<UserRemote> getAll() {
        log.info("IN LessonServiceImpl getAll");
        return userRemoteRepository.findAll();
    }

    public ResponseEntity<Object> getUserRemoteById(UUID userId) {
        log.info("getUserRemoteById(UUID userId)");
        if (userId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserRemote userRemote = getById(userId);
        if (userRemote == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userRemote, HttpStatus.OK);
    }

    public ResponseEntity<String> getUserRemoteStatus(UUID userId){
        HttpHeaders headers = new HttpHeaders();
        if (userId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserRemote userRemote = this.userRemoteRepository.getById(userId);
        if (userRemote == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            String status = userRemote.getStatus().toString();
            return new ResponseEntity<>(status, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> aproveUserRemoteStatus( UUID userId){
        if (userId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserRemote userRemoteModif =getById(userId);
        userRemoteModif.setStatus(2);
        save(userRemoteModif);

        return new ResponseEntity<>("Вы успешно завершили регистрацию", HttpStatus.OK);
    }


    public ResponseEntity<List<UserRemote>> findAllBySpecification(UUID userId
            , String firstName
            , String lastName
    ) {
        queryFactory = new JPAQueryFactory(entityManager);
        QUserRemote userRemote = QUserRemote.userRemote;
        JPAQuery query = new JPAQuery(entityManager);
        if (userId != null) {
            query.where(QUserRemote.userRemote.userId.eq(userId));
        }
        if (firstName != null) {
            query.where(QUserRemote.userRemote.firstName.eq(firstName));
        }
        if (lastName != null) {
            query.where(QUserRemote.userRemote.lastName.eq(lastName));
        }
        List<UserRemote> rows =  query.from(userRemote).stream().toList();

        return new ResponseEntity<>(rows, HttpStatus.OK);
    }

    public ResponseEntity<Object> saveUserRemote(UserRemote userRemote){
        HttpHeaders headers = new HttpHeaders();
        if (userRemote == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            save(userRemote);
            return new ResponseEntity<>(userRemote, headers, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<UserRemote> patchUserRemote(UUID userId, UserRemote userRemote){
        HttpHeaders headers = new HttpHeaders();
        if (userRemote == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(patchUser(userId, userRemote), headers, HttpStatus.OK);
    }

    public ResponseEntity<UserRemote> saveUserRemoteInvite(){
        HttpHeaders headers = new HttpHeaders();
        UserRemote userRemote = new UserRemote();
        userRemote.setDefault();
        this.userRemoteRepository.save(userRemote);
        return new ResponseEntity<>(userRemote, headers, HttpStatus.CREATED);
    }
    public ResponseEntity<Object> deleteUserRemote( UUID userId){

        UserRemote userRemote = this.userRemoteRepository.getById(userId);
        ResponseEntity<Object> response;
        if (userRemote == null){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            this.userRemoteRepository.deleteById(userId);
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

}
