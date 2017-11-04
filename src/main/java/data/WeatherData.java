package data;

import java.util.Date;

public class WeatherData implements DataModel{
    private final double lat;
    private final double lon;
    private String city;
    private Date date;
    private double lowestTemp;
    private double highestTemp;

    public WeatherData(String city, Date date, double lowestTemp, double highestTemp, double lon, double lat){
        this.city = city;
        this.date = date;
        this.lowestTemp = lowestTemp;
        this.highestTemp = highestTemp;
        this.lon = lon;
        this.lat = lat;
    }

    public String getCity() {
        return city;
    }

    public Date getDate() {
        return date;
    }

    public double getLowestTemp() {
        return lowestTemp;
    }

    public double getHighestTemp() {
        return highestTemp;
    }


    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
