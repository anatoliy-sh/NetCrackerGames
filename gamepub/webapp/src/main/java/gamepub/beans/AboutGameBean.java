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
import gamepub.db.service.UserGameService;
import gamepub.db.service.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

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
    
    Game game;

    public Game getGame()
    {
        Game g = gameService.getGameById(id);  
        return g;
        
    }
    public void addToMyGame(){
        UserGame userGame=new UserGame();      
        userGame.setGame(gameService.getGameById(id));
        userGame.setUser(userService.getUserById(1));
        userGameService.create(userGame);
    }
 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
