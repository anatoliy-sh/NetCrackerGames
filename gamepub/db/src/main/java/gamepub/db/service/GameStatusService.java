package gamepub.db.service;

import gamepub.db.dao.implementation.GameStatusDaoImplementation;
import gamepub.db.entity.GameStatus;

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
public class GameStatusService extends GameStatusDaoImplementation {
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
    public GameStatus getGameStatusById(Integer id) {
        return super.getGameStatusById(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public GameStatus create(GameStatus gameStatus) {
        return super.create(gameStatus);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public GameStatus find(Integer id) {
        return super.find(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public GameStatus update(GameStatus gameStatus) {
        return super.update(gameStatus);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<GameStatus> findAll() {
        return super.findAll();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(List<GameStatus> t) {
        super.delete(t);
    }
}
