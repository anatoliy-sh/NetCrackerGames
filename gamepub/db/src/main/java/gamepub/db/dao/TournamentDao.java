package gamepub.db.dao;

import gamepub.db.entity.Tournament;

import java.util.List;

/**
 * Created by roman on 06.12.15.
 */
public interface TournamentDao extends BaseDao<Tournament,Integer> {
    public Tournament getTournamentById(Integer id);

    public List<Tournament> getTournamentsByName(String name);
    public List<Tournament> getTournamentsByGameId(Integer id);
}
