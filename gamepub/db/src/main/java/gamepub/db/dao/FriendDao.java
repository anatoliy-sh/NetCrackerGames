package gamepub.db.dao;

import gamepub.db.entity.Friend;

import java.util.List;

/**
 * Created by roman on 05.12.15.
 */
public interface FriendDao extends BaseDao<Friend,Integer> {
    public Friend getFriendById(Integer id);
    public List<Friend> getSubscribersByUserId(Integer id);
    public List<Friend> getSubscribedToByUserId(Integer id);
}
