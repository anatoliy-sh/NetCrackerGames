package gamepub.db.dao;

import gamepub.db.entity.Screenshot;

import java.util.List;

/**
 * Created by roman on 05.12.15.
 */
public interface ScreenshotDao extends BaseDao<Screenshot, Integer> {
    public Screenshot getScreenshotById(Integer id);
    public List<Screenshot> getScreenshotsByUserId(Integer id);
}
