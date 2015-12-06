package gamepub.db.service;

import gamepub.db.dao.implementation.UserGameDaoImplementation;
import gamepub.db.entity.UserGame;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by roman on 06.12.15.
 */
@Stateless
public class UserGameService extends UserGameDaoImplementation {
    @PersistenceContext(unitName = "PERSISTENCE_WEB")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected void closeEntityManager() {
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public UserGame getUserGameById(Integer id) {
        return super.getUserGameById(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public UserGame getUserGameByUserIdAndGameId(Integer userId, Integer gameId) {
        return super.getUserGameByUserIdAndGameId(userId, gameId);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<UserGame> getUserGamesByUserId(Integer userId) {
        return super.getUserGamesByUserId(userId);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<UserGame> getUserGamesByUserId(Integer userId, Integer gameStatusId) {
        return super.getUserGamesByUserId(userId, gameStatusId);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<UserGame> getUserGamesByGameId(Integer gameId) {
        return super.getUserGamesByGameId(gameId);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<UserGame> getUserGamesByGameId(Integer gameId, Integer gameStatusId) {
        return super.getUserGamesByGameId(gameId, gameStatusId);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<UserGame> getFavoriteUserGamesByUserId(Integer userId) {
        return super.getFavoriteUserGamesByUserId(userId);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<UserGame> getFavoriteUserGamesByGameId(Integer gameId) {
        return super.getFavoriteUserGamesByGameId(gameId);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<UserGame> getWantedUserGamesByUserId(Integer userId) {
        return super.getWantedUserGamesByUserId(userId);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<UserGame> getWantedUserGamesByGameId(Integer gameId) {
        return super.getWantedUserGamesByGameId(gameId);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<UserGame> getCanExchangeUserGamesByUserId(Integer userId) {
        return super.getCanExchangeUserGamesByUserId(userId);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<UserGame> getCanExchangeUserGamesByGameId(Integer gameId) {
        return super.getCanExchangeUserGamesByGameId(gameId);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public UserGame create(UserGame userGame) {
        return super.create(userGame);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public UserGame find(Integer id) {
        return super.find(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public UserGame update(UserGame userGame) {
        return super.update(userGame);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<UserGame> findAll() {
        return super.findAll();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(List<UserGame> t) {
        super.delete(t);
    }
}
