package bugnet.api;

import bugnet.persistence.LocationBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

/**
 * Tests Classes related to Geocoding
 */
public class GeocoderTest {

    private LocationBuilder builder;

    @BeforeEach
    void setup() {
        builder = new LocationBuilder();
    }

    /**
     * finding a known location
     */
    @Test
    public void testBuilder() {
        builder.findCoordinates("Madison, WI");
        double expectedLatitude = 43.0730517;
        double expectedLongitude = -89.4012302;
        assertEquals(expectedLatitude, builder.getLatitude());
        assertEquals(expectedLongitude, builder.getLongitude());
    }

    /**
     * not find a location
     */
    @Test
    public void testVoid() {
        builder.findCoordinates("feagaega;eaveeofeowa");
        List<ResultsItem> results = builder.getResults();
        assertEquals(0, results.size());
    }

}
