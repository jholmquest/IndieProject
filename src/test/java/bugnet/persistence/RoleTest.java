package bugnet.persistence;

import bugnet.entity.Role;
import bugnet.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.*;

public class RoleTest {

    GenericDao<Role> dao;
    List<Role> roles;
    /**
     * cleans db, makes a new dao
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao<>(Role.class);

        Database database = Database.getInstance();
        database.runSQL("clean.sql");
    }

    @Test
    void getAllTest() {
        roles = dao.getAll();
        assertEquals(3, roles.size());
    }
}
