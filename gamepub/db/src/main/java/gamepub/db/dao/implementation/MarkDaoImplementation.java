package gamepub.db.dao.implementation;

import gamepub.db.dao.MarkDao;
import gamepub.db.entity.Mark;

import java.util.HashMap;
import java.util.List;

/**
 * Created by roman on 06.12.15.
 */
public class MarkDaoImplementation extends BaseDaoImplementation<Mark,Integer> implements MarkDao {
    public MarkDaoImplementation(){
        super(Mark.class);
    }

    public Mark getMarkById(Integer id) {
        String jpa = "SELECT m FROM Mark m WHERE m.id= :id";
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

    public Mark getMarkByUserAndGameId(Integer userId, Integer gameId) {
        String jpa = "SELECT m FROM Mark m WHERE m.user.id= :idUser AND m.game.id= :idGame";
        HashMap<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("idUser", userId);
        parameters.put("idGame", gameId);
        try
        {
            return this.ExecuteQuery(jpa, parameters).get(0);
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<Mark> getMarksByUserId(Integer id) {
        String jpa = "SELECT m FROM Mark m WHERE m.user.id= :id";
        HashMap<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        return this.ExecuteQuery(jpa, parameters);
    }

    public List<Mark> getMarksByGameId(Integer id) {
        String jpa = "SELECT m FROM Mark m WHERE m.game.id= :id";
        HashMap<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        return this.ExecuteQuery(jpa, parameters);
    }
}
