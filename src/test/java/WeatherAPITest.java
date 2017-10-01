import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherAPITest {
    private final int HTTP_STATUS_OK_CODE = 200;

    @Test
    public void testHttpConnectionToCurrentWeatherAPI() {
        //Test for response (HTML 200OK etc, we want 200).
        String requestUrl = createCurrentWeatherApiURL();
        try {
            HttpURLConnection connection = makeHTTPConnection.makeHttpUrlConnection(requestUrl);
            int responseCode = connection.getResponseCode();

            assertEquals(responseCode, HTTP_STATUS_OK_CODE);
        } catch (IOException exception) {
            fail(exception.getLocalizedMessage());
        }
    }

    private String createCurrentWeatherApiURL() {
        String APILink = "http://api.openweathermap.org/data/2.5/weather?APPID=";
        String APIKey = "1fd2cd75a11b7d7eef55ceb39d47eeb0";
        String city = "Tallinn";
        String countryCode = "EE";
        return APILink + city + ", " + countryCode + "&appid=" + APIKey + "&unit=metric";
    }


    @Test
    public void testHttpConnectionToForecastAPI() {
        //Test for response (HTML 200OK etc, we want 200).
        String requestUrl = createForecastApiURL();
        try {
            HttpURLConnection connection = makeHTTPConnection.makeHttpUrlConnection(requestUrl);
            int responseCode = connection.getResponseCode();

            assertEquals(responseCode, HTTP_STATUS_OK_CODE);
        } catch (IOException exception) {
            fail(exception.getLocalizedMessage());
        }
    }

    private String createForecastApiURL() {
        String APILink = "http://api.openweathermap.org/data/2.5/forecast?APPID=";
        String APIKey = "1fd2cd75a11b7d7eef55ceb39d47eeb0";
        String city = "Tallinn";
        String countryCode = "EE";
        return APILink + city + ", " + countryCode + "&appid=" + APIKey + "&unit=metric";
    }

}
