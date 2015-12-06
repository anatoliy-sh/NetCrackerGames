package gamepub.db.dao.implementation;

import gamepub.db.dao.NewsDao;
import gamepub.db.entity.News;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by roman on 06.12.15.
 */
public class NewsDaoImplementation extends BaseDaoImplementation<News,Integer> implements NewsDao {
    public NewsDaoImplementation(){
        super(News.class);
    }

    public News getNewsById(Integer id) {
        String jpa = "SELECT n FROM News n WHERE n.id= :id";
        HashMap<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        try
        {
            return this.ExecuteQuery(jpa, parameters).get(0);
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<News> getNewsByName(String name) {
        String jpa = "SELECT n FROM News n WHERE n.name= :name";
        HashMap<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("name",name);
        return this.ExecuteQuery(jpa, parameters);
    }

    public List<News> getNewsByGameId(Integer id) {
        String jpa = "SELECT n FROM News n WHERE n.game.id= :id";
        HashMap<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        return this.ExecuteQuery(jpa, parameters);
    }

    public List<News> getNewsByDate(Date date) {
        String jpa = "SELECT n FROM News n WHERE n.date= :date";
        HashMap<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("date",date);
        return this.ExecuteQuery(jpa, parameters);
    }
}
