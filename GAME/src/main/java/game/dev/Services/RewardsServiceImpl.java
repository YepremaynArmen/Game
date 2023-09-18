package game.dev.Services;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import game.dev.Models.QRewards;
import game.dev.Models.Rewards;
import game.dev.Repositories.RewardsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import jakarta.persistence.EntityManager;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@Slf4j
public class RewardsServiceImpl implements RewardsService {

    private final EntityManager entityManager;
    @Autowired
    RewardsRepository rewardsRepository;

    private JPAQueryFactory queryFactory;

    public RewardsServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Rewards getById(Long id) {
        log.info("IN rewardsRepository getById {}", id);
        return rewardsRepository.findById(id).get();
    }

    @Override
    public void save(Rewards rewards) {
        log.info("IN rewardsRepository save {}", rewards);
        rewardsRepository.save(rewards);
    }


    public Rewards patchRewards(Long id, Rewards rewards) {

        Rewards rewardsModif = getById(id);

        if (rewards.getCurrencyId()!= null) {
            rewardsModif.setCurrencyId(rewards.getCurrencyId());
        }
        if (rewards.getValue()!= null) {
            rewardsModif.setValue(rewards.getValue());
        }
        if (StringUtils.hasLength(rewards.getStatus())) {
            rewardsModif.setStatus(rewards.getStatus());
        }
        if (rewards.getQuestionId()!= null) {
            rewardsModif.setQuestionId(rewards.getQuestionId());
        }
        if (rewards.getCreatedAt()!= null) {
            rewardsModif.setCreatedAt(rewards.getCreatedAt());
        }
        if (rewards.getChangeAt()!= null) {
            rewardsModif.setChangeAt(rewards.getChangeAt());
        }
        if (rewards.getChangeBy() != null) {
            rewardsModif.setChangeBy(rewards.getChangeBy());
        }
        if (rewards.getValue()!= null) {
            rewardsModif.setValue(rewards.getValue());
        }

        rewardsRepository.save(rewardsModif);
        log.info("IN rewardsRepository save {}", rewardsModif);
        return rewardsModif;
    }

    @Override
    public void deleteById(Long id) {
        log.info("IN rewardsRepository delete {}", id);
        rewardsRepository.deleteById(id);
    }

    @Override
    public List<Rewards> getAll() {
        log.info("IN rewardsRepository getAll");
        return rewardsRepository.findAll();
    }

    public ResponseEntity<Rewards> getRewardsById(@PathVariable("id") Long id){
        if (id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Rewards rewards = getById(id);
        if (rewards == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rewards, HttpStatus.OK);
    }

    public ResponseEntity<List<Rewards>> findAllBySpecification(Long id) {
        queryFactory = new JPAQueryFactory(entityManager);
        QRewards rewards = QRewards.rewards;
        JPAQuery query = new JPAQuery(entityManager);
        if (id != null) {
            query.where(rewards.id.eq(id));
        }
        List<Rewards> rows =  query.from(rewards).stream().toList();
        ResponseEntity<List<Rewards>> response;
        response = new ResponseEntity<>(rows, HttpStatus.OK);
        return response ;
    }

    public ResponseEntity<Rewards> saveRewards(Rewards rewards){
        HttpHeaders headers = new HttpHeaders();
        if (rewards == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        save(rewards);

        return new ResponseEntity<>(rewards, headers, HttpStatus.CREATED);
    }

    public ResponseEntity<Rewards> updateRewards(Long id, Rewards rewards){
        HttpHeaders headers = new HttpHeaders();
        if (rewards == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Rewards rewardsModif =  patchRewards(id, rewards);

        return new ResponseEntity<>(rewardsModif, headers, HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteRewards(Long id){

        Rewards rewards = getById(id);
        ResponseEntity<Object> response;
        if (rewards == null){
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
