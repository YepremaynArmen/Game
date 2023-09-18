package game.dev.Controllers;

import game.dev.Models.UserRoles;
import game.dev.Services.UserRolesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/userRoles")
@CrossOrigin(origins = "*")
public class UserRolesController {

    @Autowired
    private UserRolesServiceImpl userRolesServiceIpml;

    @RequestMapping(value = "{roleId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<UserRoles> getUserRoles(@PathVariable("roleId") @Valid Long roleId){
        return userRolesServiceIpml.getUserRoles(roleId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<List<UserRoles>> findAllBySpecification(@RequestParam(name = "roleId",required=false) Long roleId
    ) {
        return userRolesServiceIpml.findAllBySpecification(roleId) ;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserRoles> saveUserRoles(@RequestBody @Valid UserRoles userRoles){
        return userRolesServiceIpml.saveUserRoles(userRoles) ;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserRoles> updateUserRoles(@PathVariable("id") Long id
            , @RequestBody UserRoles userRoles){
        return userRolesServiceIpml.updateUserRoles(id, userRoles) ;
    }    

    @RequestMapping(value = "/{userRolesId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> deleteUserRoles(@PathVariable("userId") Long id){
        return userRolesServiceIpml.deleteUserRoles(id) ;
    }    
    
    
}
