package data;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import static java.lang.Long.parseLong;

public class WeatherData implements DataModel {
    private double lat;
    private double lon;
    private String city;
    private String date;
    private double temp;
    private double lowestTemp;
    private double highestTemp;

    public WeatherData(String city, String date, double lowestTemp, double highestTemp, double lon, double lat, double temp) {
        this.city = city;
        this.date = date;
        this.lowestTemp = lowestTemp;
        this.highestTemp = highestTemp;
        this.lon = lon;
        this.lat = lat;
        this.temp = temp;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public double getLowestTemp() {
        return lowestTemp;
    }

    @Override
    public double getHighestTemp() {
        return highestTemp;
    }

    @Override
    public double getLat() {
        return lat;
    }

    @Override
    public double getLon() {
        return lon;
    }

    @Override
    public double getTemp() {
        return temp;
    }

    public String getFormattedDate(String date) {
        long epoch = parseLong(date) * 1000;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Tallinn"));
        return sdf.format(epoch);
    }

    @Override
    public String toString() {
        return "Weather for date (" + date + "): Minimum: " + lowestTemp + ", Maximum: " + highestTemp + ".";
    }

    public String getCurrentWeatherToString() {
        return "Temperature right now today (" + getFormattedDate(date) + ") is: " + temp;
    }
}
