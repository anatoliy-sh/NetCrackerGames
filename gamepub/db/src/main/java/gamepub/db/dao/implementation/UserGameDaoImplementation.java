package gamepub.db.dao.implementation;

import gamepub.db.dao.UserGameDao;
import gamepub.db.entity.Game;
import gamepub.db.entity.GameStatus;
import gamepub.db.entity.User;
import gamepub.db.entity.UserGame;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<UserGame> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Integer>get("id"), id));
        try {
            return (UserGame)getEntityManager().createQuery(cq).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public UserGame getUserGameByUserIdAndGameId(Integer userId, Integer gameId) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<UserGame> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<User>get("user").<Integer>get("id"), userId),
                cb.equal(root.<Game>get("game").<Integer>get("id"), userId));
        try {
            return (UserGame)getEntityManager().createQuery(cq).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public List<UserGame> getUserGamesByUserId(Integer userId) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<UserGame> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<User>get("user").<Integer>get("id"), userId));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<UserGame> getUserGamesByUserId(Integer userId, Integer gameStatusId) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<UserGame> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<User>get("user").<Integer>get("id"), userId),
                cb.equal(root.<GameStatus>get("gameStatus").<Integer>get("id"), gameStatusId));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<UserGame> getUserGamesByGameId(Integer gameId) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<UserGame> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Game>get("game").<Integer>get("id"), gameId));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<UserGame> getUserGamesByGameId(Integer gameId, Integer gameStatusId) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<UserGame> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Game>get("game").<Integer>get("id"), gameId),
                cb.equal(root.<GameStatus>get("gameStatus").<Integer>get("id"), gameStatusId));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<UserGame> getFavoriteUserGamesByUserId(Integer userId) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<UserGame> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<User>get("user").<Integer>get("id"), userId),
                cb.equal(root.<Boolean>get("favorite"),true));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<UserGame> getFavoriteUserGamesByGameId(Integer gameId) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<UserGame> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Game>get("game").<Integer>get("id"), gameId),
                cb.equal(root.<Boolean>get("favorite"), true));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<UserGame> getWantedUserGamesByUserId(Integer userId) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<UserGame> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<User>get("user").<Integer>get("id"), userId),
                cb.equal(root.<Boolean>get("wanted"), true));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<UserGame> getWantedUserGamesByGameId(Integer gameId) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<UserGame> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Game>get("game").<Integer>get("id"), gameId),
                cb.equal(root.<Boolean>get("wanted"), true));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<UserGame> getCanExchangeUserGamesByUserId(Integer userId) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<UserGame> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<User>get("user").<Integer>get("id"), userId),
                cb.equal(root.<Boolean>get("canExchange"), true));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<UserGame> getCanExchangeUserGamesByGameId(Integer gameId) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<UserGame> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Game>get("game").<Integer>get("id"), gameId),
                cb.equal(root.<Boolean>get("canExchange"), true));
        return getEntityManager().createQuery(cq).getResultList();
    }
}
