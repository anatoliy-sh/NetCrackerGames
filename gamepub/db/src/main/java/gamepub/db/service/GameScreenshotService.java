package gamepub.db.service;

import gamepub.db.dao.implementation.GameScreenshotDaoImplementation;
import gamepub.db.entity.GameScreenshot;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by roman on 17.12.15.
 */
@Stateless
public class GameScreenshotService extends GameScreenshotDaoImplementation {
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
    public GameScreenshot getScreenshotById(Integer id) {
        return super.getScreenshotById(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<GameScreenshot> getScreenshotsByGameId(Integer id) {
        return super.getScreenshotsByGameId(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public GameScreenshot create(GameScreenshot gameScreenshot) {
        return super.create(gameScreenshot);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public GameScreenshot find(Integer id) {
        return super.find(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public GameScreenshot update(GameScreenshot gameScreenshot) {
        return super.update(gameScreenshot);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<GameScreenshot> findAll() {
        return super.findAll();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(List<GameScreenshot> t) {
        super.delete(t);
    }
}
