package gamepub.db.service;

import gamepub.db.dao.implementation.TournamentDaoImplementation;
import gamepub.db.entity.Tournament;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by roman on 06.12.15.
 */
@Stateless
public class TournamentService extends TournamentDaoImplementation {
    @PersistenceContext(unitName = "PERSISTENCE_WEB")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected void closeEntityManager() {
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Tournament getTournamentById(Integer id) {
        return super.getTournamentById(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Tournament> getTournamentsByName(String name) {
        return super.getTournamentsByName(name);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Tournament> getTournamentsByGameId(Integer id) {
        return super.getTournamentsByGameId(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Tournament create(Tournament tournament) {
        return super.create(tournament);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Tournament find(Integer id) {
        return super.find(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Tournament update(Tournament tournament) {
        return super.update(tournament);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Tournament> findAll() {
        return super.findAll();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(List<Tournament> t) {
        super.delete(t);
    }
}
