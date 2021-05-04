package bugnet.persistence;

import bugnet.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Tests for modifying the role table
 */
public class RoleTest {

    GenericDao<Role> dao;
    GenericDao<User> userDao;
    List<Role> roles;
    /**
     * cleans db, makes a new dao
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao<>(Role.class);
        userDao = new GenericDao<>(User.class);
        Database database = Database.getInstance();
        database.runSQL("clean.sql");
    }

    /**
     * tests getting all roles
     */
    @Test
    void getAllTest() {
        roles = dao.getAll();
        assertEquals(3, roles.size());
    }

    /**
     * tests adding a role for a user
     */
    @Test
    void insertTest() {
        User user = userDao.getById(2);
        Role newRole = new Role("Test Role", "admin", user);
        int id = dao.insert(newRole);
        newRole.setId(id);
        Role addedRole = dao.getById(id);
        assertEquals(newRole, addedRole);
    }

    /**
     * test for deleting a role
     */
    @Test
    void deleteTest() {
        Role toDelete = dao.getById(3);
        dao.delete(toDelete);
        Role deletedRole = dao.getById(3);
        assertNull(deletedRole);

        User user = userDao.getById(3);
        assertFalse(user.getRoles().contains(toDelete));
    }

    /**
     * test for updating a role
     */
    @Test
    void updateTest() {
        Role toUpdate = dao.getById(3);
        toUpdate.setRoleName("fake role");
        dao.saveOrUpdate(toUpdate);
        Role updatedRole = dao.getById(3);
        assertEquals(toUpdate, updatedRole);
    }
}
