package gamepub.db.dao;

import gamepub.db.entity.UserGame;

import java.util.List;

/**
 * Created by roman on 06.12.15.
 */
public interface UserGameDao extends BaseDao<UserGame,Integer> {
    public UserGame getUserGameById(Integer id);
    public UserGame getUserGameByUserIdAndGameId(Integer userId, Integer gameId);

    public List<UserGame> getUserGamesByUserId(Integer userId);
    public List<UserGame> getUserGamesByUserId(Integer userId, Integer gameStatusId);
    public List<UserGame> getUserGamesByGameId(Integer gameId);
    public List<UserGame> getUserGamesByGameId(Integer gameId, Integer gameStatusId);

    public List<UserGame> getFavoriteUserGamesByUserId(Integer userId);
    public List<UserGame> getFavoriteUserGamesByGameId(Integer gameId);

    public List<UserGame> getWantedUserGamesByUserId(Integer userId);
    public List<UserGame> getWantedUserGamesByGameId(Integer gameId);

    public List<UserGame> getCanExchangeUserGamesByUserId(Integer userId);
    public List<UserGame> getCanExchangeUserGamesByGameId(Integer gameId);
}
