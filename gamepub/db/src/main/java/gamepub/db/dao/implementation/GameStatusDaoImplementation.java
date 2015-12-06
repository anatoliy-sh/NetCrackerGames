package gamepub.db.dao.implementation;

import gamepub.db.dao.GameStatusDao;
import gamepub.db.entity.GameStatus;

import java.util.HashMap;

/**
 * Created by roman on 06.12.15.
 */
public class GameStatusDaoImplementation extends BaseDaoImplementation<GameStatus,Integer> implements GameStatusDao {
    public GameStatusDaoImplementation(){
        super(GameStatus.class);
    }

    public GameStatus getGameStatusById(Integer id) {
        String jpa = "SELECT g FROM GameStatus g WHERE g.id= :id";
        HashMap<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        try {
            return this.ExecuteQuery(jpa, parameters).get(0);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
