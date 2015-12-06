package gamepub.db.dao.implementation;

import gamepub.db.dao.GenreDao;
import gamepub.db.entity.Genre;

import java.util.HashMap;

/**
 * Created by roman on 06.12.15.
 */
public class GenreDaoImplementation extends BaseDaoImplementation<Genre,Integer> implements GenreDao {
    public GenreDaoImplementation(){
        super(Genre.class);
    }

    public Genre getGenreById(Integer id) {
        String jpa = "SELECT g FROM Genre g WHERE g.id= :id";
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
}
