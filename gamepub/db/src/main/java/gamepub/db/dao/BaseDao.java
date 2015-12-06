package gamepub.db.dao;

import java.util.List;

/**
 * Created by roman on 05.12.15.
 */
public interface BaseDao<T,PK> {
    public T create(T t);
    public T find (PK id);
    public List<T> findAll();
    public T update(T t);
    public void delete(PK id);
    public void delete(List<T> t);
}
