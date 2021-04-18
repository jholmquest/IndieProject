package bugnet.controller;

import java.util.HashMap;
import java.util.Map;

public class LocationBuilder {

    // need to replace certain characters for the api
    public String escapeLocation(String locationName) {
        String formattedLocation = locationName;
        formattedLocation = formattedLocation.replace("%", "%25");
        formattedLocation = formattedLocation.replace(" ", "%20");
        return formattedLocation;
    }

    public Map<LocationKey, String> getCoordinates(String locationName) {
        LocationKey key = LocationKey.FOUND;
        String value = "value";

        Map<LocationKey, String> map = new HashMap<>();
        map.put(key, value);
        return map;
    }
}
