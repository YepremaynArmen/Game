package game.dev.Controllers;

import game.dev.Models.UserLocal;
import game.dev.Services.UserLocalServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/userLocal")
@CrossOrigin(origins = "*")
public class UserLocalController {
    @Autowired
    private UserLocalServiceImpl userLocalServiceImpl;

    @RequestMapping(value = "{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> getUserLocals(@PathVariable("userId") UUID userId){
        return this.userLocalServiceImpl.getUserRemoteById(userId);

    }

    @RequestMapping(method = RequestMethod.GET, value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<List<UserLocal>> findAllBySpecification(@RequestParam(name = "userId",required=false) UUID userId
    ) {
        return this.userLocalServiceImpl.findAllBySpecification(userId);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserLocal> saveUserLocal(@RequestBody @Valid UserLocal userLocal){
        return this.userLocalServiceImpl.saveUserLocal(userLocal);
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserLocal> updateUserLocal(@PathVariable("userId") UUID userId
            , @RequestBody UserLocal userLocal){
        return this.userLocalServiceImpl.updateUserLocal(userId, userLocal);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> deleteUserLocal(@PathVariable("userId") UUID userId){
        return this.userLocalServiceImpl.deleteUserLocal(userId);
    }

}
