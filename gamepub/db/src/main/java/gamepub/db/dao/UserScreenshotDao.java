package gamepub.db.dao;

import gamepub.db.entity.UserScreenshot;

import java.util.List;

/**
 * Created by roman on 05.12.15.
 */
public interface UserScreenshotDao extends BaseDao<UserScreenshot, Integer> {
    public UserScreenshot getScreenshotById(Integer id);
    public List<UserScreenshot> getScreenshotsByUserId(Integer id);
}
