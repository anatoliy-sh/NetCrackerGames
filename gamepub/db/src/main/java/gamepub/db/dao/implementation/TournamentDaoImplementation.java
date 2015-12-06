package gamepub.db.dao.implementation;

import gamepub.db.dao.TournamentDao;
import gamepub.db.entity.Tournament;

import java.util.HashMap;
import java.util.List;

/**
 * Created by roman on 06.12.15.
 */
public class TournamentDaoImplementation extends BaseDaoImplementation<Tournament,Integer> implements TournamentDao {
    public TournamentDaoImplementation(){
        super(Tournament.class);
    }

    public Tournament getTournamentById(Integer id) {
        String jpa = "SELECT t FROM Tournament t WHERE t.id = :id";
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

    public List<Tournament> getTournamentsByName(String name) {
        String jpa = "SELECT t FROM Tournament t WHERE t.name = :name";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("name",name);
        return this.ExecuteQuery(jpa, parameters);
    }

    public List<Tournament> getTournamentsByGameId(Integer id) {
        String jpa = "SELECT t FROM Tournament t WHERE t.game.id = :id";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id",id);
        return this.ExecuteQuery(jpa, parameters);
    }
}
