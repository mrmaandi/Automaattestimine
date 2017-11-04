package repository;

import data.WeatherData;
import request.Request;

public interface RepositroyModel {
    public WeatherData getCurrentWeather(Request request);
    public WeatherData getForecast(Request request);
}
