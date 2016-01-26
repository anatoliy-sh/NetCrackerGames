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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import net.bootsfaces.component.commandButton.CommandButton;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.rating.Rating;
import org.primefaces.context.RequestContext;

/**
 *
 * @author ����
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
        HttpSession ses = SessionBean.getSession();
        ses.setAttribute("gameid", id);
        Game g = gameService.getGameById(id);
        UserGame userGame = getUserGame();
        if (userGame != null) {
            disableButtons(userGame);
        }
        return g;
    }

    public List<Mark> getMarksAndReviews() {
        id = SessionBean.getGameId();
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
        
        FacesMessage errMes;
        if (review == null || review.isEmpty() || review.length() >= 501) {
             errMes= new FacesMessage(FacesMessage.SEVERITY_INFO, "", "write a riview");
             RequestContext.getCurrentInstance().showMessageInDialog(errMes);
            return;
        }
        if (mrk == 0) {
            errMes= new FacesMessage(FacesMessage.SEVERITY_INFO, "", "rate this game");
             RequestContext.getCurrentInstance().showMessageInDialog(errMes);
            return;
        }
        Mark m = null;
        try{
            m = markService.getMarkByUserAndGameId(SessionBean.getUserId(),
                    SessionBean.getGameId());
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        if (m != null) {
            errMes= new FacesMessage(FacesMessage.SEVERITY_INFO, "", "you've already left a review");
             RequestContext.getCurrentInstance().showMessageInDialog(errMes);
            return;
        }
        Mark mark = new Mark();
        mark.setDate(new java.util.Date());
        mark.setGame(gameService.getGameById(SessionBean.getGameId()));
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
        if (mark.getUser().getId() == SessionBean.getUserId()) {//��� ����� ������������ ���������
            markService.delete(mark.getId());
        }
        else{
            FacesMessage errMes= new FacesMessage(FacesMessage.SEVERITY_WARN, "error", "no rights to delete");
               RequestContext.getCurrentInstance().showMessageInDialog(errMes);
        }
    }

    public void addToFavourite() {

        FacesContext context = FacesContext.getCurrentInstance();
        boolean exist = true;
        UserGame userGame = userGameService.getUserGameByUserIdAndGameId(SessionBean.getUserId(), SessionBean.getGameId());
        if (userGame == null) {
            exist = false;
            userGame = new UserGame();
        }
        userGame.setGame(gameService.getGameById(SessionBean.getGameId()));
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
            userGame = userGameService.getUserGameByUserIdAndGameId(SessionBean.getUserId(), SessionBean.getGameId());
        } catch (Exception e) {

        }
        if (userGame == null) {
            exist = false;
            userGame = new UserGame();
        }
        userGame.setGame(gameService.getGameById(SessionBean.getGameId()));
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
        
        boolean exist = true;
        UserGame userGame = null;
        try {
            userGame = userGameService.getUserGameByUserIdAndGameId(SessionBean.getUserId(),SessionBean.getGameId() );
        } catch (Exception e) {

        }
        if (userGame == null) {
            exist = false;
            userGame = new UserGame();
        }
        userGame.setGame(gameService.getGameById(SessionBean.getGameId()));
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
        
        UserGame userGame = null;
        try {
            userGame = userGameService.getUserGameByUserIdAndGameId(SessionBean.getUserId(), SessionBean.getGameId());
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
