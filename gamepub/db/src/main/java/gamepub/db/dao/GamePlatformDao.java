package gamepub.db.dao;

import gamepub.db.entity.GamePlatform;

import java.util.List;

/**
 * Created by roman on 06.12.15.
 */
public interface GamePlatformDao extends BaseDao<GamePlatform,Integer> {
    public GamePlatform getGamePlatformById(Integer id);
    public List<GamePlatform> getGamePlatformsByGameId(Integer id);
    public List<GamePlatform> getGamePlatformByPlatformId(Integer id);
}
