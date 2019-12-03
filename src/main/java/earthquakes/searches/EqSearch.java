package earthquakes.searches;

public class EqSearch {
    private int distance;
    private int minmag;
    private int lat;
    private int lon;
    private String location;

    public EqSearch() {

    }

    public int getDistance() {
        return this.distance;
    }

    public int getMinmag() {
        return this.minmag;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setMinmag(int minmag) {
        this.minmag = minmag;
    }

    public int getLatitude() {
        return this.lat;
    }

    public int getLongitude() {
        return this.lon;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLatitude(int lat) {
        this.lat = lat;
    }

    public void setLongitude(int lon) {
        this.lon = lon;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}