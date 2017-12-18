package repository;

import data.WeatherData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import request.Request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Repository implements RepositroyModel {

    @Override
    public WeatherData getCurrentWeather(Request request) {
        try {
            request.fetchJsonCurrentWeatherString();
            JSONObject object = new JSONObject(request.getJsonData());
            JSONObject main = object.getJSONObject("main");
            double temp_min = main.getDouble("temp_min"); // temp min
            double temp_max = main.getDouble("temp_max"); // temp max
            double temp = main.getDouble("temp"); //temp

            String city = object.getString("name"); //city
            int dateTime = object.getInt("dt");
            long epoch = Long.parseLong(Integer.toString(dateTime));
            Date date = new Date(epoch * 1000);

            JSONObject coordinates = object.getJSONObject("coord");
            double lon = coordinates.getDouble("lon");
            double lat = coordinates.getDouble("lat");

            return new WeatherData(city, date, temp_min, temp_max, lon, lat, temp);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<WeatherData> getForecastWeather(Request request) {
        // 7, 14, 21
        List<String> jsonStrings = new ArrayList<>();
        ArrayList<WeatherData> forecastList = new ArrayList<>();

        try {
            request.fetchJsonForecastWeatherString();
            JSONObject obj = new JSONObject(request.getJsonData());
            JSONObject city = obj.getJSONObject("city");
            String name = city.getString("name"); // city name
            //System.out.println(name);

            JSONObject coordinates = city.getJSONObject("coord");
            double lon = coordinates.getDouble("lon");
            double lat = coordinates.getDouble("lat");
            //System.out.println(lon + " " + lat);

            JSONArray list = obj.getJSONArray("list");
            jsonStrings.add(list.getString(8));
            jsonStrings.add(list.getString(16));
            jsonStrings.add(list.getString(24));

            for (String s : jsonStrings) {
                JSONObject data = new JSONObject(s);
                int dateTime = data.getInt("dt");
                long epochTime = Long.parseLong(Integer.toString(dateTime));
                Date date = new Date(epochTime * 1000);
                JSONObject main = data.getJSONObject("main");
                double temp = main.getDouble("temp");
                double temp_min = main.getDouble("temp_min");
                double temp_max = main.getDouble("temp_max");

                forecastList.add(new WeatherData(name, date, temp_min, temp_max, lon, lat, temp));
            }
            return forecastList;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
