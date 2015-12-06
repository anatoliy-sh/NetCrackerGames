package gamepub.db.service;

import gamepub.db.dao.implementation.GenreDaoImplementation;
import gamepub.db.entity.Genre;

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
public class GenreService extends GenreDaoImplementation {
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
    public Genre getGenreById(Integer id) {
        return super.getGenreById(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Genre create(Genre genre) {
        return super.create(genre);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Genre find(Integer id) {
        return super.find(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Genre update(Genre genre) {
        return super.update(genre);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Genre> findAll() {
        return super.findAll();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(List<Genre> t) {
        super.delete(t);
    }
}
