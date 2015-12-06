package gamepub.db.service;

import gamepub.db.dao.implementation.FriendDaoImplementation;
import gamepub.db.entity.Friend;

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
public class FriendService extends FriendDaoImplementation {
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
    public Friend getFriendById(Integer id) {
        return super.getFriendById(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Friend> getSubscribersByUserId(Integer id) {
        return super.getSubscribersByUserId(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Friend> getSubscribedToByUserId(Integer id) {
        return super.getSubscribedToByUserId(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Friend create(Friend friend) {
        return super.create(friend);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Friend find(Integer id) {
        return super.find(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Friend update(Friend friend) {
        return super.update(friend);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Friend> findAll() {
        return super.findAll();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(List<Friend> t) {
        super.delete(t);
    }
}
