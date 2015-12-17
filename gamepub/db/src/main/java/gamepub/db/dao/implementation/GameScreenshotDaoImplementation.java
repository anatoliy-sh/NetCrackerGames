package gamepub.db.dao.implementation;

import gamepub.db.dao.GameScreenshotDao;
import gamepub.db.entity.GameScreenshot;

import java.util.HashMap;
import java.util.List;

/**
 * Created by roman on 17.12.15.
 */
public class GameScreenshotDaoImplementation extends BaseDaoImplementation<GameScreenshot,Integer> implements GameScreenshotDao {
    public GameScreenshotDaoImplementation(){
        super(GameScreenshot.class);
    }

    public GameScreenshot getScreenshotById(Integer id) {
        String jpa = "SELECT s FROM GameScreenshot s WHERE s.id = :id";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        try
        {
            return this.ExecuteQuery(jpa, parameters).get(0);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public List<GameScreenshot> getScreenshotsByGameId(Integer id) {
        String jpa = "SELECT s FROM UserScreenshot s WHERE s.game.id = :id ORDER BY s.date DESC";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        return this.ExecuteQuery(jpa, parameters);
    }
}
