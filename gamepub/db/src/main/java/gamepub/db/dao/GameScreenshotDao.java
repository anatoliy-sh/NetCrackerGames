package gamepub.db.dao;

import gamepub.db.entity.GameScreenshot;

import java.util.List;

/**
 * Created by roman on 17.12.15.
 */
public interface GameScreenshotDao extends BaseDao<GameScreenshot, Integer> {
    public GameScreenshot getScreenshotById(Integer id);
    public List<GameScreenshot> getScreenshotsByGameId(Integer id);
}
