package request;

import connection.HTTPConnection;
import helpers.Constants.COUNTRY_CODE;
import helpers.Constants.UNIT;

import java.io.IOException;

public class Request implements RequestModel {
    private String jsonData;
    private String city;
    private COUNTRY_CODE countryCode;
    private UNIT unit;

    public Request(String city, COUNTRY_CODE countryCode, UNIT unit) {
        this.city = city;
        this.countryCode = countryCode;
        this.unit = unit;
    }

    @Override
    public void fetchJsonCurrentWeatherString(HTTPConnection connection) {
        String url = connection.createCurrentWeatherApiURL(city, countryCode, unit);
        try {
            connection.makeHttpUrlConnection(url);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.jsonData = connection.getJsonData();
    }

    @Override
    public void fetchJsonForecastWeatherString(HTTPConnection connection) {
        String url = connection.createForecastApiURL(city, countryCode, unit);
        try {
            connection.makeHttpUrlConnection(url);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.jsonData = connection.getJsonData();
    }

    @Override
    public String getCity(){
        return city;
    }

    @Override
    public COUNTRY_CODE getCountryCode(){
        return countryCode;
    }

    @Override
    public UNIT getUnit(){
        return unit;
    }

    @Override
    public String getJsonData() {
        return jsonData;
    }
}
