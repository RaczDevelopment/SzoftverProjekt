package database;

import model.Dolgozok;
import model.Gondozottak;
import org.tinylog.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class EldersRepository {

    public static List<Gondozottak> findByName(String selectedName) {
        EntityManager em = EmfGetter.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Gondozottak> cq = cb.createQuery(Gondozottak.class);
        Root<Gondozottak> from = cq.from(Gondozottak.class);

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

    public static void insertElder(Gondozottak newElder){
        EntityManager em = EmfGetter.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(newElder);
            em.getTransaction().commit();
        }catch (Exception e){

        }finally {
            em.close();
        }
    }

    public static void  commitChange(Gondozottak change){
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

    public static void removeEmployee(Gondozottak entity){
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
