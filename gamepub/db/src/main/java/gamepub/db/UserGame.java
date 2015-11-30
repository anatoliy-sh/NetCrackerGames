package gamepub.db;

/**
 * Created by roman on 30.11.15.
 */
public class UserGame {
    int id;
    boolean favorite;
    boolean wanted;
    boolean canExchange;
    User user;
    Game game;
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
