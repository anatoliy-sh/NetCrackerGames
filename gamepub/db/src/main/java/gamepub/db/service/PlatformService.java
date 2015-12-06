package gamepub.db.service;

import gamepub.db.dao.implementation.PlatformDaoImplementation;
import gamepub.db.entity.Platform;

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
public class PlatformService extends PlatformDaoImplementation {
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
    public Platform getPlatformById(Integer id) {
        return super.getPlatformById(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Platform create(Platform platform) {
        return super.create(platform);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Platform find(Integer id) {
        return super.find(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Platform update(Platform platform) {
        return super.update(platform);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Platform> findAll() {
        return super.findAll();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(List<Platform> t) {
        super.delete(t);
    }
}
