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
        GameScreenshot result;
        try {
            result = (GameScreenshot)getEntityManager().createQuery(cq).getSingleResult();
        }catch (NoResultException e){
            result = null;
        }finally {
            closeEntityManager();
        }
        return result;

    }

    public List<GameScreenshot> getScreenshotsByGameId(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<GameScreenshot> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Game>get("game").<Integer>get("id"), id));
        List result = getEntityManager().createQuery(cq).getResultList();
        closeEntityManager();
        return result;
    }
}
