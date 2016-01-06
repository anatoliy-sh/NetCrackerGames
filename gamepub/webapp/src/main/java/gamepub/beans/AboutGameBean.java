/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamepub.beans;

import gamepub.dto.GameDto;
import gamepub.db.entity.Comment;
import gamepub.db.entity.Game;
import gamepub.db.entity.GameGenre;
import gamepub.db.entity.User;
import gamepub.db.entity.UserGame;
import gamepub.db.service.CommentService;
import gamepub.db.service.GameGenreService;
import gamepub.db.service.GameService;
import gamepub.db.service.GameStatusService;
import gamepub.db.service.UserGameService;
import gamepub.db.service.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Иван
 */
@ManagedBean
@SessionScoped
@RequestScoped
public class AboutGameBean {

    @ManagedProperty(value = "#{param.id}")
    private int id;

    @EJB
    GameService gameService;
    @EJB
    UserGameService userGameService;
    @EJB
    UserService userService;
    @EJB
    GameStatusService gameStatusService;

    Game game;

    public Game getGame() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("id", id);
        Game g = gameService.getGameById(id);
        return g;
    }

    public void addToFavourite() {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean exist = true;
        UserGame userGame = userGameService.getUserGameByUserIdAndGameId(1, Integer.valueOf(context.getExternalContext().getSessionMap().get("id").toString()));
        if (userGame == null) {
            exist = false;
            userGame = new UserGame();
        }
        userGame.setGame(gameService.getGameById(Integer.valueOf(context.getExternalContext().getSessionMap().get("id").toString())));
        context.getExternalContext().getSessionMap().remove("id");
        userGame.setUser(userService.getUserById(1));
        userGame.setCanExchange(false);
        userGame.setWanted(false);
        userGame.setFavorite(true);
        userGame.setGameStatus(gameStatusService.getGameStatusById(1));
        if (!exist) 
            userGameService.create(userGame);
        else
            userGameService.update(userGame);
    }

    public void addToWanted() {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean exist = true;
        UserGame userGame = userGameService.getUserGameByUserIdAndGameId(1, Integer.valueOf(context.getExternalContext().getSessionMap().get("id").toString()));
        if (userGame == null) {
            exist = false;
            userGame = new UserGame();
        }
        userGame.setGame(gameService.getGameById(Integer.valueOf(context.getExternalContext().getSessionMap().get("id").toString())));
        context.getExternalContext().getSessionMap().remove("id");
        userGame.setUser(userService.getUserById(1));
        userGame.setCanExchange(false);
        userGame.setWanted(true);
        userGame.setFavorite(false);
        userGame.setGameStatus(gameStatusService.getGameStatusById(1));
        if (!exist) 
            userGameService.create(userGame);
        else
            userGameService.update(userGame);
    }

    public void addToExchange() {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean exist = true;
        UserGame userGame = userGameService.getUserGameByUserIdAndGameId(1, Integer.valueOf(context.getExternalContext().getSessionMap().get("id").toString()));
        if (userGame == null) {
            exist = false;
            userGame = new UserGame();
        }
        userGame.setGame(gameService.getGameById(Integer.valueOf(context.getExternalContext().getSessionMap().get("id").toString())));
        context.getExternalContext().getSessionMap().remove("id");
        userGame.setUser(userService.getUserById(1));
        userGame.setCanExchange(true);
        userGame.setWanted(false);
        userGame.setFavorite(false);
        userGame.setGameStatus(gameStatusService.getGameStatusById(1));
        if (!exist) 
            userGameService.create(userGame);
        else
            userGameService.update(userGame);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
