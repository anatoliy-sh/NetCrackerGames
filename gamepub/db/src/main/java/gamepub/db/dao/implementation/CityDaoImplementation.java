package gamepub.db.dao.implementation;

import gamepub.db.dao.CityDao;
import gamepub.db.entity.City;

import java.util.HashMap;
import java.util.List;

/**
 * Created by roman on 05.12.15.
 */
public class CityDaoImplementation extends BaseDaoImplementation<City,Integer> implements CityDao {
    public CityDaoImplementation()
    {
        super(City.class);
    }

    public City getCityById(Integer id) {
        String jpa = "SELECT c FROM City c WHERE c.id = :id";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        try
        {
            return this.ExecuteQuery(jpa, parameters).get(0);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public List<City> getCitiesById(Integer id) {
        String jpa = "SELECT c FROM City c WHERE c.country.id = :id ORDER BY c.name";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        return this.ExecuteQuery(jpa, parameters);
    }
}
