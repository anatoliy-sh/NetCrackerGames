package gamepub.db.dao.implementation;

import gamepub.db.dao.GamePlatformDao;
import gamepub.db.entity.Game;
import gamepub.db.entity.GamePlatform;
import gamepub.db.entity.Platform;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;

/**
 * Created by roman on 06.12.15.
 */
public class GamePlatformDaoImplementation extends BaseDaoImplementation<GamePlatform,Integer> implements GamePlatformDao {
    public GamePlatformDaoImplementation(){
        super(GamePlatform.class);
    }

    public GamePlatform getGamePlatformById(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<GamePlatform> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Integer>get("id"), id));
        try {
            return (GamePlatform)getEntityManager().createQuery(cq).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public List<GamePlatform> getGamePlatformsByGameId(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<GamePlatform> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Game>get("game").<Integer>get("id"), id));
        return getEntityManager().createQuery(cq).getResultList();

    }

    public List<GamePlatform> getGamePlatformByPlatformId(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<GamePlatform> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Platform>get("platform").<Integer>get("id"), id));
        return getEntityManager().createQuery(cq).getResultList();
    }
}
