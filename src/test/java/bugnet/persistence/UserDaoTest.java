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

    }

    @Test
    void getAllSuccess() {
        List<User> users = dao.getAllUsers();
        assertEquals(2, users.size());
    }
}
