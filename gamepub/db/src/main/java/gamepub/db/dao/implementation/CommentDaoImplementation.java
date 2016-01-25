package gamepub.db.dao.implementation;

import gamepub.db.dao.CommentDao;
import gamepub.db.entity.Comment;
import gamepub.db.entity.News;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by roman on 06.12.15.
 */
public class CommentDaoImplementation extends BaseDaoImplementation<Comment,Integer> implements CommentDao {
    public CommentDaoImplementation(){
        super(Comment.class);
    }

    public Comment getCommentById(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Comment> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Integer>get("id"), id));
        try {
            return (Comment)getEntityManager().createQuery(cq).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public Comment getCommentByNewsIdAndUserIdAndDate(Integer newsId, Integer userId, Date date) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Comment> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<News>get("news").<Integer>get("id"), newsId),
                cb.equal(root.<News>get("user").<Integer>get("id"), userId),
                cb.equal(root.<Date>get("date"), date));
        try {
            return (Comment)getEntityManager().createQuery(cq).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public List<Comment> getCommentsByNewsId(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Comment> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<News>get("news").<Integer>get("id"), id));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<Comment> getCommentsByUserId(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Comment> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<News>get("user").<Integer>get("id"), id));
        return getEntityManager().createQuery(cq).getResultList();
    }
}
