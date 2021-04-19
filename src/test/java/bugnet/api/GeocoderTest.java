package bugnet.api;
import bugnet.controller.LocationBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import bugnet.persistence.PropertyLoader;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.Properties;

public class GeocoderTest {

    private static final LocationBuilder builder = new LocationBuilder();

    @Test
    public void testBuilder() {
        List<ResultsItem> results = builder.getCoordinates("Madison, WI");
        double expectedLatitude = 43.0730517;
        double expectedLongitude = -89.4012302;
        assertEquals(expectedLatitude, results.get(0).getGeometry().getLocation().getLat());
        assertEquals(expectedLongitude, results.get(0).getGeometry().getLocation().getLng());
    }

    @Test
    public void testVoid() {
        List<ResultsItem> results = builder.getCoordinates("feagaega;eaveeofeowa");
        assertEquals(0, results.size());
    }

    @Test
    public void testEscape() {
        String escape = builder.escapeLocation("A B %");
        assertEquals("A%20B%20%25", escape);
    }
}
