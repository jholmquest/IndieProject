package bugnet.persistence;

import bugnet.entity.User;
import bugnet.entity.Specimen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        List<Specimen> specimens = dao.getAllSpecimens();
        assertEquals(1, specimens.size());
    }
}
