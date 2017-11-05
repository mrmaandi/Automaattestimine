import connection.HTTPConnection;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static helpers.Constants.COUNTRY_CODE.EE;
import static helpers.Constants.UNIT.metric;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class httpConnectionTest {
    private final int HTTP_STATUS_OK_CODE = 200;
    private HTTPConnection connection;

    @Before
    public void setupTests(){

    }

    @Test
    public void httpConnectionToForecastAPITest() {
        connection = new HTTPConnection();
        //Test for response (HTML 200OK etc, we want 200).
        String requestUrl = connection.createForecastApiURL
                ("Tallinn", EE, metric);
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
        connection = new HTTPConnection();
        //Test for response (HTML 200OK etc, we want 200).
        String requestUrl = connection.createCurrentWeatherApiURL
                ("Tallinn", EE, metric);
        try {
            connection.makeHttpUrlConnection(requestUrl);
            int responseCode = connection.getResponseCode();
            assertEquals(responseCode, HTTP_STATUS_OK_CODE);
        } catch (IOException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void httpConnectionMockTest(){
        connection = mock(HTTPConnection.class);
        try {
            connection.makeHttpUrlConnection(connection.createCurrentWeatherApiURL
                    ("Tallinn", EE, metric));

            verify(connection).makeHttpUrlConnection(connection.createCurrentWeatherApiURL
                    ("Tallinn", EE, metric));
        } catch (IOException e) {
            fail("Couldn't connect.");
        }
    }

}
