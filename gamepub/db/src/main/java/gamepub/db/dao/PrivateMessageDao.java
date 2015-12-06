package gamepub.db.dao;

import gamepub.db.entity.PrivateMessage;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by roman on 05.12.15.
 */
public interface PrivateMessageDao extends BaseDao<PrivateMessage, Integer> {
    public PrivateMessage getPrivateMessageById(Integer id);
    public PrivateMessage getPrivateMessageBySenderIdAndReceiverIdAndDate(Integer senderId, Integer receiverId, Date date);

    public List<PrivateMessage> getSendedPrivateMessagesBySenderId(Integer id);
    public List<PrivateMessage> getReceivedPrivateMessagesByReceiverId(Integer id);
    public List<PrivateMessage> getPrivateMessagesBySenderIdAndReceiverId(Integer senderId, Integer receiverId);
}
