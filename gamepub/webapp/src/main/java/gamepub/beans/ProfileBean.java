package gamepub.beans;

import gamepub.db.entity.Game;
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
        return "User";
    }
    public String getDescription() {
        return "UserDescription User stories are often written on index cards or sticky notes," +
                " stored in a shoe box, and arranged on walls or tables to facilitate planning and discussion." +
                " As such, they strongly shift the focus from writing about features to discussing them. " +
                "In fact, these discussions are more important than whatever text is written.";
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
