import org.junit.Before;
import org.junit.Test;

import static helper.UnitValidator.validateGeoLocation;
import static helper.UnitValidator.validateTemperature;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrentWeatherTest {
    private weatherRepository repository;
    private weatherRequest request;
    private weatherData weatherData;

    @Before
    // We set up all the tests
    public void setupTests() {
        //request = new weatherRequest("Tallinn", Constants.COUNTRY_CODE.EE, Constants.UNIT.metric);

        //Example data:
//        request.city = "Tallinn";
//        request.temperatureHighest = 35;
//        request.temperatureLowest = 15;
//        request.geoLang = 55;
//        request.geoLat = 50;

        //Given
        repository = new weatherRepository();
        //When
        weatherData = repository.getCurrentWeather(request);
    }

    @Test
    public void receiveGeoLocation() {
        //Test if we can get valid Geo coordinates from a location.
        //"given" weatherRepository, "when" weatherRequest
        //"then":
        try {
            validateGeoLocation(request.geoLat, request.geoLong);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
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
    public void getTemperatureHighest() {
        //Test if we can get the highest temperature for the day. And check if they are correct data.
        try {
            validateTemperature(request.temperatureHighest);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void getTemperatureLowest() throws Exception {
        //Test if we can get the lowest temperature for the day. And check if they are correct data.
        try {
            validateTemperature(request.temperatureLowest);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }
}
