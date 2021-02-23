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
public class UserDao implements BaseDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Gets all users
     *
     * @return list of all users
     */
    public List<User> getAllUsers() {
        logger.debug("getting all users");

        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        List<User> users = session.createQuery(query).getResultList();
        session.close();
        logger.debug("list of users " + users);
        return users;
    }

    /**
     * Searches for a user with a specific username
     *
     * @param username username being searched for
     * @return user info
     */
    public User getUserByUsername(String username) {
        logger.debug("Searching for user with username " + username);

        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        Expression<String> propertyPath = root.get("username");
        query.where(builder.equal(propertyPath, username));
        User user = session.createQuery(query).uniqueResult();
        session.close();

        logger.debug("Result of search " + user);
        return user;
    }

    /**
     * deletes a user
     *
     * @param user user to be deleted
     */
    public void delete(User user) {
        logger.debug("deleting user " + user);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }
}
