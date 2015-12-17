package gamepub.db.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by roman on 30.11.15.
 */
@Entity
@Table(name = "COMMENT")
public class Comment {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "TEXT", columnDefinition = "TEXT", nullable = false)
    String text;
    @Column(name = "DATE", nullable = false)
    Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", nullable = false)
    User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "NEWS_ID", nullable = false)
    News news;

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment = (Comment) o;

        return id == comment.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", user=" + user +
                ", news=" + news +
                ", date=" + date +
                '}';
    }
}
