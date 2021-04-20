package bugnet.controller;

import bugnet.entity.User;
import bugnet.persistence.Database;
import bugnet.persistence.GenericDao;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class AddUserTest {

    private static final AddUser add = new AddUser();
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
}
