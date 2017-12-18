package connection;

import helpers.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPConnection implements connection.httpModel {
    private final String USER_AGENT = "Mozilla/5.0";
    private int responseCode;
    private String jsonData;

    @Override
    public String getJsonData(){
        return jsonData;
    }

    @Override
    public void makeHttpUrlConnection(String url) throws IOException {
        // Here is connection method.

        URL link = new URL(url);

        HttpURLConnection connection = (HttpURLConnection) link.openConnection();

        //add request header
        connection.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = connection.getResponseCode();
        setResponseCode(responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        jsonData = response.toString();
    }


    @Override
    public String createCurrentWeatherApiURL(String city, Constants.COUNTRY_CODE countryCode, Constants.UNIT unit) {
        String APILink = "http://api.openweathermap.org/data/2.5/weather?APPID=";
        String APIKey = "1fd2cd75a11b7d7eef55ceb39d47eeb0";
        return APILink + APIKey + "&q=" + city + "," + countryCode + "&units=" + unit;
    }

    @Override
    public String createForecastApiURL(String city, Constants.COUNTRY_CODE countryCode, Constants.UNIT unit) {
        String APILink = "http://api.openweathermap.org/data/2.5/forecast?APPID=";
        String APIKey = "1fd2cd75a11b7d7eef55ceb39d47eeb0";
        return APILink + APIKey + "&q=" + city + "," + countryCode + "&units=" + unit;
    }

    public int getResponseCode() {
        return responseCode;
    }

    private void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}
