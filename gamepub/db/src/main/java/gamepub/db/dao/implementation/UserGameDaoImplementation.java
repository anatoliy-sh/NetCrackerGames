package gamepub.db.dao.implementation;

import gamepub.db.dao.UserGameDao;
import gamepub.db.entity.UserGame;

import java.util.HashMap;
import java.util.List;

/**
 * Created by roman on 06.12.15.
 */
public class UserGameDaoImplementation extends BaseDaoImplementation<UserGame,Integer> implements UserGameDao {
    public UserGameDaoImplementation(){
        super(UserGame.class);
    }

    public UserGame getUserGameById(Integer id) {
        String jpa = "SELECT u FROM UserGame u WHERE u.id= :id";
        HashMap<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        try {
            return this.ExecuteQuery(jpa, parameters).get(0);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public UserGame getUserGameByUserIdAndGameId(Integer userId, Integer gameId) {
        String jpa = "SELECT u FROM UserGame u WHERE u.user.id= :userId AND " +
                "u.game.id= :gameId";
        HashMap<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("userId",userId);
        parameters.put("gameId",gameId);
        try {
            return this.ExecuteQuery(jpa, parameters).get(0);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<UserGame> getUserGamesByUserId(Integer userId) {
        String jpa = "SELECT u FROM UserGame u WHERE u.user.id = :id ORDER BY u.game.name";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",userId);
        return this.ExecuteQuery(jpa, parameters);
    }

    public List<UserGame> getUserGamesByUserId(Integer userId, Integer gameStatusId) {
        String jpa = "SELECT u FROM UserGame u WHERE u.user.id = :id AND " +
                "u.gameStatus.id= :gameStatusId ORDER BY u.game.name";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",userId);
        parameters.put("gameStatusId",gameStatusId);
        return this.ExecuteQuery(jpa, parameters);
    }

    public List<UserGame> getUserGamesByGameId(Integer gameId) {
        String jpa = "SELECT u FROM UserGame u WHERE u.game.id = :id ORDER BY u.game.name";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",gameId);
        return this.ExecuteQuery(jpa, parameters);
    }

    public List<UserGame> getUserGamesByGameId(Integer gameId, Integer gameStatusId) {
        String jpa = "SELECT u FROM UserGame u WHERE u.game.id = :id AND " +
                "u.gameStatus.id= :gameStatusId ORDER BY u.game.name";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",gameId);
        parameters.put("gameStatusId",gameStatusId);
        return this.ExecuteQuery(jpa, parameters);
    }

    public List<UserGame> getFavoriteUserGamesByUserId(Integer userId) {
        String jpa = "SELECT u FROM UserGame u WHERE u.user.id = :id AND u.isFavorite=true ORDER BY u.game.name";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",userId);
        return this.ExecuteQuery(jpa, parameters);
    }

    public List<UserGame> getFavoriteUserGamesByGameId(Integer gameId) {
        String jpa = "SELECT u FROM UserGame u WHERE u.game.id = :id AND u.isFavorite=true ORDER BY u.game.name";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",gameId);
        return this.ExecuteQuery(jpa, parameters);
    }

    public List<UserGame> getWantedUserGamesByUserId(Integer userId) {
        String jpa = "SELECT u FROM UserGame u WHERE u.user.id = :id AND u.isWanted=true ORDER BY u.game.name";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",userId);
        return this.ExecuteQuery(jpa, parameters);
    }

    public List<UserGame> getWantedUserGamesByGameId(Integer gameId) {
        String jpa = "SELECT u FROM UserGame u WHERE u.game.id = :id AND u.isWanted=true ORDER BY u.game.name";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",gameId);
        return this.ExecuteQuery(jpa, parameters);
    }

    public List<UserGame> getCanExchangeUserGamesByUserId(Integer userId) {
        String jpa = "SELECT u FROM UserGame u WHERE u.user.id = :id AND u.isCanExchange=true ORDER BY u.game.name";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",userId);
        return this.ExecuteQuery(jpa, parameters);
    }

    public List<UserGame> getCanExchangeUserGamesByGameId(Integer gameId) {
        String jpa = "SELECT u FROM UserGame u WHERE u.game.id = :id AND u.isCanExchange=true ORDER BY u.game.name";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",gameId);
        return this.ExecuteQuery(jpa, parameters);
    }
}
