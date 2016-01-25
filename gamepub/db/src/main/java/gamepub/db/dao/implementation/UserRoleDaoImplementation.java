package gamepub.db.dao.implementation;

import gamepub.db.dao.UserRoleDao;
import gamepub.db.entity.UserRole;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;

/**
 * Created by roman on 05.12.15.
 */
public class UserRoleDaoImplementation extends BaseDaoImplementation<UserRole,Integer> implements UserRoleDao {
    public UserRoleDaoImplementation(){
        super(UserRole.class);
    }

    public UserRole getUserRoleById(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<UserRole> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Integer>get("id"), id));
        try {
            return (UserRole)getEntityManager().createQuery(cq).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
}
