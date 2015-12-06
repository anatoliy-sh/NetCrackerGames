package gamepub.dto;

import gamepub.db.entity.Comment;

import java.util.Date;
import java.util.List;

/**
 * Created by roman on 03.12.15.
 */
public class NewsDto {
    String title;
    String text;
    Date date;
    List<Comment> comments;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
