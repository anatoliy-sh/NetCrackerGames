package gamepub.db.dao.implementation;

import gamepub.db.dao.UserDao;
import gamepub.db.entity.City;
import gamepub.db.entity.Country;
import gamepub.db.entity.User;
import gamepub.db.entity.UserRole;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by roman on 05.12.15.
 */
public class UserDaoImplementation extends BaseDaoImplementation<User,Integer> implements UserDao {
    public UserDaoImplementation(){
        super(User.class);
    }

    public User getUserById(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<User> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Integer>get("id"), id));
        try {
            return (User)getEntityManager().createQuery(cq).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public User getUserByLogin(String login) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<User> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<String>get("login"), login));
        try {
            return (User)getEntityManager().createQuery(cq).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public User getUserByLoginAndPassword(String login, String password) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<User> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<String>get("login"), login),cb.equal(root.<String>get("password"), password));
        try {
            return (User)getEntityManager().createQuery(cq).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public User getUserByEmail(String email) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<User> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<String>get("email"), email));
        try {
            return (User)getEntityManager().createQuery(cq).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public User getUserByVkInfo(String vkInfo) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<User> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<String>get("vkInfo"), vkInfo));
        try {
            return (User)getEntityManager().createQuery(cq).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public User getUserBySteamInfo(String steamInfo) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<User> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<String>get("steamInfo"), steamInfo));
        try {
            return (User)getEntityManager().createQuery(cq).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public User getUserByFbInfo(String fbInfo) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<User> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<String>get("fbInfo"), fbInfo));
        try {
            return (User)getEntityManager().createQuery(cq).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public List<User> getUsersByUserRoleId(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<User> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<UserRole>get("userRole").<Integer>get("id"), id));
        cq.orderBy(cb.asc(root.<String>get("login")));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<User> getUsersByCityId(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<User> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<City>get("city").<Integer>get("id"), id));
        cq.orderBy(cb.asc(root.<String>get("login")));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<User> getUsersByCountryId(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<User> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<City>get("city").<Country>get("country").<Integer>get("id"), id));
        cq.orderBy(cb.asc(root.<String>get("login")));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<User> getUsersByCustomParams(List<HashMap.Entry<String, Object>> parameterList) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<User> root = cq.from(instance);
        cq.select(root);
        List<Predicate> predicates = new ArrayList<Predicate>();
        if(parameterList!=null && parameterList.size()>0) {
            for (HashMap.Entry<String, Object> entry : parameterList) {
                if (entry.getKey().equals("name")) {
                    predicates.add(cb.like(root.<String>get("login"), "%" + entry.getValue() + "%"));
                } else if (entry.getKey().equals("country")) {
                    predicates.add(cb.equal(root.<City>get("city").<Country>get("country"), entry.getValue()));
                } else predicates.add(cb.equal(root.<City>get("city"), entry.getValue()));
            }
            Predicate[] p = new Predicate[predicates.size()];
            int i = 0;
            for(Predicate predicate:predicates){
                p[i] = predicate;
                i++;
            }
            cq.where(p);
        }
        cq.orderBy(cb.asc(root.<String>get("login")));
        return getEntityManager().createQuery(cq).getResultList();
    }
}
