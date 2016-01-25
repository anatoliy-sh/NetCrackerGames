/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamepub.beans;

import gamepub.db.entity.Game;
import gamepub.db.entity.UserGame;
import gamepub.db.service.GameService;
import gamepub.db.service.UserGameService;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Иван
 */
@ManagedBean
@SessionScoped
public class MyGamesBean {

    List<Game> myGames;
    List<UserGame> userGame;

    @EJB
    GameService gameService;

    @EJB
    UserGameService userGameService;

    private String listGames;

    public String getListGames() {
        return listGames;
    }

    public void setListGames(String listGames) {
        this.listGames = listGames;
    }

    public List<Game> getMyGames() {
        List<Game> tmpGames = new ArrayList<Game>();
        int userId = SessionBean.getUserId();
        userGame = userGameService.getUserGamesByUserId(userId);

        for(int i = 0; i<userGame.size(); i++){
            UserGame game = userGame.get(i);
            if(listGames.equals("1") || listGames.equals("2") && game.isFavorite()
                    ||  listGames.equals("3") && game.isWanted())
                tmpGames.add(userGame.get(i).getGame());
        }
        return tmpGames;
    }
    
    public String goToConcreteGame() {
        return "game?faces-redirect=true";
    }
}

