package bugnet.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class InputControllerTest implements InputController{

    @Test
    void emptyStringTest() {
        Double nullDouble = setCoordinate("");
        assertNull(nullDouble);
    }

    /**
     * tests if a negative number is parsed correctly
     */
    @Test
    void negativeStringTest() {
        Double negativeDouble = setCoordinate("-10.1");
        // assertEquals requires primitive while class uses wrapper so null values can be added
        assertEquals(-10.1, negativeDouble.doubleValue());
    }

    @Test
    void positiveStringTest() {
        Double positiveDouble = setCoordinate("+10.1");
        assertEquals(10.1, positiveDouble.doubleValue());
    }

    @Test
    void idZeroTest() {
        String message = checkId(0);
        assertEquals(UserFeedback.INSERT_FAILURE.getMessage(), message);
    }

    @Test
    void idValidTest() {
        int testId = 1;
        String testMessage = checkId(testId);
        String expectedMessage = UserFeedback.INSERT_SUCCESS.getMessage() + testId; // formatting
        assertEquals(expectedMessage, testMessage);
    }
}
