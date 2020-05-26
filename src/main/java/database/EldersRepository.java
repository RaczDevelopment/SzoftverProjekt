package database;

import model.Elders;
import org.tinylog.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * This contains the database operations for the EldersController.
 */
public class EldersRepository {

    /**
     * This find rows by name in the Employees table.
     * @param selectedName
     * @return a list of Elders
     */
    public List<Elders> findByName(String selectedName) {
        EntityManager em = EmfGetter.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Elders> cq = cb.createQuery(Elders.class);
        Root<Elders> from = cq.from(Elders.class);

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
     * Insert new elder in the table.
     * @param newElder
     */
    public void insertElder(Elders newElder){
        EntityManager em = EmfGetter.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(newElder);
            em.getTransaction().commit();
            Logger.info("Inserting new elder into the database successfully");
        }catch (Exception e){
            Logger.error("Inserting new elder into the database failed");
        }finally {
            em.close();
        }
    }

    /**
     * Allows you to change the data in the Elder table cells.
     * @param change
     */
    public void commitChange(Elders change){
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
     * Remove Elder from the table.
     * @param entity
     */
    public void removeEmployee(Elders entity){
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
