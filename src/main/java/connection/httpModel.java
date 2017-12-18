package connection;

import helpers.Constants.COUNTRY_CODE;
import helpers.Constants.UNIT;

import java.io.IOException;

public interface httpModel {
    void makeHttpUrlConnection(String url) throws IOException;
    String createCurrentWeatherApiURL(String city, COUNTRY_CODE countryCode, UNIT unit);
    String createForecastApiURL(String city, COUNTRY_CODE countryCode, UNIT unit);
    String getJsonData();
}
