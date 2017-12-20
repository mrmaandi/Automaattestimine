package request;

import connection.HTTPConnection;
import data.WeatherData;
import helpers.UnitValidator;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import repository.Repository;

import java.util.ArrayList;

import static helpers.Constants.COUNTRY_CODE.EE;
import static helpers.Constants.UNIT.metric;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class forecastRequestTest {
    private Request request;
    private Repository repository;
    private UnitValidator validator;

    @Before
    public void setupTests() {
        request = new Request("Tallinn", EE, metric);
        repository = new Repository();
        validator = new UnitValidator();
    }

    @Test
    public void forecastListLengthThreeTest() {
        ArrayList<WeatherData> data = repository.getForecastWeather(request);
        assertEquals(3, data.size());
    }

    @Test
    public void requestCityMatchesResponseTest() {
        ArrayList<WeatherData> data = repository.getForecastWeather(request);
        for (WeatherData forecast : data) {
            assertEquals(forecast.getCity(), request.getCity());
        }
    }

    @Test
    public void coordinatesValidTest() {
        ArrayList<WeatherData> data = repository.getForecastWeather(request);
        for (WeatherData forecast : data) {
            try {
                validator.validateGeoLocation(forecast.getLat(), forecast.getLon());
            } catch (Exception e) {
                fail("Test failed: " + e.getMessage());
            }
        }
    }

    @Test
    public void dailyLowsValidationTest() {
        ArrayList<WeatherData> data = repository.getForecastWeather(request);
        for (WeatherData forecast : data) {
            try {
                validator.validateTemperature(forecast.getLowestTemp());
            } catch (Exception e) {
                fail("Test failed: " + e.getMessage());
            }
        }
    }

    @Test
    public void dailyHighsValidationTest() {
        ArrayList<WeatherData> data = repository.getForecastWeather(request);
        for (WeatherData forecast : data) {
            try {
                validator.validateTemperature(forecast.getHighestTemp());
            } catch (Exception e) {
                fail("Test failed: " + e.getMessage());
            }
        }
    }

    @Ignore
    public void fetchJsonCurrentWeatherStringShouldThrowIOException() {
        HTTPConnection connection = mock(HTTPConnection.class);
        when(connection.createCurrentWeatherApiURL(any(), any(), any()))
                .thenReturn("http://api.openweathermap.org/data/2.5/weather?APPID=1fd2cd75a11b7d7eef55ceb39d47eeb0&q=Tallinn,EE&units=metric");

        request.fetchJsonCurrentWeatherString(connection);
    }


}
