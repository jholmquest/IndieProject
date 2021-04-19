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

    private final PropertyLoader loader = new PropertyLoader();
    private final Properties properties = loader.loadProperties("/api.properties");

    @Test
    public void makeRequest() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(properties.getProperty("request.url") + "Madison,%20WI" +
                properties.getProperty("api.key"));
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        Geocode results = mapper.readValue(response, Geocode.class);

        double expectedLatitude = 43.0730517;
        double expectedLongitude = -89.4012302;
        assertEquals(expectedLatitude, results.getResults().get(0).getGeometry().getLocation().getLat());
        assertEquals(expectedLongitude, results.getResults().get(0).getGeometry().getLocation().getLng());
    }

    @Test
    public void testBuilder() {
        LocationBuilder builder = new LocationBuilder();
        List<ResultsItem> results = builder.getCoordinates("Madison, WI");
        double expectedLatitude = 43.0730517;
        double expectedLongitude = -89.4012302;
        assertEquals(expectedLatitude, results.get(0).getGeometry().getLocation().getLat());
        assertEquals(expectedLongitude, results.get(0).getGeometry().getLocation().getLng());
    }

    @Test
    public void testVoid() {
        LocationBuilder builder = new LocationBuilder();
        List<ResultsItem> results = builder.getCoordinates("feagaega;eaveeofeowa");
        assertEquals(0, results.size());
    }


}
