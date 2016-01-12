package gamepub.beans;

import gamepub.db.entity.Game;
import gamepub.db.entity.Genre;
import gamepub.db.entity.Platform;
import gamepub.db.entity.UserGame;
import gamepub.db.service.GameService;
import gamepub.db.service.GenreService;
import gamepub.db.service.PlatformService;
import gamepub.db.service.UserGameService;
import org.hibernate.Hibernate;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Анатолий on 06.01.2016.
 */
@ManagedBean
@SessionScoped
public class SerachBean {
    List<Game> myGames;

    @EJB
    GameService gameService;

    @EJB
    GenreService genreService;
    @EJB
    PlatformService platformService;

    public List<Game> getMyGames() {
        myGames = gameService.findAll();
        return myGames;
    }

    public List<Genre> getGenre(){
        return genreService.findAll();
    }

    public List<Platform> getPlatforms(){
        return platformService.findAll();
    }

    public void serch(){

    }

    public String goToConcreteGame() {
        return "game?faces-redirect=true";
    }
}

