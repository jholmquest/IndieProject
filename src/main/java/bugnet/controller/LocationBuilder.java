package bugnet.controller;

public class LocationBuilder {

    private String locationName;
    public LocationBuilder() {

    }

    public LocationBuilder(String locationName) {
        this.locationName = escapeLocation(locationName);
    }

    public String escapeLocation(String locationName) {
        return locationName.replace(" ", "%20");
    }


}
