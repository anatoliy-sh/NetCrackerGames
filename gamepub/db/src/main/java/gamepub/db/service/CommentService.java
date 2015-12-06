package gamepub.db.service;

import gamepub.db.dao.implementation.CommentDaoImplementation;
import gamepub.db.entity.Comment;

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
public class CommentService extends CommentDaoImplementation {

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
    public Comment getCommentById(Integer id) {
        return super.getCommentById(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Comment getCommentByNewsIdAndUserIdAndDate(Integer newsId, Integer userId, Date date) {
        return super.getCommentByNewsIdAndUserIdAndDate(newsId, userId, date);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Comment> getCommentsByNewsId(Integer id) {
        return super.getCommentsByNewsId(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Comment> getCommentsByUserId(Integer id) {
        return super.getCommentsByUserId(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Comment create(Comment comment) {
        return super.create(comment);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Comment find(Integer id) {
        return super.find(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Comment update(Comment comment) {
        return super.update(comment);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Comment> findAll() {
        return super.findAll();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(List<Comment> t) {
        super.delete(t);
    }
}
