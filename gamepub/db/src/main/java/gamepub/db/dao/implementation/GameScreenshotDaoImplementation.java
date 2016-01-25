package gamepub.db.dao.implementation;

import gamepub.db.dao.GameScreenshotDao;
import gamepub.db.entity.Game;
import gamepub.db.entity.GameScreenshot;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;

/**
 * Created by roman on 17.12.15.
 */
public class GameScreenshotDaoImplementation extends BaseDaoImplementation<GameScreenshot,Integer> implements GameScreenshotDao {
    public GameScreenshotDaoImplementation(){
        super(GameScreenshot.class);
    }

    public GameScreenshot getScreenshotById(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<GameScreenshot> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Integer>get("id"), id));
        try {
            return (GameScreenshot)getEntityManager().createQuery(cq).getSingleResult();
        }catch (NoResultException e){
            return null;
        }

    }

    public List<GameScreenshot> getScreenshotsByGameId(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<GameScreenshot> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Game>get("game").<Integer>get("id"), id));
        return getEntityManager().createQuery(cq).getResultList();
    }
}
