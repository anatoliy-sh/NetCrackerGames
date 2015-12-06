package gamepub.db.dao.implementation;

import gamepub.db.dao.CountryDao;
import gamepub.db.entity.Country;

import java.util.HashMap;
import java.util.List;

/**
 * Created by roman on 05.12.15.
 */
public class CountryDaoImplementation extends BaseDaoImplementation<Country, Integer> implements CountryDao {

    public CountryDaoImplementation() {
        super(Country.class);
    }

    public Country getCountryById(Integer id) {
        String jpa = "SELECT c FROM Country c WHERE c.id= :id";
        HashMap<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        try {
            return this.ExecuteQuery(jpa, parameters).get(0);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
