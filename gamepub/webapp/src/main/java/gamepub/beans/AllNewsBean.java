package gamepub.beans;

import gamepub.db.entity.Comment;
import gamepub.db.entity.Game;
import gamepub.db.entity.News;

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

    public List<News> getNewses() {
        List<News> newses = new ArrayList<News>();
        News news = new News();
        news.setId(1);
        news.setLink("qqqq");
        news.setDate(new Date());
        news.setName("News");
        Game game = new Game();
        game.setName("Game 1");
        news.setGame(game);
        newses.add(news);

        news = new News();
        news.setId(2);
        news.setLink("qqqq");
        news.setDate(new Date());
        news.setName("News");
        game = new Game();
        game.setName("Game 1");
        news.setGame(game);
        newses.add(news);

        news = new News();
        news.setId(3);
        news.setLink("qqqq");
        news.setDate(new Date());
        news.setName("News");
        game = new Game();
        game.setName("Game 2");
        news.setGame(game);
        newses.add(news);

        return newses;
    }

    public String goToConcreteNews() {
        return "news?faces-redirect=true";
    }
}
