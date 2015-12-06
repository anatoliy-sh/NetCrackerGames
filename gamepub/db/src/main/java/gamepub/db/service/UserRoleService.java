package gamepub.db.service;

import gamepub.db.dao.implementation.UserRoleDaoImplementation;
import gamepub.db.entity.UserRole;

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
public class UserRoleService extends UserRoleDaoImplementation {
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
    public UserRole getUserRoleById(Integer id) {
        return super.getUserRoleById(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public UserRole create(UserRole userRole) {
        return super.create(userRole);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public UserRole find(Integer id) {
        return super.find(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public UserRole update(UserRole userRole) {
        return super.update(userRole);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<UserRole> findAll() {
        return super.findAll();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(List<UserRole> t) {
        super.delete(t);
    }
}
