package bugnet.persistence;

import bugnet.entity.Role;
import bugnet.entity.Specimen;
import bugnet.entity.User;
import bugnet.util.Column;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.*;

public class UserDaoTest {

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
     * verifies successful get all users
     */
    @Test
    void getAllSuccess() {
        List<User> users = dao.getAll();
        assertEquals(3, users.size());
    }

    @Test
    void lombokTest() {
        User user = new User();
        user.setId(10);
    }
    /**
     * verifies successful get of a single user by username;
     * important for login
     */
    @Test
    void getByUsernameTest() {
        users = dao.findByPropertyEqual(Column.USERNAME, "holmquest");
        assertEquals(3, users.get(0).getId());
    }

    /**
     * verifies creation of a user
     */
    @Test
    void createUserTest() {
        User newUser = new User("createdUser", "eaohgeago");
        int newId = dao.insert(newUser);
        assertEquals(newUser.getId(), newId);
        List<User> users = dao.getAll();
        assertEquals(4, users.size());
    }

    /**
     * verifies deletion of a user
     */
    @Test
    void deleteUserTest() {
        User deletedUser = dao.getById(3);
        dao.delete(deletedUser);
        assertNull(dao.getById(3));
        List<User> users = dao.getAll();
        assertEquals(2, users.size());
    }

    /**
     * verifies update of user info
     */
    @Test
    void updateUserTest() {
        User userToUpdate = dao.getById(3);
        userToUpdate.setPassword("updated");
        dao.saveOrUpdate(userToUpdate);
        User updatedUser = dao.getById(3);
        assertEquals(updatedUser, userToUpdate);
    }

    /**
     * Verifies inserting user and specimen simulatenously works
     */
    @Test
    void createUserWithSpecimenTest() {
        User newUser = new User("createdUser", "eaohgeago");
        Specimen newSpecimen = new Specimen("testbug", "here", LocalDate.now(), "hello world", newUser);
        newUser.addSpecimen(newSpecimen);
        int id = dao.insert(newUser);
        User insertedUser = dao.getById(id);
        assertNotNull(insertedUser);
        assertEquals(insertedUser, newUser);
    }

    /**
     * tests getting roles for a user
     */
    @Test
    void getRoleTest() {
        User roleUser = dao.getById(3);
        Set<Role> userRoles = roleUser.getRoles();
        GenericDao<Role> roleDao = new GenericDao<>(Role.class);
        Role role1 = roleDao.getById(1);
        Role role2 = roleDao.getById(3);

        assertTrue(userRoles.contains(role1));
        assertTrue(userRoles.contains(role2));
    }

    /**
     * tests if roles are deleted upon deleting associated user
     */
    @Test
    void deleteRoleCascadeTest() {

        User toDelete = dao.getById(3);
        dao.delete(toDelete);

        GenericDao<Role> roleDao = new GenericDao<>(Role.class);
        Role role1 = roleDao.getById(1);
        Role role2 = roleDao.getById(3);

        assertNull(role1);
        assertNull(role2);
    }
}
