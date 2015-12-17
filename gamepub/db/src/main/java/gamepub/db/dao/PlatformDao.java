package gamepub.db.dao;

import gamepub.db.entity.Platform;

/**
 * Created by roman on 06.12.15.
 */
public interface PlatformDao extends BaseDao<Platform,Integer> {
    public Platform getPlatformById(Integer id);
    public Platform getPlatformByName(String name);
}
