package notification.sender.Repositories;

import notification.sender.Models.Letters;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LettersRepository extends JpaRepository<Letters, Long>{

    Letters getById(Long id);


}

