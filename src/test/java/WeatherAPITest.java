import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherAPITest {
    private static final int HTTP_STATUS_OK_CODE = 200;
    private static HTTPConnection connection;

    @BeforeClass
    public static void setupTests(){
        connection = new HTTPConnection();
    }

    @Test
    public static void httpConnectionCorrectLinkTest(){

    }

    @Test
    public static void httpConnectionToCurrentWeatherAPITest() {
        //Test for response (HTML 200OK etc, we want 200).
        try {
            int responseCode = connection.getResponseCode();
            assertEquals(responseCode, HTTP_STATUS_OK_CODE);
        } catch (Exception e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public static void httpConnectionToForecastAPITest() {

        //Test for response (HTML 200OK etc, we want 200).
        String requestUrl = connection.createForecastApiURL();
        try {
            connection.makeHttpUrlConnection(requestUrl);
            int responseCode = connection.getResponseCode();
            assertEquals(responseCode, HTTP_STATUS_OK_CODE);
        } catch (IOException e) {
            fail(e.getLocalizedMessage());
        }
    }


}
