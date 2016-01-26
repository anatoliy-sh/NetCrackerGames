/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamepub.beans;

import gamepub.db.entity.Game;
import gamepub.db.entity.User;
import gamepub.db.entity.UserGame;
import gamepub.db.service.GameService;
import gamepub.db.service.UserGameService;
import gamepub.db.service.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author fitok
 */
@ManagedBean
@ViewScoped
public class TradeBean {
    @EJB
    UserService userService;
    @EJB
    GameService gameService;
    @EJB
    UserGameService userGameService;
    
    private List<String> userNames;
    private List<Game> wantedGames;
    
    public TradeBean() {
    }

    /**
     * @param game
     * @return the userList
     */
    public List<String> getUserNames(Game game) {
        userNames = new ArrayList<String>();
         List<UserGame> exchangeUserGameList=userGameService.getCanExchangeUserGamesByGameId(game.getId());
        for(UserGame ug:exchangeUserGameList){
        userNames.add(ug.getUser().getLogin());}
        
        return userNames;
        
    }

    /**
     * @param userList the userList to set
     */
    public void setUserNames(List<String> userList) {
        this.userNames = userList;
    }

    /**
     * @return the userGameList
     */
    

    /**
     * @return the wantedGames
     */
    public List<Game> getWantedGames() {
        wantedGames = new ArrayList<Game>(); 
        List<UserGame> wantedUserGameList = userGameService.getWantedUserGamesByUserId(SessionBean.getUserId());
        for(UserGame ug:wantedUserGameList){
        wantedGames.add(ug.getGame());}
        
        return wantedGames;
    }

    /**
     * @param wantedGames the wantedGames to set
     */
    public void setWantedGames(List<Game> wantedGames) {
        this.wantedGames = wantedGames;
    }

    public boolean noUsersCheck(Game game){
       return (getUserNames(game).isEmpty());
       
        
    }
    
}
