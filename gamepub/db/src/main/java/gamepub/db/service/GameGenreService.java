package gamepub.db.service;

import gamepub.db.dao.implementation.GameGenreDaoImplementation;
import gamepub.db.entity.GameGenre;

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
public class GameGenreService extends GameGenreDaoImplementation {
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
    public GameGenre getGameGenreById(Integer id) {
        return super.getGameGenreById(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<GameGenre> getGameGenresByGameId(Integer id) {
        return super.getGameGenresByGameId(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<GameGenre> getGameGenresByGenreId(Integer id) {
        return super.getGameGenresByGenreId(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public GameGenre create(GameGenre gameGenre) {
        return super.create(gameGenre);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public GameGenre find(Integer id) {
        return super.find(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public GameGenre update(GameGenre gameGenre) {
        return super.update(gameGenre);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<GameGenre> findAll() {
        return super.findAll();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(List<GameGenre> t) {
        super.delete(t);
    }
}
