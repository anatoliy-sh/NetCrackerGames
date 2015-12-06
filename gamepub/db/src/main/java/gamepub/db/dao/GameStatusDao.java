package gamepub.db.dao;

import gamepub.db.entity.GameStatus;

/**
 * Created by roman on 06.12.15.
 */
public interface GameStatusDao extends BaseDao<GameStatus,Integer> {
    public GameStatus getGameStatusById(Integer id);
}
