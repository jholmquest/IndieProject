package bugnet.persistence;

import bugnet.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * CRUD for user
 *
 * @author James Holmquest
 */
public class UserDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Gets all users
     * @return list of all users
     */
    public List<User> getAllUsers() {

        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        List<User> users = session.createQuery(query).getResultList();
        session.close();
        logger.info(users);
        return users;
    }

    /**
     * inserts a user
     * @param user user to be inserted
     * @return id of new user
     */
    public int createUser(User user) {

        int id = 0;
        Session session = sessionFactory.openSession();
        CriteriaBuilder build = session.getCriteriaBuilder();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(user);
        transaction.commit();
        session.close();
        return id;
    }
}
