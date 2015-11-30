package gamepub.db;

/**
 * Created by roman on 30.11.15.
 */
public class GameGenre {
    int id;
    Game game;
    Genre genre;

    public GameGenre() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameGenre)) return false;

        GameGenre gameGenre = (GameGenre) o;

        return id == gameGenre.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "GameGenre{" +
                "id=" + id +
                ", game=" + game +
                ", genre=" + genre +
                '}';
    }
}
