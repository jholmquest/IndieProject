package bugnet.persistence;

import bugnet.entity.Specimen;
import bugnet.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
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
        database.runSQL("cleanSpecimen.sql");
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
        User newUser = new User("createdUser", "eaohgeago");
        int newId = dao.create(newUser);
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
        dao.saveOrUpdate(userToUpdate);
        User updatedUser = dao.getUserByUsername("holmquest");
        assertTrue(updatedUser.equals(userToUpdate));
    }

    /**
     * Verifies inserting user and specimen simulatenously works
     */
    @Test
    void createUserWithSpecimenTest() {
        User newUser = new User("createdUser", "eaohgeago");
        Specimen newSpecimen = new Specimen("testbug", "here", LocalDate.now(), "hello world", newUser);
        newUser.addSpecimen(newSpecimen);
        dao.create(newUser);
        User insertedUser = dao.getUserByUsername("createdUser");
        assertNotNull(insertedUser);
        assertTrue(insertedUser.equals(newUser));
    }
}
