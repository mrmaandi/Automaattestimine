package request;

import data.WeatherData;
import helpers.Constants;
import helpers.UnitValidator;
import org.junit.Before;
import org.junit.Test;
import repository.Repository;

import static helpers.Constants.COUNTRY_CODE.EE;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class currentWeatherRequestTest {
    private Request request;
    private Repository repository;
    private UnitValidator validator;

    @Before
    public void setupTests(){
        request = new Request("Tallinn", EE, Constants.UNIT.metric);
        repository = new Repository();
        validator = new UnitValidator();
    }

    @Test
    public void requestCityMatchesResponseTest() throws Exception {
        WeatherData data = repository.getCurrentWeather(request);
        System.out.println(data.getCity() + data.getDate() + data.getHighestTemp() + data.getLowestTemp());
        assertEquals(data.getCity(), request.getCity());
    }

    @Test
    public void coordinatesMatchRequestTest() throws Exception {
        WeatherData data = repository.getCurrentWeather(request);
        try {
            validator.validateGeoLocation(data.getLat(), data.getLon());
        } catch (Exception e) {
            fail("Test failed: " + e.getMessage());
        }
    }

    @Test
    public void temperatureHighestValidTest() throws Exception {
        WeatherData data = repository.getCurrentWeather(request);
        try {
            validator.validateTemperature(data.getHighestTemp());
        } catch (Exception e) {
            fail("Test failed: " + e.getMessage());
        }
    }

    @Test
    public void temperatureLowestValidTest() throws Exception {
        WeatherData data = repository.getCurrentWeather(request);
        try {
            validator.validateTemperature(data.getLowestTemp());
        } catch (Exception e) {
            fail("Test failed: " + e.getMessage());
        }
    }

    @Test
    public void temperatureValidTest() throws Exception {
        WeatherData data = repository.getCurrentWeather(request);
        try {
            validator.validateTemperature(data.getTemp());
        } catch (Exception e) {
            fail("Test failed: " + e.getMessage());
        }
    }

    @Test
    public void dateValidTest() throws Exception {
        WeatherData data = repository.getCurrentWeather(request);
        try {
            validator.validateCurrentDate(data.getDate());
        } catch (Exception e) {
            fail("Test failed: " + e.getMessage());
        }
    }

}
