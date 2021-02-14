package bugnet.persistence;

import bugnet.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class UserDaoTest {

    UserDao dao;

    /**
     * cleans db, makes a new dao
     */
    @BeforeEach
    void setUp() {
        dao = new UserDao();

        Database database = Database.getInstance();
        database.runSQL("cleanUser.sql");
    }

    /**
     * verifies successful get all users
     */
    @Test
    void getAllSuccess() {
        List<User> users = dao.getAllUsers();
        assertEquals(3, users.size());
    }

    /**
     * verifies successful get of a single user by username;
     * important for login
     */
    @Test
    void getByUsernameTest() {
        User testUser = dao.getUserByUsername("holmquest");
        assertEquals(3, testUser.getId());
    }

    /**
     * verifies creation of a user
     */
    @Test
    void createUserTest() {
        User newUser = new User(4, "createdUser", "eaohgeago");
        int newId = dao.createUser(newUser);
        assertEquals(newUser.getId(), newId);
        List<User> users = dao.getAllUsers();
        assertEquals(4, users.size());
    }

    /**
     * verifies deletion of a user
     */
    @Test
    void deleteUserTest() {
        User deletedUser = dao.getUserByUsername("holmquest");
        dao.delete(deletedUser);
        assertNull(dao.getUserByUsername("holmquest"));
        List<User> users = dao.getAllUsers();
        assertEquals(2, users.size());
    }

    /**
     * verifies update of user info
     */
    @Test
    void updateUserTest() {
        User userToUpdate = dao.getUserByUsername("holmquest");
        userToUpdate.setPassword("updated");
        dao.updateUser(userToUpdate);
        User updatedUser = dao.getUserByUsername("holmquest");
        assertEquals("updated", updatedUser.getPassword());
    }
}
