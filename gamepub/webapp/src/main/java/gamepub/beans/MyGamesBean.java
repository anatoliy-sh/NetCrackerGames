/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamepub.beans;

import gamepub.db.entity.Game;
import gamepub.db.service.GameService;
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

    @EJB
    GameService gameService;

    public List<Game> getMyGames() {
        return gameService.findAll();
    }
    
    public String goToConcreteGame() {
        return "game?faces-redirect=true";
    }
}

