package gamepub.db.dao.implementation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by roman on 05.12.15.
 */
public class EntityManagerCreator {
    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("PERSISTENCE");
    }

    public static EntityManager CreateEntityManager(){
        return emf.createEntityManager();
    }

    public static void close(){
        emf.close();
    }
}
