package game.dev.Services;

import game.dev.Models.Rewards;

import java.util.List;

public interface RewardsService {


    Rewards getById(Long id);

    void save(Rewards questions);
    public Rewards patchRewards(Long id, Rewards rewards);
    void  deleteById(Long id);
    List<Rewards> getAll();

}
