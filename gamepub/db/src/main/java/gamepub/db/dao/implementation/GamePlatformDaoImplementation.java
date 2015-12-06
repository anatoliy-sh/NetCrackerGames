package gamepub.db.dao.implementation;

import gamepub.db.dao.GamePlatformDao;
import gamepub.db.entity.GamePlatform;

import java.util.HashMap;
import java.util.List;

/**
 * Created by roman on 06.12.15.
 */
public class GamePlatformDaoImplementation extends BaseDaoImplementation<GamePlatform,Integer> implements GamePlatformDao {
    public GamePlatformDaoImplementation(){
        super(GamePlatform.class);
    }

    public GamePlatform getGamePlatformById(Integer id) {
        String jpa = "SELECT g FROM GamePlatform g WHERE g.id= :id";
        HashMap<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        try {
            return this.ExecuteQuery(jpa, parameters).get(0);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<GamePlatform> getGamePlatformsByGameId(Integer id) {
        String jpa = "SELECT g FROM GamePlatform g WHERE g.game.id= :id";
        HashMap<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        return this.ExecuteQuery(jpa, parameters);

    }

    public List<GamePlatform> getGamePlatformByPlatformId(Integer id) {
        String jpa = "SELECT g FROM GamePlatform g WHERE g.platform.id= :id";
        HashMap<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        return this.ExecuteQuery(jpa, parameters);
    }
}
