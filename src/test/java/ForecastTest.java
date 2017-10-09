import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static helper.UnitValidator.validateDate;
import static helper.UnitValidator.validateTemperature;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ForecastTest {
    private weatherRepository repository;
    private weatherRequest request;
    private weatherData weatherData;
    private int requestedDays = 3;
    private List<weatherData> forecastList;
    private String COUNTRY_CODE = "EE";
    private String UNIT = "metric";

    @BeforeClass
    // We set up all the tests
    public void setupTests() {
        request = new weatherRequest("Tallinn", COUNTRY_CODE, UNIT);
        forecastList = new ArrayList<>();
        //Given
        repository = new weatherRepository();
        //When
        try {
            weatherData = repository.getForecast(request);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void requestCityMatchesResponseCity() {
        //Test if the requested city name matches the response city name.
        try {
            assertEquals(weatherData.getCityName(), request.city);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void dailyTemperaturesHighestValid() {
        try {
            //foreach day in the forecast
            for (weatherData day : request.forecastList) {
                validateTemperature(weatherData.getTemperatureHighest());
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void dailyTemperaturesLowestValid() {
        try {
            //foreach day in the forecast
            for (weatherData day : request.forecastList) {
                validateTemperature(weatherData.getTemperatureLowest());
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void forecastThreeDays() {
        //test if we can get three day forecast
        try {
            assertEquals(requestedDays, request.getDaysLength());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void forecastDatesValid() {
        //test if our forecast dates are valid
        try {
            for (weatherData day : request.forecastList) {
                validateDate(day.getDate());
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
