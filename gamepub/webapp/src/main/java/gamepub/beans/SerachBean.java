package gamepub.beans;

import com.sun.faces.context.SessionMap;
import gamepub.db.entity.Game;
import gamepub.db.entity.Genre;
import gamepub.db.entity.Platform;
import gamepub.db.entity.UserGame;
import gamepub.db.service.GameService;
import gamepub.db.service.GenreService;
import gamepub.db.service.PlatformService;
import gamepub.db.service.UserGameService;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.component.selectoneradio.SelectOneRadio;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.IntegerConverter;
import java.util.*;

/**
 * Created by �������� on 06.01.2016.
 */
@ManagedBean
@SessionScoped
public class SerachBean {

    String genre = "0";
    List<HashMap.Entry<String, Object>> parametersList;


    @EJB
    GameService gameService;

    @EJB
    GenreService genreService;
    @EJB
    PlatformService platformService;

    String[] platform;
    String myGenre;
    String myGame;
    Date date;

    public void setGenres(String genreS) {
        genre = genreS;
    }

    public List<Game> getMyGames() {
        parametersList = new ArrayList<HashMap.Entry<String, Object>>();
        Map.Entry<String, Object> param;
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getExternalContext().getSessionMap().containsKey("genre") && !context.getExternalContext().getSessionMap().get("genre").toString().equals("0")) {
            genre = context.getExternalContext().getSessionMap().get("genre").toString();
            param = new HashMap.SimpleEntry<String, Object>("genre", genreService.getGenreById(Integer.parseInt(genre)));
            parametersList.add(param);
            //context.getExternalContext().getSessionMap().remove("genre");
        }
        if (context.getExternalContext().getSessionMap().containsKey("name")
                && context.getExternalContext().getSessionMap().get("name").toString().length() > 0) {
            String name = context.getExternalContext().getSessionMap().get("name").toString();
            param = new HashMap.SimpleEntry<String, Object>("name", name);
            parametersList.add(param);
            //context.getExternalContext().getSessionMap().remove("name");
        }
        if (context.getExternalContext().getSessionMap().containsKey("platform") && context.getExternalContext().getSessionMap().get("platform")!= null && ((String[])context.getExternalContext().getSessionMap().get("platform")).length>0) {
            String[] platform = (String[])context.getExternalContext().getSessionMap().get("platform");
            List<Platform> platforms = new ArrayList<Platform>();
            for(String p:platform){
                platforms.add(platformService.getPlatformById(Integer.parseInt(p)));
            }
            param = new HashMap.SimpleEntry<String, Object>("platform", platforms);
            parametersList.add(param);
        }
        if (context.getExternalContext().getSessionMap().containsKey("dateGame")) {
            param = new HashMap.SimpleEntry<String, Object>("dateGame", context.getExternalContext().getSessionMap().get("dateGame"));
            parametersList.add(param);
        }

        return gameService.getGamesByCustomParams(parametersList);

    }

    public List<Genre> getGenre() {
        List<Genre> genres = new ArrayList<Genre>();
        Genre genre = new Genre();
        genre.setName("None");
        genre.setId(0);
        genres.add(genre);
        genres.addAll(genreService.findAll());
        return genres;
    }

    public List<Platform> getPlatforms() {
        return platformService.findAll();
    }

    public void search() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (myGame != null) {
            facesContext.getExternalContext().getSessionMap().put("name", myGame);
        }

        if (myGenre != null) {
            facesContext.getExternalContext().getSessionMap().put("genre", myGenre);
        }

        if (platform != null) {
            facesContext.getExternalContext().getSessionMap().put("platform", platform);
        }
        if (date != null) {
            facesContext.getExternalContext().getSessionMap().put("dateGame", date);
        }

    }

    public String getGenreStr() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            genre = context.getExternalContext().getSessionMap().get("genre").toString();
        } catch (Exception e) {

        }
        return genre;
    }

    public String goToConcreteGame() {
        return "game?faces-redirect=true";
    }

    public String getMyGame() {
        return myGame;
    }

    public void setMyGame(String myGame) {
        this.myGame = myGame;
    }

    public String[] getPlatform() {
        return platform;
    }

    public void setPlatform(String[] platform) {
        this.platform = platform;
    }

    public String getMyGenre() {
        return myGenre;
    }

    public void setMyGenre(String myGenre) {
        this.myGenre = myGenre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

