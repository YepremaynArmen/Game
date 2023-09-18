package game.dev.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import game.dev.Models.UserLocal;

import java.util.UUID;

public interface UserLocalRepository extends JpaRepository<UserLocal, UUID>{

    UserLocal getOne(UUID userId);

    void deleteById(UUID userId);
}

