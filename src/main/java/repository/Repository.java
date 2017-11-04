package repository;

import data.WeatherData;
import org.json.JSONException;
import org.json.JSONObject;
import request.Request;

import java.util.Date;

public class Repository {

    public WeatherData getCurrentWeather(Request request){
        try {
            JSONObject obj = new JSONObject(request.getJsonData());
            JSONObject main = obj.getJSONObject("main");
            double temp_min = main.getDouble("temp_min"); // temp min
            double temp_max = main.getDouble("temp_max"); // temp max
            System.out.println(temp_min + " " + temp_max);

            String city = obj.getString("name"); //city
            int dt = obj.getInt("dt");
            Date date = new Date();
            date.setTime(dt); // Date
            System.out.println(city);
            System.out.println(date.getTime());

            JSONObject coord = obj.getJSONObject("coord");
            double lon = coord.getDouble("lon");
            double lat = coord.getDouble("lat");

            return new WeatherData(city, date, temp_min, temp_max, lon, lat);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
