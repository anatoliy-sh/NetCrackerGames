package gamepub.db.dao.implementation;

import gamepub.db.dao.UserDao;
import gamepub.db.entity.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by roman on 05.12.15.
 */
public class UserDaoImplementation extends BaseDaoImplementation<User,Integer> implements UserDao {
    public UserDaoImplementation(){
        super(User.class);
    }

    public User getUserById(Integer id) {
        String jpa = "SELECT u FROM User u WHERE u.id = :id";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        try
        {
            return this.ExecuteQuery(jpa, parameters).get(0);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public User getUserByLogin(String login) {
        String jpa = "SELECT u FROM User u WHERE u.login = :login";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("login",login);
        try
        {
            return this.ExecuteQuery(jpa, parameters).get(0);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public User getUserByLoginAndPassword(String login, String password) {
        String jpa = "SELECT u FROM User u WHERE u.login = :login AND u.password = :password";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("login",login);
        parameters.put("password", password);
        try
        {
            return this.ExecuteQuery(jpa, parameters).get(0);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public User getUserByEmail(String email) {
        String jpa = "SELECT u FROM User u WHERE u.email = :email";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("email",email);
        try
        {
            return this.ExecuteQuery(jpa, parameters).get(0);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public User getUserByVkInfo(String vkInfo) {
        String jpa = "SELECT u FROM User u WHERE u.vkInfo = :vkInfo";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("vkInfo",vkInfo);
        try
        {
            return this.ExecuteQuery(jpa, parameters).get(0);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public User getUserBySteamInfo(String steamInfo) {
        String jpa = "SELECT u FROM User u WHERE u.steamInfo = :steamInfo";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("steamInfo",steamInfo);
        try
        {
            return this.ExecuteQuery(jpa, parameters).get(0);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public User getUserByFbInfo(String fbInfo) {
        String jpa = "SELECT u FROM User u WHERE u.fbInfo = :fbInfo";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("fbInfo",fbInfo);
        try
        {
            return this.ExecuteQuery(jpa, parameters).get(0);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public List<User> getUsersByUserRoleId(Integer id) {
        String jpa = "SELECT u FROM User u WHERE u.userRole.id = :id ORDER BY u.name";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        return this.ExecuteQuery(jpa, parameters);
    }

    public List<User> getUsersByCityId(Integer id) {
        String jpa = "SELECT u FROM User u WHERE u.city.id = :id ORDER BY u.name";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        return this.ExecuteQuery(jpa, parameters);
    }

    public List<User> getUsersByCountryId(Integer id) {
        String jpa = "SELECT u FROM User u WHERE u.city.country.id = :id ORDER BY u.name";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        return this.ExecuteQuery(jpa, parameters);
    }
}
