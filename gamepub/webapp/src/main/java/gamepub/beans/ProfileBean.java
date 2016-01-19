package gamepub.beans;

import gamepub.db.entity.Game;
import gamepub.db.entity.User;
import gamepub.db.service.GameService;
import gamepub.db.service.UserService;

import javax.ejb.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Анатолий on 06.01.2016.
 */
@ManagedBean
@SessionScoped
public class ProfileBean {

    private int id;
    List<Game> myGames;



    @EJB
    UserService userService;
    @EJB
    GameService gameService;



    public String getName() {

        id = SessionBean.getUserId();
        User user = userService.getUserById(id);
        return user.getLogin();
    }
    public String getDescription() {
        User user = userService.getUserById(id);
        return user.getFbInfo() + user.getEmail() + user.getCity().getName()
                + user.getCity().getCountry().getName();
    }

    public String getImage(){
        User user = userService.getUserById(id);
        return user.getAvatarUrl();
    }

    public List<Game> getMyGames() {

        return gameService.getGamesOrderByMarks(4);
    }

}
