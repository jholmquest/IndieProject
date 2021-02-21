package bugnet.persistence;

import bugnet.entity.User;
import bugnet.entity.Specimen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Tests the methods in SpecimenDao
 */
public class SpecimenDaoTest {

    SpecimenDao dao;

    /**
     * cleans db, makes a new dao
     */
    @BeforeEach
    void setUp() {
        dao = new SpecimenDao();

        Database database = Database.getInstance();
        database.runSQL("cleanUser.sql");
        database.runSQL("cleanSpecimen.sql");
    }

    /**
     * verifies successful get all specimens
     */
    @Test
    void getAllSuccess() {
        List<Specimen> specimens = dao.getAllSpecimens();
        assertEquals(3, specimens.size());
    }

    /**
     * verifies cascading works by deleting a user
     */
    @Test
    void cascadeTest() {
        UserDao userDao = new UserDao();
        User user = userDao.getUserByUsername("holmquest");
        userDao.delete(user);
        assertNull(dao.getSpecimenById(1));
    }

    /**
     * verifies inserting works
     */
    @Test
    void insertTest() {
        UserDao userDao = new UserDao();
        User user = userDao.getUserByUsername("testuser");
        Specimen newSpecimen = new Specimen("testbug", "here", new Date(), "hello world", user);
        user.addSpecimen(newSpecimen);
        int id = dao.createSpecimen(newSpecimen);
        Specimen createdSpecimen = dao.getSpecimenById(id);
        assertEquals(newSpecimen.getBugName(), createdSpecimen.getBugName());
    }

    /**
     * verifies selecting by id works
     */
    @Test
    void getByIdTest() {
        Specimen specimen = dao.getSpecimenById(1);
        assertEquals("beetle", specimen.getBugName());
    }

    /**
     * verifies deleting specimen working
     */
    @Test
    void deleteTest() {
        Specimen specimen = dao.getSpecimenById(1);
        dao.delete(specimen);
        Specimen deletedSpecimen = dao.getSpecimenById(1);
        assertNull(deletedSpecimen);
    }

    /**
     * verifies updating specimen working
     */
    @Test
    void updateTest() {
        Specimen specimenToUpdate = dao.getSpecimenById(1);
        specimenToUpdate.setBugName("updated beetle");
        dao.updateSpecimen(specimenToUpdate);
        Specimen updatedSpecimen = dao.getSpecimenById(1);
        assertEquals(updatedSpecimen.getBugName(), specimenToUpdate.getBugName());
    }
}
