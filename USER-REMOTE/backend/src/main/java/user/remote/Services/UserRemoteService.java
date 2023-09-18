package user.remote.Services;

import user.remote.Models.UserRemote;
import java.util.List;
import java.util.UUID;

public interface UserRemoteService {

    UserRemote getById(UUID userId);

    void save(UserRemote userRemote);

    UserRemote patchUser(UUID userId, UserRemote userRemote);

    void  deleteById(UUID userId);
    List<UserRemote> getAll();


}
