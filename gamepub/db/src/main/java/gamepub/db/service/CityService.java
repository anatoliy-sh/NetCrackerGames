package gamepub.db.service;

import gamepub.db.dao.implementation.CityDaoImplementation;
import gamepub.db.entity.City;

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
public class CityService extends CityDaoImplementation {

    @PersistenceContext(unitName = "PERSISTENCE_WEB")
    protected EntityManager em;

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<City> getCitiesById(Integer id) {
        return super.getCitiesById(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public City getCityById(Integer id) {
        return super.getCityById(id);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected void closeEntityManager() {
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public City create(City city) {
        return super.create(city);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public City find(Integer id) {
        return super.find(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public City update(City city) {
        return super.update(city);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<City> findAll() {
        return super.findAll();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(List<City> t) {
        super.delete(t);
    }
}
