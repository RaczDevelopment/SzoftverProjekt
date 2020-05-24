package database;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import model.CareTaking;
import model.Elders;
import org.tinylog.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CaretakeRepository {

    public static void insertCareTake(CareTaking newCareTake){
        EntityManager em = EmfGetter.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(newCareTake);
            em.getTransaction().commit();
        }catch (Exception e){

        }finally {
            em.close();
        }
    }

    public static List<CareTaking> findByColumn(String selectedColumn, String entity) {
        EntityManager em = EmfGetter.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<CareTaking> cq = cb.createQuery(CareTaking.class);
        Root<CareTaking> from = cq.from(CareTaking.class);
        if(entity.isEmpty()){
            cq.select(from);
        } else {
            if (selectedColumn.equals("id") || selectedColumn.equals("price")) {
                Integer number = Integer.parseInt(entity);
                cq.select(from).where(cb.equal(from.get(selectedColumn), number));
            } else if (selectedColumn.equals("date")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                LocalDate date = LocalDate.parse(entity, formatter);
                cq.select(from).where(cb.equal(from.get(selectedColumn), date));
            } else if (selectedColumn.equals("careTimeWithoutTravel") || selectedColumn.equals("careTime")) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime time = LocalTime.parse(entity, dtf);
                cq.select(from).where(cb.equal(from.get(selectedColumn), time));
            } else {
                cq.select(from).where(cb.like(from.get(selectedColumn), "%" + entity + "%"));
            }
        }
        try {
            Query q = em.createQuery(cq);
            return q.getResultList();
        } catch (Exception e) {
        } finally {
            em.close();
        }
        return new ArrayList<>();
    }

    public static void  commitChange(CareTaking change){
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

    public static void removeCareTake(CareTaking entity){
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
