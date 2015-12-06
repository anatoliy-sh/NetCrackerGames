package gamepub.db.entity;

import javax.persistence.*;

/**
 * Created by roman on 30.11.15.
 */
@Entity
@Table(name = "USER_GAME")
public class UserGame {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "FAVORITE", nullable = false)
    boolean favorite;
    @Column(name = "WANTED", nullable = false)
    boolean wanted;
    @Column(name = "CAN_EXCHANGE", nullable = false)
    boolean canExchange;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", nullable = false)
    User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GAME_ID", nullable = false)
    Game game;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GAME_STATUS_ID", nullable = false)
    GameStatus gameStatus;

    public UserGame() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isWanted() {
        return wanted;
    }

    public void setWanted(boolean wanted) {
        this.wanted = wanted;
    }

    public boolean isCanExchange() {
        return canExchange;
    }

    public void setCanExchange(boolean canExchange) {
        this.canExchange = canExchange;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserGame)) return false;

        UserGame userGame = (UserGame) o;

        return id == userGame.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "UserGame{" +
                "id=" + id +
                ", favorite=" + favorite +
                ", wanted=" + wanted +
                ", canExchange=" + canExchange +
                ", user=" + user +
                ", game=" + game +
                ", gameStatus=" + gameStatus +
                '}';
    }
}
