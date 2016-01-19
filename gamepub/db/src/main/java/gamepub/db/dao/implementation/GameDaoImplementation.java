package gamepub.db.dao.implementation;

import gamepub.db.dao.GameDao;
import gamepub.db.entity.Game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by roman on 06.12.15.
 */
public class GameDaoImplementation extends BaseDaoImplementation<Game, Integer> implements GameDao {
    public GameDaoImplementation() {
        super(Game.class);
    }

    public Game getGameById(Integer id) {
        String jpa = "SELECT g FROM Game g WHERE g.id= :id";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", id);
        try {
            return this.ExecuteQuery(jpa, parameters).get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Game> getGamesByName(String name) {
        String jpa = "SELECT g FROM Game g WHERE g.name= :name";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("name", name);
        return this.ExecuteQuery(jpa, parameters);
    }

    public List<Game> getGamesByCustomParams(List<HashMap.Entry<String, Object>> parameterList) {
        String jpa = "Select DISTINCT g.game FROM GameGenre g, GamePlatform gp WHERE gp.game=g.game";
        if (parameterList.size() == 0) {
            return this.ExecuteQuery(jpa);
        }
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        for (HashMap.Entry<String, Object> param : parameterList) {
            if (param.getKey().equals("name")) {
                jpa += " AND g.game.name LIKE :name";
                parameters.put(param.getKey(), "%" + param.getValue() + "%");
            } else {
                if (param.getKey().equals("platform")) {
                    jpa += " AND gp.platform= :platform";
                } else if (param.getKey().equals("genre")) {
                    jpa += " AND g.genre= :genre";
                } else jpa += " AND g.game.date<= :date";
                parameters.put(param.getKey(),param.getValue());
            }
        }
        return this.ExecuteQuery(jpa, parameters);
    }

    public List<Game> getGamesOrderByMarks(int maxValue){
        String jpa = "SELECT g FROM Game g ORDER BY g.metacritic, g.releaseDate";
        try {
            return this.ExecuteQuery(jpa,maxValue);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
