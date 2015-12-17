package gamepub.db.dao.implementation;

import gamepub.db.dao.UserScreenshotDao;
import gamepub.db.entity.UserScreenshot;

import java.util.HashMap;
import java.util.List;

/**
 * Created by roman on 05.12.15.
 */
public class UserScreenshotDaoImplementation extends BaseDaoImplementation<UserScreenshot,Integer> implements UserScreenshotDao {
    public UserScreenshotDaoImplementation(){
        super(UserScreenshot.class);
    }

    public UserScreenshot getScreenshotById(Integer id) {
        String jpa = "SELECT s FROM UserScreenshot s WHERE s.id = :id";
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

    public List<UserScreenshot> getScreenshotsByUserId(Integer id) {
        String jpa = "SELECT s FROM UserScreenshot s WHERE s.user.id = :id ORDER BY s.date DESC";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        return this.ExecuteQuery(jpa, parameters);
    }
}
