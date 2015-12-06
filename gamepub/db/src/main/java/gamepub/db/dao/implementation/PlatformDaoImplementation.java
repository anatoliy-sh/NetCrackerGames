package gamepub.db.dao.implementation;

import gamepub.db.dao.PlatformDao;
import gamepub.db.entity.Platform;

import java.util.HashMap;

/**
 * Created by roman on 06.12.15.
 */
public class PlatformDaoImplementation extends BaseDaoImplementation<Platform,Integer> implements PlatformDao {
    public PlatformDaoImplementation(){
        super(Platform.class);
    }

    public Platform getPlatformById(Integer id) {
        String jpa = "SELECT p FROM Platform p WHERE p.id= :id";
        HashMap<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        try
        {
            return this.ExecuteQuery(jpa, parameters).get(0);
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
