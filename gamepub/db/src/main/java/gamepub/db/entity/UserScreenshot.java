package gamepub.db.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by roman on 30.11.15.
 */
@Entity
@Table(name = "USER_SCREENSHOT")
public class UserScreenshot {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "LINK", nullable = false)
    String link;
    @Column(name = "DATE", nullable = false)
    Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", nullable = false)
    User user;

    public UserScreenshot() {
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
        if (!(o instanceof UserScreenshot)) return false;

        UserScreenshot that = (UserScreenshot) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "UserScreenshot{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", date=" + date +
                ", user=" + user +
                '}';
    }
}
