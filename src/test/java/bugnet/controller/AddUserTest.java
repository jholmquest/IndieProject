package bugnet.controller;

import bugnet.entity.Role;
import bugnet.entity.User;
import bugnet.persistence.AddUser;
import bugnet.util.Column;
import bugnet.persistence.Database;
import bugnet.persistence.GenericDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * tests methods that control adding users
 *
 * @author James Holmquest
 */
public class AddUserTest {

    private static final AddUser add = new AddUser();
    GenericDao<User> dao;
    List<User> users;

    /**
     * cleans db, makes a new dao
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao<>(User.class);

        Database database = Database.getInstance();
        database.runSQL("clean.sql");
    }

    /**
     * tests if a unique username is properly detected
     */
    @Test
    public void uniqueUsernameTest() {
        assertTrue(add.uniqueUsername("afeaeafefewafaweawef"));
    }

    /**
     * tests if a repeat username is properly detected
     */
    @Test
    public void repeatUsernameTest() {
        assertFalse(add.uniqueUsername("testuser"));
    }

    /**
     * tests that an id of -1 is returned when attempting to add a duplicate user
     */
    @Test
    public void repeatUserTest() {
        User repeatUser = new User("testuser", "pasword");
        int id = add.add(repeatUser);
        users = dao.getAll();
        assertEquals(-1, id);
        assertEquals(3, users.size());
    }

    /**
     * tests creating a user by passing in a username and password
     */
    @Test
    public void newUserTest() {
        User newUser = new User("uniqueUser", "pasword");
        int id = add.add(newUser);
        newUser.setId(id);
        User insertedUser = dao.getById(id);
        assertEquals(newUser, insertedUser);

        GenericDao<Role> roleDao = new GenericDao<>(Role.class);
        List<Role> newRole = roleDao.findByPropertyEqual(Column.USERNAME, newUser.getUsername());
        assertEquals(1, newRole.size());
    }
}
