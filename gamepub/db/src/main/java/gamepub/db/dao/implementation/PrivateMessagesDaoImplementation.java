package gamepub.db.dao.implementation;

import gamepub.db.dao.PrivateMessageDao;
import gamepub.db.entity.PrivateMessage;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by roman on 05.12.15.
 */
public class PrivateMessagesDaoImplementation extends BaseDaoImplementation<PrivateMessage,Integer> implements PrivateMessageDao {
    public PrivateMessagesDaoImplementation(){
        super(PrivateMessage.class);
    }

    public PrivateMessage getPrivateMessageById(Integer id) {
        String jpa = "SELECT p FROM PrivateMessage u WHERE p.id = :id";
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

    public PrivateMessage getPrivateMessageBySenderIdAndReceiverIdAndDate(Integer senderId, Integer receiverId, Date date) {
        String jpa = "SELECT p FROM PrivateMessage u WHERE p.sender.id = :senderId AND " +
                "p.receiver.id = :receiverId AND" +
                "p.date = :date";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("senderId",senderId);
        parameters.put("receiverId",receiverId);
        parameters.put("date",date);
        try
        {
            return this.ExecuteQuery(jpa, parameters).get(0);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public List<PrivateMessage> getSendedPrivateMessagesBySenderId(Integer id) {
        String jpa = "SELECT p FROM PrivateMessage p WHERE p.sender.id = :id ORDER BY p.date DESC";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        return this.ExecuteQuery(jpa, parameters);
    }

    public List<PrivateMessage> getReceivedPrivateMessagesByReceiverId(Integer id) {
        String jpa = "SELECT p FROM PrivateMessage p WHERE p.receiver.id = :id ORDER BY p.date DESC";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        return this.ExecuteQuery(jpa, parameters);
    }

    public List<PrivateMessage> getPrivateMessagesBySenderIdAndReceiverId(Integer senderId, Integer receiverId) {
        String jpa = "SELECT p FROM PrivateMessage p WHERE p.sender.id = :senderId AND p.receiver.id = :receiverID  ORDER BY p.date DESC";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("senderId",senderId);
        parameters.put("receiverID",receiverId);
        return this.ExecuteQuery(jpa, parameters);
    }
}
