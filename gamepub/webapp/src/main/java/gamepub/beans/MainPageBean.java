package gamepub.beans;

import gamepub.db.entity.Game;
import gamepub.db.service.GameService;
import sun.util.resources.cldr.es.CalendarData_es_GQ;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Анатолий on 17.12.2015.
 */
@ManagedBean
@SessionScoped
public class MainPageBean {

    List<Game> myGames;

    @EJB
    GameService gameService;

    public List<Game> getMyGames() {
        myGames = gameService.findAll();
        List<Game> tmp= new ArrayList<Game>(4);
        for (int i=0; i<4; i++){
            tmp.add(myGames.get(i));
        }
        return tmp;
    }

    public String goToConcreteGame() {
        return "game?faces-redirect=true";
    }
}