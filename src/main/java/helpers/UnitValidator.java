package helpers;

public class UnitValidator {
    private double minTemp = -70;
    private double maxTemp = 70;
    private double minLat = -90;
    private double maxLat = 90;
    private double minLong = -180;
    private double maxLong = 180;

    //We validate the unit measures for temperature.
    public void validateTemperature(Double requestTemp) throws Exception {
        if (requestTemp == null) {
            throw new Exception("Temperature value is null");
        }
        if (minTemp > requestTemp || requestTemp > maxTemp) {
            throw new Exception("Temperature is not between values " + minTemp + " and " + maxTemp + ".");
        }
    }

    //We validate the unit measures for GPS.
    public void validateGeoLocation(Double latitude, Double longitude) throws Exception {
        if (latitude == null || longitude == null) {
            throw new Exception("Latitude and/or longitude are null.");
        }
        if (minLat > latitude || latitude > maxLat) {
            throw new Exception("Latitude is not between " + minLat + " and " + maxLat + ".");
        }
        if (minLong > longitude || longitude > maxLong) {
            throw new Exception("Longitude is not between " + minLong + " and " + maxLong + ".");
        }
    }

    //We validate the date.
    public void validateCurrentDate(String date) throws Exception {
        if (date == null) {
            throw new Exception("No date specified.");
        }
    }

}
