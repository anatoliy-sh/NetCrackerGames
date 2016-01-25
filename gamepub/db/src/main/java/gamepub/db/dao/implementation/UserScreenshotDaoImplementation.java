package gamepub.db.dao.implementation;

import gamepub.db.dao.UserScreenshotDao;
import gamepub.db.entity.User;
import gamepub.db.entity.UserScreenshot;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;

/**
 * Created by roman on 05.12.15.
 */
public class UserScreenshotDaoImplementation extends BaseDaoImplementation<UserScreenshot,Integer> implements UserScreenshotDao {
    public UserScreenshotDaoImplementation(){
        super(UserScreenshot.class);
    }

    public UserScreenshot getScreenshotById(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<UserScreenshot> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Integer>get("id"), id));
        try {
            return (UserScreenshot)getEntityManager().createQuery(cq).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public List<UserScreenshot> getScreenshotsByUserId(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<UserScreenshot> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<User>get("user").<Integer>get("id"), id));
        return getEntityManager().createQuery(cq).getResultList();
    }
}
