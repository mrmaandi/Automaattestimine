package repository;

import connection.HTTPConnection;
import data.WeatherData;
import helpers.UnitValidator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import request.Request;

import java.util.ArrayList;
import java.util.List;

public class Repository implements RepositroyModel {
    private HTTPConnection connection;

    public Repository(){
        connection = new HTTPConnection();
    }

    @Override
    public WeatherData getCurrentWeather(Request request) throws Exception {
        try {
            request.fetchJsonCurrentWeatherString(connection);
            JSONObject object = new JSONObject(request.getJsonData());
            JSONObject main = object.getJSONObject("main");
            double temp_min = main.getDouble("temp_min"); // temp min
            double temp_max = main.getDouble("temp_max"); // temp max
            double temp = main.getDouble("temp"); //temp

            String city = object.getString("name"); //city

            String dateString = object.getString("dt");

            JSONObject coordinates = object.getJSONObject("coord");
            double longitude = coordinates.getDouble("lon");
            double latitude = coordinates.getDouble("lat");

            try {
                validateJSONValues(temp_min, temp_max, temp, dateString, longitude, latitude);
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }

            return new WeatherData(city, dateString, temp_min, temp_max, longitude, latitude, temp);

        } catch (JSONException e) {
            System.out.println("Couldn't get current weather, because JSON could not be read.");
            e.printStackTrace();
        }
        return null;
    }

    private void validateJSONValues(double temperatureMinimum, double temperatureMaximum, double temperature, String dateString, double longitude, double latitude) throws Exception {
        UnitValidator unitValidator = new UnitValidator();
        unitValidator.validateGeoLocation(latitude, longitude);
        unitValidator.validateTemperature(temperatureMaximum);
        unitValidator.validateTemperature(temperatureMinimum);
        unitValidator.validateTemperature(temperature);
        unitValidator.validateCurrentDate(dateString);
    }

    @Override
    public ArrayList<WeatherData> getForecastWeather(Request request) {
        // 7, 14, 21
        List<String> jsonStrings = new ArrayList<>();
        ArrayList<WeatherData> forecastList = new ArrayList<>();

        try {
            request.fetchJsonForecastWeatherString(connection);
            JSONObject object = new JSONObject(request.getJsonData());
            JSONObject city = object.getJSONObject("city");
            String name = city.getString("name"); // city name

            JSONObject coordinates = city.getJSONObject("coord");
            double longitude = coordinates.getDouble("lon");
            double latitude = coordinates.getDouble("lat");

            JSONArray list = object.getJSONArray("list");
            jsonStrings.add(list.getString(8));
            jsonStrings.add(list.getString(16));
            jsonStrings.add(list.getString(24));

            for (String s : jsonStrings) {
                JSONObject data = new JSONObject(s);
                String dateString = data.getString("dt_txt");
                JSONObject main = data.getJSONObject("main");
                double temp = main.getDouble("temp");
                double temp_min = main.getDouble("temp_min");
                double temp_max = main.getDouble("temp_max");

                forecastList.add(new WeatherData(name, dateString, temp_min, temp_max, longitude, latitude, temp));
            }
            return forecastList;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
