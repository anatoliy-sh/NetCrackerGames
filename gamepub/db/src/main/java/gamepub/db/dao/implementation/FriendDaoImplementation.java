package gamepub.db.dao.implementation;

import gamepub.db.dao.FriendDao;
import gamepub.db.entity.Friend;

import java.util.HashMap;
import java.util.List;

/**
 * Created by roman on 05.12.15.
 */
public class FriendDaoImplementation extends BaseDaoImplementation<Friend, Integer> implements FriendDao {
    public FriendDaoImplementation(){
        super(Friend.class);
    }

    public Friend getFriendById(Integer id) {
        String jpa = "SELECT f FROM Friend f WHERE f.id = :id";
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

    public List<Friend> getSubscribersByUserId(Integer id) {
        String jpa = "SELECT f FROM Friend f WHERE f.subscribedTo.id = :id";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        return this.ExecuteQuery(jpa, parameters);
    }

    public List<Friend> getSubscribedToByUserId(Integer id) {
        String jpa = "SELECT f FROM Friend f WHERE f.subscriber.id = :id";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        return this.ExecuteQuery(jpa, parameters);
    }
}
