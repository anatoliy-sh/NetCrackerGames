package gamepub.db.dao.implementation;

import gamepub.db.dao.GameStatusDao;
import gamepub.db.entity.GameStatus;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;

/**
 * Created by roman on 06.12.15.
 */
public class GameStatusDaoImplementation extends BaseDaoImplementation<GameStatus,Integer> implements GameStatusDao {
    public GameStatusDaoImplementation(){
        super(GameStatus.class);
    }

    public GameStatus getGameStatusById(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<GameStatus> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Integer>get("id"), id));
        try {
            return (GameStatus)getEntityManager().createQuery(cq).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
}
