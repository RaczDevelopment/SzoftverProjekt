package database;

import model.Employees;
import org.tinylog.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    public static List<Employees> findByName(String selectedName) {
        EntityManager em = EmfGetter.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Employees> cq = cb.createQuery(Employees.class);
        Root<Employees> from = cq.from(Employees.class);

        cq.select(from).where(cb.like(from.get("name"), selectedName));
        try {
            Query q = em.createQuery(cq);
            return q.getResultList();
        } catch (Exception e) {

        } finally {
            em.close();
        }
        return new ArrayList<>();
    }

    public static void insertEmployee(Employees newEmployee){
        EntityManager em = EmfGetter.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(newEmployee);
            em.getTransaction().commit();
        }catch (Exception e){

        }finally {
            em.close();
        }
    }

    public static void  commitChange(Employees change){
        EntityManager em = EmfGetter.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(change);
            em.getTransaction().commit();
        }catch (Exception e){
            Logger.error("ERROR.");
        }finally {
            em.close();
        }
    }

    public static void removeEmployee(Employees entity){
        EntityManager em = EmfGetter.getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            em.getTransaction().commit();
        }catch (Exception e) {
        } finally {
            em.close();
        }
    }
}
