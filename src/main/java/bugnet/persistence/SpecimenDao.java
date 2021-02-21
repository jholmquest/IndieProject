package bugnet.persistence;

import bugnet.entity.Specimen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * CRUD for specimen
 *
 * @author James Holmquest
 */
public class SpecimenDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Gets all specimens
     *
     * @return list of all specimens
     */
    public List<Specimen> getAllSpecimens() {
        logger.debug("getting all specimens");

        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Specimen> query = builder.createQuery(Specimen.class);
        Root<Specimen> root = query.from(Specimen.class);
        List<Specimen> specimens = session.createQuery(query).getResultList();
        session.close();
        logger.debug("list of specimens " + specimens);
        return specimens;
    }

    /**
     * inserts a specimen
     *
     * @param specimen specimen to be inserted
     * @return id of new specimen
     */
    public int createSpecimen(Specimen specimen) {
        logger.debug("inserting specimen " + specimen);

        int id;
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        Transaction transaction = session.beginTransaction();
        id = (int) session.save(specimen);
        transaction.commit();
        session.close();

        logger.debug("inserted specimen with id " + id);
        return id;
    }

    /**
     * updates a specimen in the db with new values
     *
     * @param specimen new specimen info
     */
    public void updateSpecimen(Specimen specimen) {
        logger.debug("updating specimen " + specimen);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(specimen);
        transaction.commit();
        session.close();
    }

    /**
     * deletes a specimen
     *
     * @param specimen specimen to be deleted
     */
    public void delete(Specimen specimen) {
        logger.debug("deleting specimen " + specimen);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(specimen);
        transaction.commit();
        session.close();
    }
}
