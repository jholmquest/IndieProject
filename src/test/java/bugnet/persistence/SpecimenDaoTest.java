package bugnet.persistence;

import bugnet.entity.User;
import bugnet.entity.Specimen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the methods in SpecimenDao
 */
public class SpecimenDaoTest {

    GenericDao<Specimen> dao;

    /**
     * cleans db, makes a new dao
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao<>(Specimen.class);

        Database database = Database.getInstance();
        database.runSQL("cleanUser.sql");
        database.runSQL("cleanSpecimen.sql");
    }

    /**
     * verifies successful get all specimens
     */
    @Test
    void getAllSuccess() {
        List<Specimen> specimens = dao.getAll();
        assertEquals(3, specimens.size());
    }

    /**
     * verifies cascading works by deleting a user
     */
    @Test
    void cascadeTest() {
        GenericDao<User> userDao = new GenericDao<>(User.class);
        User user = userDao.getById(3);
        userDao.delete(user);
        assertNull(dao.getById(1));
    }

    /**
     * verifies inserting works
     */
    @Test
    void insertTest() {
        UserDao userDao = new UserDao();
        User user = userDao.getUserByUsername("testuser");
        LocalDate date = LocalDate.now();
        Specimen newSpecimen = new Specimen("testbug", "here", date, "hello world", user);
        user.addSpecimen(newSpecimen);
        int id = dao.insert(newSpecimen);
        Specimen createdSpecimen = dao.getById(id);
        assertTrue(createdSpecimen.equals(newSpecimen));
    }

    /**
     * verifies selecting by id works
     */
    @Test
    void getByIdTest() {
        Specimen specimen = dao.getById(1);
        assertEquals("beetle", specimen.getBugName());
    }

    /**
     * verifies deleting specimen working
     */
    @Test
    void deleteTest() {
        Specimen specimen = dao.getById(1);
        dao.delete(specimen);
        Specimen deletedSpecimen = dao.getById(1);
        assertNull(deletedSpecimen);
    }

    /**
     * verifies updating specimen working
     */
    @Test
    void updateTest() {
        Specimen specimenToUpdate = dao.getById(1);
        specimenToUpdate.setBugName("updated beetle");
        dao.saveOrUpdate(specimenToUpdate);
        Specimen updatedSpecimen = dao.getById(1);
        assertTrue(updatedSpecimen.equals(specimenToUpdate));
    }
}
