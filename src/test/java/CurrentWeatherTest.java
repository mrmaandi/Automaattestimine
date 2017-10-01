import org.junit.Before;
import org.junit.Test;

import static helper.UnitValidator.validateTemperature;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrentWeatherTest {
    private WeatherRepository weatherRepository;
    private WeatherRequest request;
    private WeatherData weatherData;

    @Before
    // We set up all the tests
    public void setupTests() {
        //request = new WeatherRequest("Tallinn", Constants.COUNTRY_CODE.EE, Constants.UNIT.metric);

        //Example:
//        request.city = "Tallinn";
//        request.temperatureHighest = 35;
//        request.temperatureLowest = 15;
//        request.geoLang = 55;
//        request.geoLat = 50;

        //Given
        weatherRepository = new WeatherRepository();
        //When
        weatherData = weatherRepository.getCurrentWeather(request);
    }

    @Test
    public void receiveGeoLocation() {
        //Test if we can get valid Geo coordinates from a location.
        //"given" weatherRepository, "when" weatherRequest
        //"then":
        try {
            //assertEquals();
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
            assertEquals(weatherData.temperatureHighest, request.temperatureHighest);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void getTemperatureLowest() throws Exception {
        //Test if we can get the lowest temperature for the day. And check if they are correct data.
        try {
            assertEquals(weatherData.temperatureLowest, request.temperatureLowest);
            throw new Exception("test");
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }
}
