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

/**
 * This contains the database operations for the EmployeeController.
 */
public class EmployeeRepository {

    /**
     * This find rows by name in the Employees table.
     * @param selectedName
     * @return a list of Employees
     */
    public List<Employees> findByName(String selectedName) {
        EntityManager em = EmfGetter.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Employees> cq = cb.createQuery(Employees.class);
        Root<Employees> from = cq.from(Employees.class);

        cq.select(from).where(cb.like(from.get("name"), selectedName));
        try {
            Query q = em.createQuery(cq);
            Logger.info("Select completed successful");
            return q.getResultList();
        } catch (Exception e) {
            Logger.error("Select failed");
        } finally {
            em.close();
        }
        return new ArrayList<>();
    }

    /**
     * Insert new employee in the table.
     * @param newEmployee
     */
    public void insertEmployee(Employees newEmployee){
        EntityManager em = EmfGetter.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(newEmployee);
            em.getTransaction().commit();
            Logger.info("Inserting new employee into the database successfully");
        }catch (Exception e){
            Logger.error("Inserting new employee into the database failed");
        }finally {
            em.close();
        }
    }

    /**
     * Allows you to change the data in the Employee table cells.
     * @param change
     */
    public void  commitChange(Employees change){
        EntityManager em = EmfGetter.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(change);
            em.getTransaction().commit();
            Logger.info("Commit success");
        }catch (Exception e){
            Logger.error("Commit failed");
        }finally {
            em.close();
        }
    }

    /**
     * Remove Employee from the table.
     * @param entity
     */
    public void removeEmployee(Employees entity){
        EntityManager em = EmfGetter.getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            em.getTransaction().commit();
            Logger.info("Entity removed from the database successfully");
        }catch (Exception e) {
            Logger.error("Remove failed");
        } finally {
            em.close();
        }
    }
}
