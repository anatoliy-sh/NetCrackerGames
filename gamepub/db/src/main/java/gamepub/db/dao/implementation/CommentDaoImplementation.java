package gamepub.db.dao.implementation;

import gamepub.db.dao.CommentDao;
import gamepub.db.entity.Comment;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by roman on 06.12.15.
 */
public class CommentDaoImplementation extends BaseDaoImplementation<Comment,Integer> implements CommentDao {
    public CommentDaoImplementation(){
        super(Comment.class);
    }

    public Comment getCommentById(Integer id) {
        String jpa = "SELECT c FROM Comment c WHERE c.id = :id";
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

    public Comment getCommentByNewsIdAndUserIdAndDate(Integer newsId, Integer userId, Date date) {
        String jpa = "SELECT c FROM Comment c WHERE c.news.id = :newsId AND" +
                "c.user.id= :userId AND c.date= :date";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("userId",userId);
        parameters.put("newsId",newsId);
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

    public List<Comment> getCommentsByNewsId(Integer id) {
        String jpa = "SELECT c FROM Comment c WHERE c.news.id = :id ORDER BY c.date";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        return this.ExecuteQuery(jpa, parameters);
    }

    public List<Comment> getCommentsByUserId(Integer id) {
        String jpa = "SELECT c FROM Comment c WHERE c.user.id = :id ORDER BY c.date";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        return this.ExecuteQuery(jpa, parameters);
    }
}
