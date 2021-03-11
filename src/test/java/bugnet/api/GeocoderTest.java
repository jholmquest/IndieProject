package bugnet.api;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import bugnet.persistence.PropertyLoader;
import org.junit.jupiter.api.BeforeEach;

import java.util.Properties;
import java.util.Set;

public class GeocoderTest {

    private final PropertyLoader loader = new PropertyLoader();
    private final Properties properties = loader.loadProperties("/api.properties");

    @Test
    public void makeRequest() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target(properties.getProperty("request.url") + "address=Madison,WI&" +
                        properties.getProperty("api.key"));
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        assertEquals("???", response);
    }

    @Test
    public void showPropertyNames() throws Exception {
        Set<String> propertyNames = properties.stringPropertyNames();
        assertEquals("???", propertyNames);
    }
}
