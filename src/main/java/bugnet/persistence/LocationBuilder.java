package bugnet.persistence;

import bugnet.api.Geocode;
import bugnet.api.ResultsItem;
import bugnet.persistence.PropertyLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.Properties;

public class LocationBuilder {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final PropertyLoader loader = new PropertyLoader();
    private final Properties properties = loader.loadProperties("/api.properties");
    private List<ResultsItem> results;

    public LocationBuilder() {
        results = new ArrayList<>();
    }

    /**
     *
     * @param locationName Name of location being escaped
     * @return string with proper escape characters for a url
     */
    public String escapeLocation(String locationName) {
        String formattedLocation = locationName;
        formattedLocation = formattedLocation.replace("%", "%25");
        formattedLocation = formattedLocation.replace(" ", "%20");
        return formattedLocation;
    }

    /**
     * Takes a location and searches for it using the Geocode api
     * @param locationName where an insect was collected
     */
    public void findCoordinates(String locationName) {

        Client client = ClientBuilder.newClient();

        try {
            WebTarget target = client.target(
                    properties.getProperty("request.url") +
                            escapeLocation(locationName) +
                            properties.getProperty("api.key"));
            String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
            ObjectMapper mapper = new ObjectMapper();
            Geocode queryResults = mapper.readValue(response, Geocode.class);

            results = queryResults.getResults();

        } catch(Exception e) {
            logger.error(e);
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }

    public List<ResultsItem> getResults() {
        return results;
    }

    public double getLatitude() {
        return results.get(0).getGeometry().getLocation().getLat();
    }

    public double getLongitude() {
        return results.get(0).getGeometry().getLocation().getLng();
    }
}
