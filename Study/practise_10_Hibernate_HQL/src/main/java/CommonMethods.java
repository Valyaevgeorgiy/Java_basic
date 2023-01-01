import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class CommonMethods {

    public static<T> List<T> getAllEntriesUsingCriteriaApi(Session session,Class<T> myClass)
    {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(myClass);
        Root<T> rootEntry = cq.from(myClass);
        CriteriaQuery<T> all = cq.select(rootEntry);

        TypedQuery<T> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public  static <T> List<T> getAllEntriesUsingHQL(Session session, Class<T> myClass) {
        return session.createQuery("FROM Employee", myClass).getResultList();
    }

    public  static <T> List<T> getAllDepartmentsUsingHQL(Session session, Class<T> myClass) {
        return session.createQuery("FROM Department ", myClass).getResultList();
    }
}
