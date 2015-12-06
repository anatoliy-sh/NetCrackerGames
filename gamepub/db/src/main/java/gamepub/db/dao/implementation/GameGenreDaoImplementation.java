package gamepub.db.dao.implementation;

import gamepub.db.dao.GameGenreDao;
import gamepub.db.entity.GameGenre;

import java.util.HashMap;
import java.util.List;

/**
 * Created by roman on 06.12.15.
 */
public class GameGenreDaoImplementation extends BaseDaoImplementation<GameGenre,Integer> implements GameGenreDao {
    public GameGenreDaoImplementation(){
        super(GameGenre.class);
    }

    public GameGenre getGameGenreById(Integer id) {
        String jpa = "SELECT g FROM GameGenre g WHERE g.id= :id";
        HashMap<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        try {
            return this.ExecuteQuery(jpa, parameters).get(0);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<GameGenre> getGameGenresByGameId(Integer id) {
        String jpa = "SELECT g FROM GameGenre g WHERE g.game.id= :id";
        HashMap<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        return this.ExecuteQuery(jpa, parameters);
    }

    public List<GameGenre> getGameGenresByGenreId(Integer id) {
        String jpa = "SELECT g FROM GameGenre g WHERE g.genre.id= :id";
        HashMap<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        return this.ExecuteQuery(jpa, parameters);
    }
}
