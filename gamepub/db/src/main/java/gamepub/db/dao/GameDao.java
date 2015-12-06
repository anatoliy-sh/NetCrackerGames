package gamepub.db.dao;

import gamepub.db.entity.Game;

import java.util.List;

/**
 * Created by roman on 06.12.15.
 */
public interface GameDao extends BaseDao<Game,Integer> {
    public Game getGameById(Integer id);
    public List<Game> getGamesByName(String name);
}
