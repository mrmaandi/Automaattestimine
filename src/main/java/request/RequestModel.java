package request;

import connection.HTTPConnection;
import helpers.Constants.COUNTRY_CODE;

import static helpers.Constants.UNIT;

public interface RequestModel {
     void fetchJsonCurrentWeatherString(HTTPConnection connection);
     void fetchJsonForecastWeatherString(HTTPConnection connection);
     String getCity();
     COUNTRY_CODE getCountryCode();
     UNIT getUnit();
     String getJsonData();
}
