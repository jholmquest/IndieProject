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
        users = dao.getAll();
        assertEquals(-1, id);
        assertEquals(3, users.size());
    }

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
