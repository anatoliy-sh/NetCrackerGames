package gamepub.db.dao.implementation;

import gamepub.db.dao.GameGenreDao;
import gamepub.db.entity.Game;
import gamepub.db.entity.GameGenre;
import gamepub.db.entity.Genre;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;

/**
 * Created by roman on 06.12.15.
 */
public class GameGenreDaoImplementation extends BaseDaoImplementation<GameGenre,Integer> implements GameGenreDao {
    public GameGenreDaoImplementation(){
        super(GameGenre.class);
    }

    public GameGenre getGameGenreById(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<GameGenre> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Integer>get("id"), id));
        try {
            return (GameGenre)getEntityManager().createQuery(cq).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public List<GameGenre> getGameGenresByGameId(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<GameGenre> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Game>get("game").<Integer>get("id"), id));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<GameGenre> getGameGenresByGenreId(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<GameGenre> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Genre>get("genre").<Integer>get("id"), id));
        return getEntityManager().createQuery(cq).getResultList();
    }
}
