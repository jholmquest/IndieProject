package bugnet.persistence;

import bugnet.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class UserDaoTest {

    UserDao dao;

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

    @Test
    void getByUsernameTest() {
        User testUser = dao.getUserByUsername("holmquest");
        assertEquals(3, testUser.getId());
    }

    @Test
    void createUserTest() {
        User newUser = new User(4, "createdUser", "eaohgeago");
        int newId = dao.createUser(newUser);
        assertEquals(newUser.getId(), newId);
    }

    @Test
    void deleteUserTest() {
        User deletedUser = dao.getUserByUsername("holmquest");
        dao.delete(deletedUser);
        List<User> users = dao.getAllUsers();
        assertEquals(2, users.size());
    }

    @Test
    void updateUserTest() {
        User userToUpdate = dao.getUserByUsername("holmquest");
        userToUpdate.setPassword("updated");
        dao.updateUser(userToUpdate);
        User updatedUser = dao.getUserByUsername("holmquest");
        assertEquals("updated", updatedUser.getPassword());
    }
}
