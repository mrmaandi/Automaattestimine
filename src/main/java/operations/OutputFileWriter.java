package operations;

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
        for (String city : inputDataList.getCities()) {
            // Current weather
            Request request = new Request(city, EE, metric);
            Repository repository = new Repository();

            writeFilesBufferedWriter(outputDestination, request, repository);
        }
    }

    void writeFilesBufferedWriter(String outputDestination, Request request, Repository repository) {
        WeatherData currentWeather;
        BufferedWriter bufferedWriter;

        try {
            currentWeather = repository.getCurrentWeather(request);

            String outputPath = absolutePath + outputDestination
                    + currentWeather.getCity() + ".txt";
            File outputFile = new File(outputPath);
            FileWriter fileWriter = new FileWriter(outputFile);

            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("City: " + currentWeather.getCity() + "\n" + "Coordinates: "
                    + currentWeather.getLat() + ", " + currentWeather.getLon() + "\n");

            //Forecasts
            writeForecasts(request, repository, bufferedWriter);

            //Current weather
            bufferedWriter.write(currentWeather.getCurrentWeatherToString() + "\n");

            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Could not get weather.");
        }
    }

    void writeForecasts(Request request, Repository repository, BufferedWriter bufferedWriter) throws IOException {
        ArrayList<WeatherData> forecastList = repository.getForecastWeather(request);
        for (WeatherData data : forecastList) {
            String output = "Weather for date (" + data.getDate() + "): Minimum: " + data.getLowestTemp() + ", Maximum: " + data.getHighestTemp();
            bufferedWriter.write(output + "\n");
        }
    }
}
