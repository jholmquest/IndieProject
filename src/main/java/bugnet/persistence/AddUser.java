package bugnet.persistence;

import bugnet.entity.Role;
import bugnet.entity.User;
import bugnet.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Holds the logic necessary for creating a user
 *
 * @author James Holmquest
 */
public class AddUser {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao<User> dao;

    public AddUser() {
        dao = new GenericDao<>(User.class);
    }

    /**
     * checks if a username is unique
     * @param username username being checked
     * @return true if no username found, otherwise false
     */
    public boolean uniqueUsername(String username) {
        List<User> users = dao.findByPropertyEqual("username", username);

        return users.isEmpty();
    }

    /**
     * Attempts to add a user, if successful also triggers method to set their role
     * @param newUser User to be added
     * @return message to be output based on results
     */
    public int add(User newUser) {
        int id = 0;

        if (uniqueUsername(newUser.getUsername())) {
            id = dao.insert(newUser);

            if (id != 0) {
                newUser.setId(id);
                initializeRole(newUser);
            } else {
                logger.error("There was an issue adding the user");
            }

            return id;
        }

        return id;
    }

    /**
     * Gives a new user the initial role of user
     * @param newUser newly created user
     */
    public void initializeRole(User newUser) {

        Role initialRole = new Role("user", newUser.getUsername(), newUser);
        GenericDao<Role> roleDao = new GenericDao<>(Role.class);
        int roleId = roleDao.insert(initialRole);
        initialRole.setId(roleId);
        newUser.addRole(initialRole);
    }
}
