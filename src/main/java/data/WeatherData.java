package data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class WeatherData implements DataModel{
    private double lat;
    private double lon;
    private String city;
    private Date date;
    private double temp;
    private double lowestTemp;
    private double highestTemp;

    public WeatherData(String city, Date date, double lowestTemp, double highestTemp, double lon, double lat, double temp){
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
    public Date getDate() {
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

    public String getFormattedDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }

    @Override
    public String toString(){
        return "Temperature in " + city + " (for " + getFormattedDate() + ") is " + temp + ", highest is: " + highestTemp +
                " and lowest is: " + lowestTemp + ".";
    }
}
