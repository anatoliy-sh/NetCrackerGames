package gamepub.db.dao.implementation;

import gamepub.db.dao.TournamentDao;
import gamepub.db.entity.Game;
import gamepub.db.entity.Tournament;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;

/**
 * Created by roman on 06.12.15.
 */
public class TournamentDaoImplementation extends BaseDaoImplementation<Tournament,Integer> implements TournamentDao {
    public TournamentDaoImplementation(){
        super(Tournament.class);
    }

    public Tournament getTournamentById(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Tournament> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Integer>get("id"), id));
        try {
            return (Tournament)getEntityManager().createQuery(cq).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public List<Tournament> getTournamentsByName(String name) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Tournament> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<String>get("name"), name));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<Tournament> getTournamentsByGameId(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Tournament> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Game>get("game").<Integer>get("id"), id));
        return getEntityManager().createQuery(cq).getResultList();
    }
}
