package gamepub.beans;

import gamepub.db.entity.City;
import gamepub.db.entity.Game;
import gamepub.db.entity.User;
import gamepub.db.entity.UserGame;
import gamepub.db.service.CityService;
import gamepub.db.service.GameService;
import gamepub.db.service.UserGameService;
import gamepub.db.service.UserService;
import org.hibernate.Session;

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
    List<UserGame> userGame;
    private boolean isEdit;
    private boolean isMy;
    private int cityId;
    private String email, fbInfo, name, password;


    @EJB
    UserService userService;
    @EJB
    GameService gameService;
    @EJB
    CityService cityService;
    @EJB
    UserGameService userGameService;

    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {

        id = SessionBean.getUserId();
        int tmpId = Integer.parseInt(userId);
        if(tmpId != 0)
            id = Integer.parseInt(userId);


        User user = userService.getUserById(id);
        isEdit = false;
        return user.getLogin();
    }

    public String getImage() {
        User user = userService.getUserById(id);
        return user.getAvatarUrl();
    }

    public String getCountry() {
        User user = userService.getUserById(id);
        return user.getCity().getCountry().getName();
    }

    public String getEmail() {
        User user = userService.getUserById(id);
        return user.getEmail();
    }

    public void setEmail(String uemail) {
        email = uemail;
    }

    public String getFbInfo() {
        User user = userService.getUserById(id);
        if (user.getFbInfo() == null)
            return " ";
        return user.getFbInfo();
    }

    public void setFbInfo(String ufbInfo) {
        fbInfo = ufbInfo;
    }


    public int getCityId() {
        return cityId;
    }

    public void setCityId(int ucityId) {
        cityId = ucityId;
    }

    public String getCity() {
        User user = userService.getUserById(id);
        return user.getCity().getName();
    }


    public List<City> getCities() {
        return cityService.findAll();
    }

    public boolean getIsEdit() {
        return isEdit;
    }
    public boolean getIsMy(){
        return Integer.parseInt(userId) == 0;
    }

    public List<Game> getRecomendGames() {

        return gameService.getGamesOrderByMarks(4);
    }

    public void edit() {
        isEdit = true;
    }

    public void update(){
        User user = userService.getUserById(id);
        if(cityId != 0){
            user.setCity(cityService.getCityById(cityId));
        }
        if(fbInfo != null){
            user.setFbInfo(fbInfo);
            System.out.println(fbInfo);
        }
        System.out.println(fbInfo);
        isEdit = false;
        //userService.delete(user.getId());
        userService.update(user);

    }

    //Games
    
    public List<UserGame> getMyGames() {
        return userGameService.getUserGamesByUserId(SessionBean.getUserId());       
    }

    public List<UserGame> getFavouriteGames() {
        return userGameService.getFavoriteUserGamesByUserId(SessionBean.getUserId());
    }

    public List<UserGame> getExchangeGames() {
        return userGameService.getCanExchangeUserGamesByUserId(SessionBean.getUserId());
    }

    public void deleteMyGame(UserGame myGame) {
        userGameService.delete(myGame.getId());
    }
}
