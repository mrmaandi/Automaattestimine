package connection;

import helpers.Constants.COUNTRY_CODE;
import helpers.Constants.UNIT;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPConnection implements connection.httpModel {
    private final String USER_AGENT = "Mozilla/5.0";
    private int responseCode;
    private String jsonData;

    @Override
    public String getJsonData() {
        return jsonData;
    }

    @Override
    public void makeHttpUrlConnection(String url) throws IOException {
        if(url != null){
            // Here is connection method.

            URL link = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) link.openConnection();

            // add request header
            connection.setRequestProperty("User-Agent", USER_AGENT);

            responseCode = connection.getResponseCode();

            if (responseCode == 404) {
                throw new FileNotFoundException("Could not find a file for that input. Please enter correct city.");
            }

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            jsonData = response.toString();
        }
    }


    @Override
    public String createCurrentWeatherApiURL(String city, COUNTRY_CODE countryCode, UNIT unit) {
        String APILink = "http://api.openweathermap.org/data/2.5/weather?APPID=";
        String APIKey = "1fd2cd75a11b7d7eef55ceb39d47eeb0";
        return APILink + APIKey + "&q=" + city + "," + countryCode + "&units=" + unit;
    }

    @Override
    public String createForecastApiURL(String city, COUNTRY_CODE countryCode, UNIT unit) {
        String APILink = "http://api.openweathermap.org/data/2.5/forecast?APPID=";
        String APIKey = "1fd2cd75a11b7d7eef55ceb39d47eeb0";
        return APILink + APIKey + "&q=" + city + "," + countryCode + "&units=" + unit;
    }

    public int getResponseCode() {
        return responseCode;
    }
}
