package database;

import model.Dolgozok;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    public static List<Dolgozok> getAllAsList() {
        EntityManager em = EmfGetter.getEntityManager();
        String selectedName ="Abz" + "%";
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Dolgozok> cq = cb.createQuery(Dolgozok.class);
        Root<Dolgozok> from = cq.from(Dolgozok.class);

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
}
