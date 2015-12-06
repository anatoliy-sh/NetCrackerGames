package gamepub.db.dao;

import gamepub.db.entity.Comment;

import java.util.Date;
import java.util.List;

/**
 * Created by roman on 06.12.15.
 */
public interface CommentDao extends BaseDao<Comment,Integer> {
    public Comment getCommentById(Integer id);
    public Comment getCommentByNewsIdAndUserIdAndDate(Integer newsId, Integer userId, Date date);

    public List<Comment> getCommentsByNewsId(Integer id);
    public List<Comment> getCommentsByUserId(Integer id);
}
