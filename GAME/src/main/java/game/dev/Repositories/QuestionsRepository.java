package game.dev.Repositories;

import game.dev.Models.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<Questions, Long>{

}

