package gamepub.db.dao.implementation;

import gamepub.db.dao.GameDao;
import gamepub.db.entity.Game;

import java.util.HashMap;
import java.util.List;

/**
 * Created by roman on 06.12.15.
 */
public class GameDaoImplementation extends BaseDaoImplementation<Game,Integer> implements GameDao {
    public GameDaoImplementation(){
        super(Game.class);
    }

    public Game getGameById(Integer id) {
        String jpa = "SELECT g FROM Game g WHERE g.id= :id";
        HashMap<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        try {
            return this.ExecuteQuery(jpa, parameters).get(0);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Game> getGamesByName(String name) {
        String jpa = "SELECT g FROM Game g WHERE g.name= :name";
        HashMap<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("name",name);
        return this.ExecuteQuery(jpa, parameters);
    }
}
