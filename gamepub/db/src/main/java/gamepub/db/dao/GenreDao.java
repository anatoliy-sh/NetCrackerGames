package gamepub.db.dao;

import gamepub.db.entity.Genre;

/**
 * Created by roman on 06.12.15.
 */
public interface GenreDao extends BaseDao<Genre,Integer> {
    public Genre getGenreById(Integer id);
}
