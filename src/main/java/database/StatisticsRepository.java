package database;

import model.CareTaking;
import org.tinylog.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This contains the database operations for the StatisticsController.
 */
public class StatisticsRepository {

    /**
     * Find persons between two date.
     * @param fromWhatTime
     * @param howLong
     * @return a List which contains CareTakings.
     */
    public List<CareTaking> findPersons(LocalDate fromWhatTime, LocalDate howLong) {
        EntityManager em = EmfGetter.getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<CareTaking> cq = cb.createQuery(CareTaking.class);
        Root<CareTaking> from = cq.from(CareTaking.class);

        if(fromWhatTime == howLong)  {
            cq.select(from).where(cb.equal(from.get("date"), fromWhatTime));
        }else{
            cq.select(from).where(cb.between(from.get("date"), fromWhatTime, howLong));
        }

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
     * Give the number of Elders who are currently in the database.
     * @return a String type number
     */
    public String sumElder (){
        EntityManager em = EmfGetter.getEntityManager();

        String queryString = "SELECT Count(*) FROM Gondozottak";

        try {
            Query q = em.createNativeQuery(queryString);
            Logger.info("Select completed successful");
            return q.getSingleResult().toString();
        } catch (Exception e) {
            Logger.error("Select failed");
        } finally {
            em.close();
        }
        return new String();
    }

    /**
     * Give the number of Employees who are currently in the database.
     * @return a String type number
     */
    public String sumEmployee (){
        EntityManager em = EmfGetter.getEntityManager();

        String queryString = "SELECT Count(*) FROM Dolgozok";

        try {
            Query q = em.createNativeQuery(queryString);
            Logger.info("Select completed successful");
            return q.getSingleResult().toString();
        } catch (Exception e) {
            Logger.error("Select failed");
        } finally {
            em.close();
        }
        return new String();
    }
}
