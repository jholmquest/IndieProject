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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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

    /*
    public String escapeLocation(String locationName) {
        String formattedLocation = locationName;
        formattedLocation = formattedLocation.replace("%", "%25");
        formattedLocation = formattedLocation.replace(" ", "%20");
        return formattedLocation;
    }

    I found out there's a class that already does what I want
*/
    /**
     * Takes a location and searches for it using the Geocode api
     * @param locationName where an insect was collected
     */
    public void findCoordinates(String locationName) {

        Client client = ClientBuilder.newClient();

        try {
            WebTarget target = client.target(
                    properties.getProperty("request.url") +
                            URLEncoder.encode(locationName, StandardCharsets.UTF_8.toString()) +
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

    /**
     * gets the results from the api
     * @return list of results
     */
    public List<ResultsItem> getResults() {
        return results;
    }

    /**
     * gets latitude found
     * @return latitude
     */
    public double getLatitude() {
        return results.get(0).getGeometry().getLocation().getLat();
    }

    /**
     * gets longitude found
     * @return longitude
     */
    public double getLongitude() {
        return results.get(0).getGeometry().getLocation().getLng();
    }
}
