package gamepub.db.service;

import gamepub.db.dao.implementation.PrivateMessagesDaoImplementation;
import gamepub.db.entity.PrivateMessage;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

/**
 * Created by roman on 06.12.15.
 */
@Stateless
public class PrivateMessageService extends PrivateMessagesDaoImplementation {
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
    public PrivateMessage getPrivateMessageById(Integer id) {
        return super.getPrivateMessageById(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public PrivateMessage getPrivateMessageBySenderIdAndReceiverIdAndDate(Integer senderId, Integer receiverId, Date date) {
        return super.getPrivateMessageBySenderIdAndReceiverIdAndDate(senderId, receiverId, date);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<PrivateMessage> getSendedPrivateMessagesBySenderId(Integer id) {
        return super.getSendedPrivateMessagesBySenderId(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<PrivateMessage> getReceivedPrivateMessagesByReceiverId(Integer id) {
        return super.getReceivedPrivateMessagesByReceiverId(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<PrivateMessage> getPrivateMessagesBySenderIdAndReceiverId(Integer senderId, Integer receiverId) {
        return super.getPrivateMessagesBySenderIdAndReceiverId(senderId, receiverId);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public PrivateMessage create(PrivateMessage privateMessage) {
        return super.create(privateMessage);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public PrivateMessage find(Integer id) {
        return super.find(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public PrivateMessage update(PrivateMessage privateMessage) {
        return super.update(privateMessage);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<PrivateMessage> findAll() {
        return super.findAll();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(List<PrivateMessage> t) {
        super.delete(t);
    }
}
