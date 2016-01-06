package gamepub.beans;

import gamepub.db.entity.Game;
import gamepub.db.entity.UserGame;
import gamepub.db.service.GameService;
import gamepub.db.service.UserGameService;

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


    public List<Game> getMyGames() {

        return gameService.findAll();
    }

    public String goToConcreteGame() {
        return "game?faces-redirect=true";
    }
}

