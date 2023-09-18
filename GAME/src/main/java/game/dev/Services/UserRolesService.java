package game.dev.Services;

import game.dev.Models.UserRoles;

import java.util.List;


public interface UserRolesService {


    UserRoles getById(Long roleId);

    void save(UserRoles userLocal);
    public UserRoles patchUserRoles(Long roleId, UserRoles userRoles);
    void  delete(Long roleId);
    List<UserRoles> getAll();


}
