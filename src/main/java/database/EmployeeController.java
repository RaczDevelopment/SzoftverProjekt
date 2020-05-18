package database;

import model.Dolgozok;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.util.List;


public class EmployeeController {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersDB");

    public static List<Dolgozok> getEmployee(){
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT e FROM Dolgozok e", Dolgozok.class);
        em.close();
        return q.getResultList();
    }
}
