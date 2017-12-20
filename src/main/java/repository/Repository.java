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
            JSONObject jsonObject = new JSONObject(request.getJsonData());
            JSONObject mainObject = jsonObject.getJSONObject("main");
            double temperatureMinimum = mainObject.getDouble("temp_min"); // temp min
            double temperatureMaximum = mainObject.getDouble("temp_max"); // temp max
            double temperature = mainObject.getDouble("temp"); //temp

            String city = jsonObject.getString("name"); //city

            String dateString = jsonObject.getString("dt");

            JSONObject coordinates = jsonObject.getJSONObject("coord");
            double longitude = coordinates.getDouble("lon");
            double latitude = coordinates.getDouble("lat");

            try {
                validateJSONValues(temperatureMinimum, temperatureMaximum, temperature, dateString, longitude, latitude);
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }

            return new WeatherData(city, dateString, temperatureMinimum, temperatureMaximum, longitude, latitude, temperature);

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

        List<String> jsonStrings = new ArrayList<>();
        ArrayList<WeatherData> forecastList = new ArrayList<>();

        try {
            request.fetchJsonForecastWeatherString(connection);
            JSONObject jsonObject = new JSONObject(request.getJsonData());
            JSONObject city = jsonObject.getJSONObject("city");
            String name = city.getString("name"); // city name

            JSONObject coordinates = city.getJSONObject("coord");
            double longitude = coordinates.getDouble("lon");
            double latitude = coordinates.getDouble("lat");

            if (jsonObject.getString("cod").equals("404") ) {
                throw new Exception("File code 404.");
            }

            JSONArray list = jsonObject.getJSONArray("list");
            jsonStrings.add(list.getString(8));
            jsonStrings.add(list.getString(16));
            jsonStrings.add(list.getString(24));

            createForecastWeatherData(jsonStrings, forecastList, name, longitude, latitude);
            return forecastList;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    void createForecastWeatherData(List<String> jsonStrings, ArrayList<WeatherData> forecastList, String name, double longitude, double latitude) throws JSONException {
        for (String jsonString : jsonStrings) {
            JSONObject data = new JSONObject(jsonString);
            String dateString = data.getString("dt_txt");
            JSONObject main = data.getJSONObject("main");
            double temp = main.getDouble("temp");
            double temperatureMinimum = main.getDouble("temp_min");
            double temperatureMaximum = main.getDouble("temp_max");

            forecastList.add(new WeatherData(name, dateString, temperatureMinimum, temperatureMaximum, longitude, latitude, temp));
        }
    }

    public HTTPConnection getConnection() {
        return connection;
    }
}
