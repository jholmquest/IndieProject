package bugnet.controller;

import bugnet.entity.Role;
import bugnet.entity.User;
import bugnet.persistence.Database;
import bugnet.persistence.GenericDao;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddUserTest {

    private static final AddUser add = new AddUser();
    private static final GenericDao<User> userDao = new GenericDao<>(User.class);
    /**
     * cleans db, makes a new dao
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("clean.sql");
    }

    @Test
    public void uniqueUsernameTest() {
        assertTrue(add.uniqueUsername("afeaeafefewafaweawef"));
    }

    @Test
    public void repeatUsernameTest() {
        assertFalse(add.uniqueUsername("testuser"));
    }

    @Test
    public void repeatUserTest() {
        User repeatUser = new User("testuser", "pasword");
        int id = add.add(repeatUser);
        List<User> users = userDao.getAll();

        assertEquals(3, users.size());
        assertEquals(0, id);
    }

    @Test
    public void newUserTest() {
        User newUser = new User("uniqueUser", "pasword");
        int id = add.add(newUser);
        newUser.setId(id);
        User insertedUser = userDao.getById(id);
        assertNotNull(insertedUser);

        GenericDao<Role> roleDao = new GenericDao<>(Role.class);
        List<Role> newRole = roleDao.findByPropertyEqual("username", newUser.getUsername());
        assertEquals(1, newRole.size());
    }
}
