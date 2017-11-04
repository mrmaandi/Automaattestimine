package data;

import java.util.Date;

/**
 * Created by Vill- on 11/3/2017.
 */
public class CurrentWeather extends WeatherData {
    public CurrentWeather(String city, Date date, double lowestTemp, double highestTemp, double lat, double lon) {
        super(city, date, lowestTemp, highestTemp, lat, lon);
    }
}
