/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamepub.beans;

import gamepub.db.entity.Game;
import gamepub.db.entity.News;
import gamepub.db.entity.Platform;
import gamepub.db.entity.User;
import gamepub.db.service.GameService;
import gamepub.db.service.NewsService;
import gamepub.db.service.UserService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ManagedBean
@SessionScoped
public class LifeSearchBean {

    String request;
    @EJB
    NewsService newsService;
    @EJB
    GameService gameService;
    @EJB
    UserService userService;

    public String getRequest(){
        return request;
    }

    public void setRequest(String request){
        this.request  = request;
    }
    public void lifeSearch() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if(request != null){
            facesContext.getExternalContext().getSessionMap().put("name", request);
        }
    }
    public List<News> getNewses() {
        List<HashMap.Entry<String, Object>> parametersList = new ArrayList<HashMap.Entry<String, Object>>();
        Map.Entry<String, Object> param;
        FacesContext context = FacesContext.getCurrentInstance();
        if(request != null && request.length()>0) {
            param = new HashMap.SimpleEntry<String, Object>("name", request);
            parametersList.add(param);
            return newsService.getNewsByCustomParams(parametersList);
        }
        return null;
    }
    public List<Game> getGames() {
        List<HashMap.Entry<String, Object>> parametersList = new ArrayList<HashMap.Entry<String, Object>>();
        Map.Entry<String, Object> param;
        if(request != null && request.length()>0) {
            param = new HashMap.SimpleEntry<String, Object>("name", request);
            parametersList.add(param);
            return gameService.getGamesByCustomParams(parametersList);
        }
        return null;
    }
    public List<User> getUsers() {
        List<HashMap.Entry<String, Object>> parametersList = new ArrayList<HashMap.Entry<String, Object>>();
        Map.Entry<String, Object> param;
        if (request != null && request.length()>0) {
            param = new HashMap.SimpleEntry<String, Object>("login", request);
            parametersList.add(param);
            return userService.getUsersByCustomParams(parametersList);
        }
        return null;
    }
}
