package gamepub.db.service;

import gamepub.db.dao.implementation.ScreenshotDaoImplementation;
import gamepub.db.entity.Screenshot;

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
public class ScreenshotService extends ScreenshotDaoImplementation {
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
    public Screenshot getScreenshotById(Integer id) {
        return super.getScreenshotById(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Screenshot> getScreenshotsByUserId(Integer id) {
        return super.getScreenshotsByUserId(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Screenshot create(Screenshot screenshot) {
        return super.create(screenshot);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Screenshot find(Integer id) {
        return super.find(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Screenshot update(Screenshot screenshot) {
        return super.update(screenshot);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Screenshot> findAll() {
        return super.findAll();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(List<Screenshot> t) {
        super.delete(t);
    }
}
