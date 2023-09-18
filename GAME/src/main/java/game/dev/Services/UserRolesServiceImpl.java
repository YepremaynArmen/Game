package game.dev.Services;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import game.dev.Models.QUserRoles;
import game.dev.Models.UserRoles;
import game.dev.Repositories.UserRolesRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;

@Service
@Slf4j
public class UserRolesServiceImpl implements UserRolesService {

    private final EntityManager entityManager;
    @Autowired
    UserRolesRepository userRolesRepository;

    private JPAQueryFactory queryFactory;

    public UserRolesServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public UserRoles getById(Long roleId) {
        log.info("IN UserRolesServiceImpl getById {}", roleId);
        return userRolesRepository.findById(roleId).get();
    }

    @Override
    public void save(UserRoles userLocal) {
        log.info("IN UserRolesServiceImpl save {}", userLocal);
        userRolesRepository.save(userLocal);
    }

    public UserRoles patchUserRoles(Long roleId, UserRoles userRoles) {

        UserRoles userRolesModif = userRolesRepository.getById(roleId);

        if (userRoles.getUserId()!= null) {
            userRolesModif.setUserId(userRoles.getUserId());
        }
        if (StringUtils.hasLength(userRoles.getRoleName())) {
            userRolesModif.setRoleName(userRoles.getRoleName());
        }

        userRolesRepository.save(userRolesModif);
        log.info("IN userRolesService save {}", userRolesModif);
        return userRolesModif;
    }


    @Override
    public void delete(Long roleId) {
        log.info("IN UserRolesServiceImpl delete {}", roleId);
        userRolesRepository.deleteById(roleId);
    }

    @Override
    public List<UserRoles> getAll() {
        log.info("IN LessonServiceImpl getAll");
        return userRolesRepository.findAll();
    }

    public ResponseEntity<UserRoles> getUserRoles(Long Id){
        if (Id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserRoles userRoles = getById(Id);
        if (userRoles == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userRoles, HttpStatus.OK);
    }

    public ResponseEntity<List<UserRoles>> findAllBySpecification(Long Id
    ) {
        queryFactory = new JPAQueryFactory(entityManager);
        QUserRoles userRoles = QUserRoles.userRoles;
        JPAQuery query = new JPAQuery(entityManager);
        if (Id != null) {
            query.where(userRoles.roleId.eq(Id));
        }
        List<UserRoles> rows =  query.from(userRoles).stream().toList();
        ResponseEntity<List<UserRoles>> response;
        response = new ResponseEntity<>(rows, HttpStatus.OK);
        return response ;
    }

    public ResponseEntity<UserRoles> saveUserRoles(UserRoles userRoles){
        HttpHeaders headers = new HttpHeaders();
        if (userRoles == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        save(userRoles);

        return new ResponseEntity<>(userRoles, headers, HttpStatus.CREATED);
    }

    public ResponseEntity<UserRoles> updateUserRoles(Long id, UserRoles userRoles){
        HttpHeaders headers = new HttpHeaders();
        if (userRoles == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserRoles userRolesModif =  patchUserRoles(id, userRoles);

        return new ResponseEntity<>(userRolesModif, headers, HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteUserRoles(Long userId){

        UserRoles userRoles = getById(userId);
        ResponseEntity<Object> response;
        if (userRoles == null){
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
