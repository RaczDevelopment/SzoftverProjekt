package database;

import model.CareTaking;

import org.tinylog.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.*;

/**
 * This contains the database operations for the CaretakeController.
 */
public class CaretakersRepository {

    /**
     * This find rows by selectedColumn in the Caretaking table.
     * @param selectedColumn
     * @param entity
     * @return a list of CareTaking
     */
    public List<CareTaking> findByColumn(String selectedColumn, String entity) {
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
     * Insert new caretake in the CareTaking table.
     * @param newCareTake
     */
    public void insertCareTake(CareTaking newCareTake){
        EntityManager em = EmfGetter.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(newCareTake);
            em.getTransaction().commit();
            Logger.info("Inserting new caretake into the database successfully");
        }catch (Exception e){
            Logger.error("Inserting new caretake into the database failed");
        }finally {
            em.close();
        }
    }

    /**
     * Allows you to change the data in the Caretaking table cells.
     * @param change
     */
    public void commitChange(CareTaking change){
        EntityManager em = EmfGetter.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(change);
            em.getTransaction().commit();
        }catch (Exception e){
            Logger.error("Commit failed");
        }finally {
            em.close();
        }
    }

    /**
     * Remove Caretake from the table.
     * @param entity
     */
    public void removeCareTake(CareTaking entity){
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
