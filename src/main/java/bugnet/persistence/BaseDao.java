package bugnet.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;

public interface BaseDao {


    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Inserts a record
     * @param record record being inserted
     * @return id of record
     */
    default int create(Object record) {
        int id;
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        Transaction transaction = session.beginTransaction();
        id = (int) session.save(record);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Saves or updates a record
     * @param record record being updated/saved
     */
    default void saveOrUpdate(Object record) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(record);
        transaction.commit();
        session.close();
    }

    /**
     * Deletes a record
     * @param record record to be deleted
     */
    default void delete(Object record) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(record);
        transaction.commit();
        session.close();
    }
}
