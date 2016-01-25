package gamepub.db.dao.implementation;

import gamepub.db.dao.FriendDao;
import gamepub.db.entity.Friend;
import gamepub.db.entity.User;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;

/**
 * Created by roman on 05.12.15.
 */
public class FriendDaoImplementation extends BaseDaoImplementation<Friend, Integer> implements FriendDao {
    public FriendDaoImplementation(){
        super(Friend.class);
    }

    public Friend getFriendById(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Friend> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Integer>get("id"), id));
        try {
            return (Friend)getEntityManager().createQuery(cq).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public List<Friend> getSubscribersByUserId(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Friend> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<User>get("subscribedTo").<Integer>get("id"), id));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<Friend> getSubscribedToByUserId(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Friend> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<User>get("subscriber").<Integer>get("id"), id));
        return getEntityManager().createQuery(cq).getResultList();
    }
}
