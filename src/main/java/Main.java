import filereader.ReadWriteFile;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Enter city:");
//        String s = br.readLine();
//
//        // Current weather
//        Request request = new Request(s, Constants.COUNTRY_CODE.EE, Constants.UNIT.metric);
//        Repository repository = new Repository();
//        WeatherData currentWeather = repository.getCurrentWeather(request);
//        System.out.println(currentWeather.toString());
//
//        ArrayList<WeatherData> forecastList = repository.getForecastWeather(request);
//        for (WeatherData data : forecastList) {
//            System.out.println(data.toString());
//        }

        ReadWriteFile readFile = new ReadWriteFile();
        readFile.writeToFile();

    }
}
