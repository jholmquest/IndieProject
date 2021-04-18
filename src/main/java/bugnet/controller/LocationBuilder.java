package bugnet.controller;

public class LocationBuilder {

    private String locationName;
    public LocationBuilder() {

    }

    public LocationBuilder(String locationName) {
        this.locationName = escapeLocation(locationName);
    }

    // need to replace certain characters for the api
    public String escapeLocation(String locationName) {
        String formattedLocation = locationName;
        formattedLocation = formattedLocation.replace("%", "%25");
        formattedLocation = formattedLocation.replace(" ", "%20");
        return formattedLocation;
    }


}
