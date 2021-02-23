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
public class SpecimenDao implements BaseDao {

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

    public Specimen getSpecimenById(int id) {
        logger.debug("getting specimen of id " + id);

        Session session = sessionFactory.openSession();
        Specimen specimen = session.get(Specimen.class, id);
        session.close();
        return specimen;
    }
}
