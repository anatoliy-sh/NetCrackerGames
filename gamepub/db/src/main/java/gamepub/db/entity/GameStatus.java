package gamepub.db.entity;

import java.util.List;

/**
 * Created by roman on 30.11.15.
 */
public class GameStatus {
    int id;
    String name;

    List<UserGame> userGames;

    public GameStatus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserGame> getUserGames() {
        return userGames;
    }

    public void setUserGames(List<UserGame> userGames) {
        this.userGames = userGames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameStatus)) return false;

        GameStatus that = (GameStatus) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "GameStatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
