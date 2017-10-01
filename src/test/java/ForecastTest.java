import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static helper.UnitValidator.validateTemperature;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ForecastTest {
    private weatherRepository repository;
    private weatherRequest request;
    private weatherData weatherData;
    private int requestedDays = 3;
    //private List<Double> forecastList = new ArrayList<>();

    @Before
    // We set up all the tests
    public void setupTests() {
        //request = new weatherRequest("Tallinn", Constants.COUNTRY_CODE.EE, Constants.UNIT.metric);

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
            assertEquals(weatherData.city, request.city);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void dailyTemperaturesHighestValid() {
        try {
            //foreach day in the forecast
            for (Object day : request.forecastList) {
                validateTemperature(day.temperatureHighest);
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void dailyTemperaturesLowestValid() {
        try {
            //foreach day in the forecast
            for (Object day : request.forecastList) {
                validateTemperature(day.temperatureLowest);
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void forecastThreeDays() {
        //test if we can get three day forecast
        try {
            assertEquals(requestedDays, request.days.length);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void forecastDatesValid() {
        //test if our forecast dates are valid
        try {
            for (Object day : request.forecastList) {
                validateDate(day);
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
