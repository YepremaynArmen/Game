package user.remote.Controllers;

import jakarta.persistence.EntityManager;
import user.remote.Models.UserRemote;
import user.remote.Services.UserRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.remote.Services.UserRemoteServiceImpl;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/userPersonals")
@CrossOrigin(origins = "*")
public class UserRemoteController {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRemoteService userRemoteService;
    @Autowired
    private UserRemoteServiceImpl userRemoteServiceImpl;

    @RequestMapping(value = "{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  ResponseEntity<Object>  getUserRemoteById(@PathVariable("userId") UUID userId
    ) {
        return this.userRemoteServiceImpl.getUserRemoteById(userId);
    }

    @RequestMapping(value = "/status/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getUserRemoteStatus(@PathVariable("userId") UUID userId){
        if (userId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return this.userRemoteServiceImpl.getUserRemoteStatus(userId);
    }


    @RequestMapping(value = "/aprove/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> aproveUserRemoteStatus(@PathVariable("userId") UUID userId){
        return this.userRemoteServiceImpl.aproveUserRemoteStatus(userId);
    }



    @RequestMapping(method = RequestMethod.GET, value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<UserRemote>> findAllBySpecification(@RequestParam(name = "userId",required=false) UUID userId
            , @RequestParam(name = "firstName", required=false)String firstName
            , @RequestParam(name = "lastName", required=false)String lastName
    ) {
        return this.userRemoteServiceImpl.findAllBySpecification(userId, firstName, lastName);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> saveUserRemote(@RequestBody  UserRemote userRemote){
        return this.userRemoteServiceImpl.saveUserRemote(userRemote);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserRemote> patchUserRemote(@PathVariable("userId") UUID userId,
                                                      @RequestBody UserRemote userRemote
    ){
        return this.userRemoteServiceImpl.patchUserRemote(userId, userRemote);
    }

    @RequestMapping(value = "/invite", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserRemote> saveUserRemoteInvite(){
        return this.userRemoteServiceImpl.saveUserRemoteInvite();
    }


    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> deleteUserRemote(@PathVariable("userId") UUID userId){
        return this.userRemoteServiceImpl.deleteUserRemote(userId);
    }

}