package gamepub.db.entity;

/**
 * Created by roman on 30.11.15.
 */
public class Friend {
    int id;
    User subscriber;
    User subscribeTo;

    public Friend() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(User subscriber) {
        this.subscriber = subscriber;
    }

    public User getSubscribeTo() {
        return subscribeTo;
    }

    public void setSubscribeTo(User subscribeTo) {
        this.subscribeTo = subscribeTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Friend)) return false;

        Friend friend = (Friend) o;

        return id == friend.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", subscriber=" + subscriber +
                ", subscribeTo=" + subscribeTo +
                '}';
    }
}
