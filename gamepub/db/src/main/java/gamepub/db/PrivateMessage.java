package gamepub.db;

import java.util.Date;

/**
 * Created by roman on 30.11.15.
 */
public class PrivateMessage {
    int id;
    Date date;
    String text;
    User sender;
    User receiver;

    public PrivateMessage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrivateMessage)) return false;

        PrivateMessage that = (PrivateMessage) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "PrivateMessage{" +
                "id=" + id +
                ", date=" + date +
                ", text='" + text + '\'' +
                ", sender=" + sender +
                ", receiver=" + receiver +
                '}';
    }
}
