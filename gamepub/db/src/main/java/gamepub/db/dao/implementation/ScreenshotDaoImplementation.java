package gamepub.db.dao.implementation;

import gamepub.db.dao.ScreenshotDao;
import gamepub.db.entity.Screenshot;

import java.util.HashMap;
import java.util.List;

/**
 * Created by roman on 05.12.15.
 */
public class ScreenshotDaoImplementation extends BaseDaoImplementation<Screenshot,Integer> implements ScreenshotDao {
    public ScreenshotDaoImplementation(){
        super(Screenshot.class);
    }

    public Screenshot getScreenshotById(Integer id) {
        String jpa = "SELECT s FROM Screenshot s WHERE s.id = :id";
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

    public List<Screenshot> getScreenshotsByUserId(Integer id) {
        String jpa = "SELECT s FROM Screenshot s WHERE s.user.id = :id ORDER BY s.date DESC";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        return this.ExecuteQuery(jpa, parameters);
    }
}
