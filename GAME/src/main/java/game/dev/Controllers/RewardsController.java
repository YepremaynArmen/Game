package game.dev.Controllers;

import game.dev.Models.Rewards;
import game.dev.Services.RewardsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/rewards")
@CrossOrigin(origins = "*")
public class RewardsController {

    @Autowired
    private RewardsServiceImpl rewardsServiceImpl;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Rewards> getRewardsById(@PathVariable("id") Long id){
        return this.rewardsServiceImpl.getRewardsById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<List<Rewards>> findAllBySpecification(@RequestParam(name = "id",required=false) Long id
    ) {
        return this.rewardsServiceImpl.findAllBySpecification(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Rewards> saveRewards(@RequestBody @Valid Rewards rewards){
        return this.rewardsServiceImpl.saveRewards(rewards);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Rewards> updateRewards(@PathVariable("id") Long id
            , @RequestBody Rewards rewards){
        return this.rewardsServiceImpl.updateRewards(id, rewards);
    }    

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> deleteRewards(@PathVariable("id") Long id){
        return this.rewardsServiceImpl.deleteRewards(id);
    }
    
    
}
