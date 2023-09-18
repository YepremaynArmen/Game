package user.remote.Repositories;

import user.remote.Models.UserRemote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRemoteRepository extends JpaRepository<UserRemote, UUID>{

    UserRemote getById(UUID userId);


}

