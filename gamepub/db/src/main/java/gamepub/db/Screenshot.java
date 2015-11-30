package gamepub.db;

import java.util.Date;

/**
 * Created by roman on 30.11.15.
 */
public class Screenshot {
    int id;
    String link;
    Date date;

    User user;

    public Screenshot() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Screenshot)) return false;

        Screenshot that = (Screenshot) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Screenshot{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", date=" + date +
                ", user=" + user +
                '}';
    }
}
