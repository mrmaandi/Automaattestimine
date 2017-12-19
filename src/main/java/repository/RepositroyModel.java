package repository;

import data.WeatherData;
import request.Request;

import java.util.ArrayList;

public interface RepositroyModel {
    WeatherData getCurrentWeather(Request request) throws Exception;
    ArrayList<WeatherData> getForecastWeather(Request request);
}
