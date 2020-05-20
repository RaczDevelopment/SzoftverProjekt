package database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmfGetter {
    private EmfGetter(){}

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersDB");

    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    public static void closeEmf(){
        emf.close();
    }

}
