package bugnet.persistence;

import bugnet.entity.Role;
import bugnet.entity.Specimen;
import bugnet.entity.User;
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

    /**
     * verifies successful get of a single user by username;
     * important for login
     */
    @Test
    void getByUsernameTest() {
        users = dao.findByPropertyEqual("username", "holmquest");
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

    // Tests getting roles for a user
    @Test
    void getRoleTest() {
        User user = dao.getById(3);
    }

    // Tests if deleting a role successfully removes it from user's list of roles without deleting them
    @Test
    void deleteRoleCascadeTest() {
        GenericDao<Role> roleDao = new GenericDao<>(Role.class);
        Role role = roleDao.getById(1);
        roleDao.delete(role);
        User user = dao.getById(3);
    }
}
