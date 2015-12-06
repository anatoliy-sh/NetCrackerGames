package gamepub.db.dao;

import gamepub.db.entity.UserRole;

/**
 * Created by roman on 05.12.15.
 */
public interface UserRoleDao extends BaseDao<UserRole,Integer> {
    public UserRole getUserRoleById(Integer id);
}
