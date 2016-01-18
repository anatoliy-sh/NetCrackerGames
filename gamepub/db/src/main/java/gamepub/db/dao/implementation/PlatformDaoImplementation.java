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

    public Platform getPlatformByName(String name) {
        String jpa = "SELECT p FROM Platform p WHERE p.name= :name";
        HashMap<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("name",name);
        try
        {
            return this.ExecuteQuery(jpa, parameters).get(0);
        }catch (Exception e)
        {
            return null;
        }
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
            return null;
        }


    }
}
