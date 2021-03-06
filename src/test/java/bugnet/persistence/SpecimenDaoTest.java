package bugnet.persistence;

import bugnet.entity.User;
import bugnet.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * tests modifying specimens via hibernate
 *
 * @author James Holmquest
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
        database.runSQL("clean.sql");
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
        GenericDao<User> userDao = new GenericDao<>(User.class);
        User user = userDao.getById(1);
        LocalDate date = LocalDate.now();
        Specimen newSpecimen = new Specimen("testbug", "here", date, "hello world", user);
        user.addSpecimen(newSpecimen);
        int id = dao.insert(newSpecimen);
        Specimen createdSpecimen = dao.getById(id);
        assertEquals(newSpecimen, createdSpecimen);
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
        Specimen specimenToUpdate = dao.getById(2);
        specimenToUpdate.setBugName("updated bug");
        dao.saveOrUpdate(specimenToUpdate);
        Specimen updatedSpecimen = dao.getById(2);
        assertEquals(updatedSpecimen, specimenToUpdate);
    }
}
