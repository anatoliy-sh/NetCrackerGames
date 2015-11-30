package gamepub.db;

import javax.management.ListenerNotFoundException;
import java.util.List;

/**
 * Created by roman on 30.11.15.
 */
public class Game {
    int id;
    String name;
    String linkToSteam;
    String linkToSonyPlaystationStore;

    List<GamePlatform> gamePlatforms;
    List<GameGenre> gameGenres;
    List<Tournament> tournaments;
    List<Mark> marks;
    List<UserGame> userGames;
    List<News> newses;


    public Game() {
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

    public String getLinkToSteam() {
        return linkToSteam;
    }

    public void setLinkToSteam(String linkToSteam) {
        this.linkToSteam = linkToSteam;
    }

    public String getLinkToSonyPlaystationStore() {
        return linkToSonyPlaystationStore;
    }

    public void setLinkToSonyPlaystationStore(String linkToSonyPlaystationStore) {
        this.linkToSonyPlaystationStore = linkToSonyPlaystationStore;
    }

    public List<GamePlatform> getGamePlatforms() {
        return gamePlatforms;
    }

    public void setGamePlatforms(List<GamePlatform> gamePlatforms) {
        this.gamePlatforms = gamePlatforms;
    }

    public List<GameGenre> getGameGenres() {
        return gameGenres;
    }

    public void setGameGenres(List<GameGenre> gameGenres) {
        this.gameGenres = gameGenres;
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public List<UserGame> getUserGames() {
        return userGames;
    }

    public void setUserGames(List<UserGame> userGames) {
        this.userGames = userGames;
    }

    public List<News> getNewses() {
        return newses;
    }

    public void setNewses(List<News> newses) {
        this.newses = newses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;

        Game game = (Game) o;

        return id == game.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", linkToSteam='" + linkToSteam + '\'' +
                ", linkToSonyPlaystationStore='" + linkToSonyPlaystationStore + '\'' +
                '}';
    }
}
