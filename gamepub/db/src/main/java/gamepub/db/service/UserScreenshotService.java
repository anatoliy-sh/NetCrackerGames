package gamepub.db.service;

import gamepub.db.dao.implementation.UserScreenshotDaoImplementation;
import gamepub.db.entity.UserScreenshot;

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
public class UserScreenshotService extends UserScreenshotDaoImplementation {
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
    public UserScreenshot getScreenshotById(Integer id) {
        return super.getScreenshotById(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<UserScreenshot> getScreenshotsByUserId(Integer id) {
        return super.getScreenshotsByUserId(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public UserScreenshot create(UserScreenshot userScreenshot) {
        return super.create(userScreenshot);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public UserScreenshot find(Integer id) {
        return super.find(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public UserScreenshot update(UserScreenshot userScreenshot) {
        return super.update(userScreenshot);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<UserScreenshot> findAll() {
        return super.findAll();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(List<UserScreenshot> t) {
        super.delete(t);
    }
}
