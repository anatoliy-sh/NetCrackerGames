package gamepub.beans;

import gamepub.db.entity.Game;
import gamepub.db.entity.User;
import gamepub.db.service.GameService;
import gamepub.db.service.UserService;

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
public class ProfileBean {

    private int id = 1;
    List<Game> myGames;



    @EJB
    UserService userService;
    @EJB
    GameService gameService;



    public String getName() {
        User user = userService.getUserById(id);
        return user.getLogin();
    }
    public String getDescription() {
        User user = userService.getUserById(id);
        return user.getFbInfo() + user.getEmail() + user.getCity();
    }

    public String getImage(){
        User user = userService.getUserById(id);
        return user.getAvatarUrl();
    }

    public List<Game> getMyGames() {
        myGames = gameService.findAll();
        List<Game> tmp= new ArrayList<Game>(4);
        for (int i=0; i<6; i++){
            tmp.add(myGames.get(i));
        }
        return tmp;
    }

}
