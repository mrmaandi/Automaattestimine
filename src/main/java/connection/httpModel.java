package connection;

import helpers.Constants;

import java.io.IOException;

public interface httpModel {
    public void makeHttpUrlConnection(String url) throws IOException;
    public String createCurrentWeatherApiURL(String city, Constants.COUNTRY_CODE countryCode, Constants.UNIT unit);
    public String createForecastApiURL(String city, Constants.COUNTRY_CODE countryCode, Constants.UNIT unit);
}
