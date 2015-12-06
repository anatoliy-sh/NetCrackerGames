package gamepub.db.dao;

import gamepub.db.entity.GameGenre;

import java.util.List;

/**
 * Created by roman on 06.12.15.
 */
public interface GameGenreDao extends BaseDao<GameGenre,Integer> {
    public GameGenre getGameGenreById(Integer id);
    public List<GameGenre> getGameGenresByGameId(Integer id);
    public List<GameGenre> getGameGenresByGenreId(Integer id);
}
