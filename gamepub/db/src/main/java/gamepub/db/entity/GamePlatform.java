package gamepub.db.entity;

import javax.persistence.*;

/**
 * Created by roman on 30.11.15.
 */
@Entity
@Table(name = "GAME_PLATFORM")
public class GamePlatform {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GAME_ID", nullable = false)
    Game game;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PLATFORM_ID", nullable = false)
    Platform platform;

    public GamePlatform() {
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

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GamePlatform)) return false;

        GamePlatform that = (GamePlatform) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "GamePlatform{" +
                "id=" + id +
                ", game=" + game +
                ", platform=" + platform +
                '}';
    }
}
