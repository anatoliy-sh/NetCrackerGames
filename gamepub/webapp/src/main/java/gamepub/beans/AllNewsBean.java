package gamepub.beans;

import gamepub.db.entity.Comment;
import gamepub.db.entity.Game;
import gamepub.db.entity.News;
import gamepub.db.service.GameService;
import gamepub.db.service.NewsService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by roman on 03.12.15.
 */
@ManagedBean
@SessionScoped
public class AllNewsBean {

    List<News> newses;

    @EJB
    NewsService newsService;

    @EJB
    GameService gameService;
    public List<News> getNewses() {
        return newsService.findAll();
    }

    public String goToConcreteNews() {
        return "news?faces-redirect=true";
    }
}
