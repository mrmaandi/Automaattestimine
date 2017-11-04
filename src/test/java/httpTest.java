import connection.HTTPConnection;
import helpers.Constants;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class httpTest {
    private final int HTTP_STATUS_OK_CODE = 200;
    private HTTPConnection connection;

    @Before
    public void setupTests(){
        connection = new HTTPConnection();
    }

    @Test
    public void httpConnectionToForecastAPITest() {

        //Test for response (HTML 200OK etc, we want 200).
        String requestUrl = connection.createForecastApiURL("Tallinn", Constants.COUNTRY_CODE.EE, Constants.UNIT.metric);
        try {
            connection.makeHttpUrlConnection(requestUrl);
            int responseCode = connection.getResponseCode();
            assertEquals(responseCode, HTTP_STATUS_OK_CODE);
        } catch (IOException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void httpConnectionToCurrentWeatherAPITest() {

        //Test for response (HTML 200OK etc, we want 200).
        String requestUrl = connection.createCurrentWeatherApiURL("Tallinn", Constants.COUNTRY_CODE.EE, Constants.UNIT.metric);
        try {
            connection.makeHttpUrlConnection(requestUrl);
            int responseCode = connection.getResponseCode();
            assertEquals(responseCode, HTTP_STATUS_OK_CODE);
        } catch (IOException e) {
            fail(e.getLocalizedMessage());
        }
    }


}
