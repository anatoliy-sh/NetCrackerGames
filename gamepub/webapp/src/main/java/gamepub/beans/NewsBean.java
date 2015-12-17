package gamepub.beans;

import gamepub.db.entity.Comment;
import gamepub.db.entity.Game;
import gamepub.db.entity.News;
import gamepub.db.entity.User;
import gamepub.db.service.NewsService;
import gamepub.dto.NewsDto;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by roman on 03.12.15.
 */
@ManagedBean
@SessionScoped
@RequestScoped
public class NewsBean {
    @ManagedProperty(value = "#{param.id}")
    private int id;

    @EJB
    NewsService newsService;

    public News getNews()
    {
        return newsService.getNewsById(id);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
