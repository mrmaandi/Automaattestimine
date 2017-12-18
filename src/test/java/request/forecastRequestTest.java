package request;

import data.WeatherData;
import helpers.Constants;
import helpers.UnitValidator;
import org.junit.Before;
import org.junit.Test;
import repository.Repository;

import java.util.ArrayList;

import static helpers.Constants.COUNTRY_CODE.EE;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class forecastRequestTest {
    private Request request;
    private Repository repository;
    private UnitValidator validator;

    @Before
    public void setupTests() {
        request = new Request("Tallinn", EE, Constants.UNIT.metric);
        repository = new Repository();
        validator = new UnitValidator();
    }

    @Test
    public void forecastListLengthThreeTest(){
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
    public void dailyLowsValidationTest(){
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
    public void dailyHighsValidationTest(){
        ArrayList<WeatherData> data = repository.getForecastWeather(request);
        for (WeatherData forecast : data) {
            try {
                validator.validateTemperature(forecast.getHighestTemp());
            } catch (Exception e) {
                fail("Test failed: " + e.getMessage());
            }
        }
    }


}
