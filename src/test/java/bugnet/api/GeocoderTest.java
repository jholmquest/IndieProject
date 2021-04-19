package bugnet.api;

import bugnet.controller.LocationBuilder;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

/**
 * Tests Classes related to Geocoding
 */
public class GeocoderTest {

    private static final LocationBuilder builder = new LocationBuilder();

    // Tests finding a known location
    @Test
    public void testBuilder() {
        List<ResultsItem> results = builder.getCoordinates("Madison, WI");
        double expectedLatitude = 43.0730517;
        double expectedLongitude = -89.4012302;
        assertEquals(expectedLatitude, results.get(0).getGeometry().getLocation().getLat());
        assertEquals(expectedLongitude, results.get(0).getGeometry().getLocation().getLng());
    }

    // Tests finding a nonexistant location
    @Test
    public void testVoid() {
        List<ResultsItem> results = builder.getCoordinates("feagaega;eaveeofeowa");
        assertEquals(0, results.size());
    }

    // Tests escaping characters properly
    @Test
    public void testEscape() {
        String escape = builder.escapeLocation("A B %");
        assertEquals("A%20B%20%25", escape);
    }
}
