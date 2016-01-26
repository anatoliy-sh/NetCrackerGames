package gamepub.db.dao.implementation;

import gamepub.db.dao.GameDao;
import gamepub.db.entity.Game;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by roman on 06.12.15.
 */
public class GameDaoImplementation extends BaseDaoImplementation<Game, Integer> implements GameDao {
    public GameDaoImplementation() {
        super(Game.class);
    }

    public Game getGameById(Integer id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Game> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<Integer>get("id"), id));
        try {
            return (Game)getEntityManager().createQuery(cq).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public List<Game> getGamesByName(String name) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Game> root = cq.from(instance);
        cq.select(root);
        cq.where(cb.equal(root.<String>get("name"), name));
        cq.orderBy(cb.asc(root.<String>get("name")));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<Game> getGamesByCustomParams(List<HashMap.Entry<String, Object>> parameterList) {
        String jpa = "Select DISTINCT g.game FROM GameGenre g, GamePlatform gp WHERE gp.game=g.game";
        if (parameterList.size() == 0) {
            return this.ExecuteQuery(jpa);
        }
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        for (HashMap.Entry<String, Object> param : parameterList) {
            if (param.getKey().equals("name")) {
                jpa += " AND g.game.name LIKE :name";
                parameters.put(param.getKey(), "%" + param.getValue() + "%");
            } else {
                if (param.getKey().equals("platform")) {
                    jpa += " AND gp.platform= :platform";
                } else if (param.getKey().equals("genre")) {
                    jpa += " AND g.genre= :genre";
                } else jpa += " AND g.game.releaseDate<= :dateGame";
                parameters.put(param.getKey(),param.getValue());
            }
        }
        return this.ExecuteQuery(jpa, parameters);
    }

    public List<Game> getGamesOrderByMarks(int maxValue){
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Game> root = cq.from(instance);
        cq.select(root);
        cq.orderBy(cb.desc(root.<Integer>get("metacritic")), cb.desc(root.<Integer>get("releaseDate")));
        List<Game> queryResult = getEntityManager().createQuery(cq).setMaxResults(maxValue).getResultList();
        List<Game> result = new ArrayList<Game>();
        result = queryResult;
    /*    for(int i = 1; i<=10 && queryResult.size()<maxValue+i; i++)
            result.add(queryResult.get(maxValue+i));*/
        return result;
    }
}
