package bugnet.controller;

import bugnet.api.Geocode;
import bugnet.persistence.PropertyLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class LocationBuilder {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final PropertyLoader loader = new PropertyLoader();
    private final Properties properties = loader.loadProperties("/api.properties");

    // need to replace certain characters for the api
    public String escapeLocation(String locationName) {
        String formattedLocation = locationName;
        formattedLocation = formattedLocation.replace("%", "%25");
        formattedLocation = formattedLocation.replace(" ", "%20");
        return formattedLocation;
    }

    public Map<LocationKey, String> getCoordinates(String locationName) {

        Client client = ClientBuilder.newClient();
        Map<LocationKey, String> map = new HashMap<>();

        try {
            WebTarget target = client.target(
                    properties.getProperty("request.url") +
                            escapeLocation(locationName) +
                            properties.getProperty("api.key"));
            String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
            ObjectMapper mapper = new ObjectMapper();
            Geocode results = mapper.readValue(response, Geocode.class);

            String found = results.getStatus();
            String latitude = String.valueOf(results.getResults().get(0).getGeometry().getLocation().getLat());
            String longitude = String.valueOf(results.getResults().get(0).getGeometry().getLocation().getLng());
            map.put(LocationKey.FOUND, found);
            map.put(LocationKey.LATITUDE, latitude);
            map.put(LocationKey.LONGITUDE, longitude);

        } catch(Exception e) {
            logger.error(e);
        } finally {
            if (client != null) {
                client.close();
            }
        }

        return map;
    }
}
