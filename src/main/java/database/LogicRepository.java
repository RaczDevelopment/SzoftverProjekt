package database;

import model.CareTaking;
import model.Employees;
import model.Statistics;
import org.tinylog.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;

public class LogicRepository {

    public static List<CareTaking> findPersons() {
        EntityManager em = EmfGetter.getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<CareTaking> cq = cb.createQuery(CareTaking.class);
        Root<CareTaking> from = cq.from(CareTaking.class);
        cq.select(from);

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

    /*public static Integer getSum(String name, LocalDate fromWhatTime, LocalDate howLong){
        EntityManager em = EmfGetter.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<CareTaking> cq = cb.createQuery(CareTaking.class);
        Root<CareTaking> from = cq.from(CareTaking.class);


        cq.select(from).where();
    }*/
}
