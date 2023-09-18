package game.dev.Services;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import game.dev.Models.QUserLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import jakarta.persistence.*;
import game.dev.Models.UserLocal;
import game.dev.Repositories.UserLocalRepository;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserLocalServiceImpl implements UserLocalService {

    private final EntityManager entityManager;
    @Autowired
    UserLocalRepository userLocalRepository;
    private JPAQueryFactory queryFactory;
    public UserLocalServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public UserLocal getById(UUID userId) {
        log.info("IN UserLocalServiceImpl getById {}", userId);
        return userLocalRepository.findById(userId).get();
    }

    @Override
    public void save(UserLocal userLocal) {
        log.info("IN UserLocalServiceImpl save {}", userLocal);
        userLocalRepository.save(userLocal);
    }

    public UserLocal patchUserLocal(UUID userId, UserLocal userLocal) {

        UserLocal userLocalModif = userLocalRepository.getOne(userId);

        if (userLocal.getAvatar()!= null) {
            userLocalModif.setAvatar(userLocal.getAvatar());
        }
        if (userLocal.getUpdateDate()!= null) {
            userLocalModif.setUpdateDate(userLocal.getUpdateDate());
        }
        if (StringUtils.hasLength(userLocal.getLogin())) {
            userLocalModif.setLogin(userLocal.getLogin());
        }
        if (StringUtils.hasLength(userLocal.getPassword())) {
            userLocalModif.setPassword(userLocal.getPassword());
        }
        if (StringUtils.hasLength(userLocal.getDescription())) {
            userLocalModif.setDescription(userLocal.getDescription());
        }

        userLocalRepository.save(userLocalModif);
        log.info("IN userLocalRepository save {}", userLocalModif);
        return userLocalModif;
    }
    
    
    @Override
    public void delete(UUID userId) {
        log.info("IN UserLocalServiceImpl delete {}", userId);
        userLocalRepository.deleteById(userId);
    }

    @Override
    public List<UserLocal> getAll() {
        log.info("IN LessonServiceImpl getAll");
        return userLocalRepository.findAll();
    }

    public ResponseEntity<Object> getUserRemoteById(UUID userId) {
        log.info("getUserRemoteById(UUID userId)");
        if (userId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserLocal userLocal = getById(userId);
        if (userLocal == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userLocal, HttpStatus.OK);
    }

    public ResponseEntity<List<UserLocal>> findAllBySpecification( UUID userId
    ) {
        queryFactory = new JPAQueryFactory(entityManager);
        QUserLocal userLocal = QUserLocal.userLocal;
        JPAQuery query = new JPAQuery(entityManager);
        if (userId != null) {
            query.where(userLocal.userId.eq(userId));
        }
        List<UserLocal> rows =  query.from(userLocal).stream().toList();
        ResponseEntity<List<UserLocal>> response;
        response = new ResponseEntity<>(rows, HttpStatus.OK);
        return response ;
    }

    public ResponseEntity<UserLocal> saveUserLocal(UserLocal userLocal){
        HttpHeaders headers = new HttpHeaders();
        if (userLocal == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        //  this.userLocalRepository.save(userLocal);

        return new ResponseEntity<>(userLocal, headers, HttpStatus.CREATED);
    }

    public ResponseEntity<UserLocal> updateUserLocal(UUID userId, UserLocal userLocal){
        HttpHeaders headers = new HttpHeaders();
        if (userLocal == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserLocal userLocalModif =  patchUserLocal(userId, userLocal);

        return new ResponseEntity<>(userLocalModif, headers, HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteUserLocal(UUID userId){

        UserLocal userLocal = getById(userId);
        ResponseEntity<Object> response;
        if (userLocal == null){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            delete(userId);
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

}
