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
import gamepub.db.entity.Mark;
import gamepub.db.entity.User;
import gamepub.db.entity.UserGame;
import gamepub.db.service.CommentService;
import gamepub.db.service.GameGenreService;
import gamepub.db.service.GameService;
import gamepub.db.service.GameStatusService;
import gamepub.db.service.MarkService;
import gamepub.db.service.UserGameService;
import gamepub.db.service.UserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.rating.Rating;

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
    @EJB
    MarkService markService;

    Game game;
    List<Mark> marksAndReviews;

    public Game getGame() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("id", id);
        Game g = gameService.getGameById(id);
        UserGame userGame = getUserGame();
        if (userGame != null) {
            disableButtons(userGame);
        }
        return g;
    }

    public List<Mark> getMarksAndReviews() {
        FacesContext context = FacesContext.getCurrentInstance();
        //if(context.getExternalContext().getSessionMap().containsKey("id")) {
        id = Integer.parseInt(context.getExternalContext().getSessionMap().get("id").toString());
        //}
        marksAndReviews = markService.getMarksByGameId(id);
        return marksAndReviews;
    }

    public void addMarkAndReview() {
        FacesContext context = FacesContext.getCurrentInstance();

        UIViewRoot uiViewRoot = context.getViewRoot();
        InputTextarea inputText = null;
        Rating rating = null;
        inputText = (InputTextarea) uiViewRoot.findComponent("markAdderForm:markAdderNewComment");
        rating = (Rating) uiViewRoot.findComponent("markAdderForm:markAdderNewMark");
        int mrk = Integer.valueOf((String) rating.getValue());
        String review = (String) inputText.getValue();
        if (review == null || review.isEmpty() || review.length() >= 501) {
            return;
        }
        if (mrk == 0) {
            return;
        }
        Mark m = markService.getMarkByUserAndGameId(SessionBean.getUserId(), Integer.valueOf(context.getExternalContext().getSessionMap().get("id").toString()));
        if (m != null) {
            return;
        }

        Mark mark = new Mark();
        mark.setDate(new java.util.Date());
        mark.setGame(gameService.getGameById(Integer.valueOf(context.getExternalContext().getSessionMap().get("id").toString())));
        //context.getExternalContext().getSessionMap().remove("id");
        mark.setMark(mrk);
        mark.setReview(review);
        mark.setUser(userService.getUserById(SessionBean.getUserId()));
        markService.create(mark);

        inputText.setValue("");
        rating.setValue(0);
        //marksAndReviews = markService.getMarksByGameId(id);
    }

    public void deleteMarkAndReview(Mark mark) {
        if (mark.getUser().getId() == SessionBean.getUserId()) {//тут потом пользователя проверять
            markService.delete(mark.getId());
        }
    }

    public void addToFavourite() {

        FacesContext context = FacesContext.getCurrentInstance();
        boolean exist = true;
        UserGame userGame = userGameService.getUserGameByUserIdAndGameId(SessionBean.getUserId(), Integer.valueOf(context.getExternalContext().getSessionMap().get("id").toString()));
        if (userGame == null) {
            exist = false;
            userGame = new UserGame();
        }
        userGame.setGame(gameService.getGameById(Integer.valueOf(context.getExternalContext().getSessionMap().get("id").toString())));
        //context.getExternalContext().getSessionMap().remove("id");
        userGame.setUser(userService.getUserById(SessionBean.getUserId()));
        userGame.setGameStatus(gameStatusService.getGameStatusById(1));
        if (!exist) {
            userGame.setCanExchange(false);
            userGame.setWanted(false);
            userGame.setFavorite(true);
            userGameService.create(userGame);
        } else {
            userGame.setFavorite(true);
            userGameService.delete(userGame.getId());
            userGameService.update(userGame);
        }
        disableButtons(userGame);
    }

    public void addToWanted() {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean exist = true;
        UserGame userGame = null;
        try {
            userGame = userGameService.getUserGameByUserIdAndGameId(SessionBean.getUserId(), Integer.valueOf(context.getExternalContext().getSessionMap().get("id").toString()));
        } catch (Exception e) {

        }
        if (userGame == null) {
            exist = false;
            userGame = new UserGame();
        }
        userGame.setGame(gameService.getGameById(Integer.valueOf(context.getExternalContext().getSessionMap().get("id").toString())));
        //context.getExternalContext().getSessionMap().remove("id");
        userGame.setUser(userService.getUserById(SessionBean.getUserId()));

        userGame.setGameStatus(gameStatusService.getGameStatusById(1));
        if (!exist) {
            userGame.setCanExchange(false);
            userGame.setWanted(true);
            userGame.setFavorite(false);
            userGameService.create(userGame);
        } else {
            userGame.setWanted(true);
            userGameService.delete(userGame.getId());
            userGameService.update(userGame);
        }
        disableButtons(userGame);
    }

    public void addToExchange() {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean exist = true;
        UserGame userGame = null;
        try {
            userGame = userGameService.getUserGameByUserIdAndGameId(SessionBean.getUserId(), Integer.valueOf(context.getExternalContext().getSessionMap().get("id").toString()));
        } catch (Exception e) {

        }
        if (userGame == null) {
            exist = false;
            userGame = new UserGame();
        }
        userGame.setGame(gameService.getGameById(Integer.valueOf(context.getExternalContext().getSessionMap().get("id").toString())));
        //context.getExternalContext().getSessionMap().remove("id");
        userGame.setUser(userService.getUserById(SessionBean.getUserId()));

        userGame.setGameStatus(gameStatusService.getGameStatusById(1));
        if (!exist) {
            userGame.setCanExchange(true);
            userGame.setWanted(false);
            userGame.setFavorite(false);
            userGameService.create(userGame);
        } else {
            userGame.setCanExchange(true);
            userGameService.delete(userGame.getId());
            userGameService.update(userGame);
        }
        disableButtons(userGame);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private UserGame getUserGame() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserGame userGame = null;
        try {
            userGame = userGameService.getUserGameByUserIdAndGameId(SessionBean.getUserId(), Integer.valueOf(context.getExternalContext().getSessionMap().get("id").toString()));
        } catch (Exception e) {

        }
        return userGame;
    }

    private void disableButtons(UserGame userGame) {
        FacesContext context = FacesContext.getCurrentInstance();
        UIViewRoot uiViewRoot = context.getViewRoot();
        CommandButton commandButton;
        if (userGame.isFavorite()) {
            commandButton = (CommandButton) uiViewRoot.findComponent("addBut:favourite");
            commandButton.setDisabled(true);
        }
        if (userGame.isWanted()) {
            commandButton = (CommandButton) uiViewRoot.findComponent("addBut:wanted");
            commandButton.setDisabled(true);
        }
        if (userGame.isCanExchange()) {
            commandButton = (CommandButton) uiViewRoot.findComponent("addBut:exchange");
            commandButton.setDisabled(true);
        }

    }
}
