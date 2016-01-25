package gamepub.db.dao.implementation;

import gamepub.db.dao.NewsDao;
import gamepub.db.entity.Game;
import gamepub.db.entity.News;
import org.omg.CORBA.DATA_CONVERSION;

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
public class NewsDaoImplementation extends BaseDaoImplementation<News,Integer> implements NewsDao {
    public NewsDaoImplementation(){
        super(News.class);
    }

    public News getNewsById(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<News> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Integer>get("id"), id));
        try {
            return (News)getEntityManager().createQuery(cq).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public List<News> getNewsByName(String name) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<News> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.like(root.<String>get("name"), "%" + name + "%"));
        cq.orderBy(cb.desc(root.<Date>get("date")));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<News> getNewsByGameId(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<News> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Game>get("game").<Integer>get("id"), id));
        cq.orderBy(cb.desc(root.<Date>get("date")));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<News> getNewsOrderByDate() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<News> root = cq.from(instance);
        cq.select(root);
        cq.orderBy(cb.desc(root.<Date>get("date")));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<News> getNewsByDate(Date date) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<News> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Date>get("date"), date));
        cq.orderBy(cb.desc(root.<Date>get("date")));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<News> getNewsByCustomParams(List<HashMap.Entry<String, Object>> parameterList){
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<News> root = cq.from(instance);
        cq.select(root);
        if(parameterList!=null && parameterList.size()>0) {
            for (HashMap.Entry<String, Object> entry : parameterList) {
                if (entry.getKey().equals("name")) {
                    cq.where(cb.like(root.<String>get("name"), "%" + entry.getValue() + "%"));
                } else if (entry.getKey().equals("game")) {
                    cq.where(cb.equal(root.<Game>get("game"), entry.getValue()));
                } else cq.where(cb.lessThanOrEqualTo(root.<Date>get("date"), (Date) entry.getValue()));
            }
        }
        cq.orderBy(cb.desc(root.<Date>get("date")));
        return getEntityManager().createQuery(cq).getResultList();
    }
}
