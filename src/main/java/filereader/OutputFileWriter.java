package filereader;

import data.InputDataList;
import data.WeatherData;
import repository.Repository;
import request.Request;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import static helpers.Constants.COUNTRY_CODE.EE;
import static helpers.Constants.UNIT.metric;

public class OutputFileWriter {
    private String absolutePath = Paths.get("").toAbsolutePath().toString();
    private InputDataList inputDataList;

    public OutputFileWriter(InputDataList inputDataList) {
        this.inputDataList = inputDataList;
    }

    public void writeToFile(String outputDestination) {
        BufferedWriter bufferedWriter;

        try {
            for (String city : inputDataList.getCities()) {
                // Current weather
                Request request = new Request(city, EE, metric);
                Repository repository = new Repository();
                WeatherData currentWeather = repository.getCurrentWeather(request);

                String outputPath = absolutePath + outputDestination
                        + currentWeather.getCity() + ".txt";
                File outputFile = new File(outputPath);
                FileWriter fileWriter = new FileWriter(outputFile);
                bufferedWriter = new BufferedWriter(fileWriter);

                bufferedWriter.write("City: " + city + "\n" + "Coordinates: "
                        + currentWeather.getLat() + ", " + currentWeather.getLon() + "\n");

                ArrayList<WeatherData> forecastList = repository.getForecastWeather(request);
                for (WeatherData data : forecastList) {
                    // linna nimi, koordinaadid, iga ennustatud päeva kohta min, max temp, hetke ilma temp.
                    String output = "Weather for date (" + data.getFormattedDate() + "): Minimum: " + data.getLowestTemp() + ", Maximum: " + data.getHighestTemp();
                    bufferedWriter.write(output + "\n");
                }

                bufferedWriter.write(currentWeather.toString() + "\n");

                bufferedWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
