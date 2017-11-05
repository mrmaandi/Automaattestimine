package repository;

import data.WeatherData;
import request.Request;

import java.util.ArrayList;

public interface RepositroyModel {
    public WeatherData getCurrentWeather(Request request);
    public ArrayList<WeatherData> getForecastWeather(Request request);
}
