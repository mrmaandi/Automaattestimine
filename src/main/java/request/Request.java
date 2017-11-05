package request;

import connection.HTTPConnection;
import helpers.Constants;

import java.io.IOException;

public class Request implements RequestModel {
    private String jsonData;
    private String city;
    private Constants.COUNTRY_CODE countryCode;
    private Constants.UNIT unit;

    public Request(String city, Constants.COUNTRY_CODE countryCode, Constants.UNIT unit) {
        this.city = city;
        this.countryCode = countryCode;
        this.unit = unit;
    }

    @Override
    public void fetchJsonCurrentWeatherString() {
        HTTPConnection connection = new HTTPConnection();
        String url = connection.createCurrentWeatherApiURL(city, countryCode, unit);
        try {
            connection.makeHttpUrlConnection(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.jsonData = connection.getJsonData();
    }

    @Override
    public void fetchJsonForecastWeatherString() {
        HTTPConnection connection = new HTTPConnection();
        String url = connection.createForecastApiURL(city, countryCode, unit);
        try {
            connection.makeHttpUrlConnection(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.jsonData = connection.getJsonData();
    }

    @Override
    public String getCity(){
        return city;
    }

    @Override
    public Constants.COUNTRY_CODE getCountryCode(){
        return countryCode;
    }

    @Override
    public Constants.UNIT getUnit(){
        return unit;
    }

    @Override
    public String getJsonData() {
        return jsonData;
    }
}
