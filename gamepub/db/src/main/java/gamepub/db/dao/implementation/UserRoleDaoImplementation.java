package gamepub.db.dao.implementation;

import gamepub.db.dao.UserRoleDao;
import gamepub.db.entity.UserRole;

import java.util.HashMap;
import java.util.List;

/**
 * Created by roman on 05.12.15.
 */
public class UserRoleDaoImplementation extends BaseDaoImplementation<UserRole,Integer> implements UserRoleDao {
    public UserRoleDaoImplementation(){
        super(UserRole.class);
    }

    public UserRole getUserRoleById(Integer id) {
        String jpa = "SELECT u FROM UserRole u WHERE u.id = :id";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        try
        {
            return this.ExecuteQuery(jpa, parameters).get(0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
