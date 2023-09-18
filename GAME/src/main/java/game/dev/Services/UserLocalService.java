package game.dev.Services;


import game.dev.Models.UserLocal;

import java.util.List;
import java.util.UUID;

public interface UserLocalService {


    UserLocal getById(UUID userId);

    void save(UserLocal userLocal);
    public UserLocal patchUserLocal(UUID userId, UserLocal userLocal);
    void  delete(UUID userId);
    List<UserLocal> getAll();

}
