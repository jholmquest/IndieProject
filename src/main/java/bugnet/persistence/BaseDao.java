package bugnet.persistence;

import bugnet.entity.Specimen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;

public interface BaseDao {


    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Inserts a record
     * @param object record being inserted
     * @return id of record
     */
    default int create(Object object) {
        int id;
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        Transaction transaction = session.beginTransaction();
        id = (int) session.save(object);
        transaction.commit();
        session.close();
        return id;
    }
}
