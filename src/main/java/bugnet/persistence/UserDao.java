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
        User user = session.createQuery(query).getSingleResult();
        session.close();

        logger.debug("Result of search " + user);
        return user;
    }

    /**
     * inserts a user
     *
     * @param user user to be inserted
     * @return id of new user
     */
    public int createUser(User user) {
        logger.debug("inserting user " + user);

        int id = 0;
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        Transaction transaction = session.beginTransaction();
        id = (int) session.save(user);
        transaction.commit();
        session.close();

        logger.debug("inserted user with id " + id);
        return id;
    }

    /**
     * updates a user in the db with new values
     *
     * @param user new user info
     */
    public void updateUser(User user) {
        logger.debug("updating user " + user);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
        session.close();
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
