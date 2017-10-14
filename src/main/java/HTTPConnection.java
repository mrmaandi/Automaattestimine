import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HTTPConnection {
    private final String USER_AGENT = "Mozilla/5.0";
    private int responseCode;

    public void makeHttpUrlConnection(String url) throws IOException {
        // Here is connection method.

        URL link = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) link.openConnection();


        //add request header
        connection.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = connection.getResponseCode();
        setResponseCode(responseCode);

        System.out.println("\nSending 'GET' request to URL : " + link);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
    }

    public String createCurrentWeatherApiURL(String city, String countryCode, String units) {
        String APILink = "http://api.openweathermap.org/data/2.5/weather?APPID=";
        String APIKey = "1fd2cd75a11b7d7eef55ceb39d47eeb0";
        return APILink + APIKey + "&q=" + city + "&unit=metric";
    }


    public String createForecastApiURL(String city, String countryCode, String units) {
        String APILink = "http://api.openweathermap.org/data/2.5/forecast?APPID=";
        String APIKey = "1fd2cd75a11b7d7eef55ceb39d47eeb0";
        return APILink + city + ", " + countryCode + "&appid=" + APIKey + "&unit=metric";
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}
