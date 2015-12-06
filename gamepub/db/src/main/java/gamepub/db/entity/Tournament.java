package gamepub.db.entity;

import javax.persistence.*;

/**
 * Created by roman on 30.11.15.
 */
@Entity
@Table(name = "TOURNAMENT")
public class Tournament {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "NAME", nullable = false)
    String name;
    @Column(name = "LINK", nullable = false)
    String link;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GAME_ID", nullable = false)
    Game game;

    public Tournament() {
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tournament)) return false;

        Tournament that = (Tournament) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", game=" + game +
                '}';
    }
}
