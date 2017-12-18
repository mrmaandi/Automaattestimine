package filereader;

import data.WeatherData;
import repository.Repository;
import request.Request;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static helpers.Constants.COUNTRY_CODE.EE;
import static helpers.Constants.UNIT.metric;

public class ReadWriteFile {
    private String absolutePath = Paths.get("").toAbsolutePath().toString();
    private final Path inputPath = Paths.get(absolutePath + "\\src\\main\\java\\filereader\\input.txt");

    public void writeToFile() throws FileNotFoundException {
        System.out.println("Path: " + inputPath);
        BufferedWriter bufferedWriter;
        BufferedReader bufferedReader;

        // This will reference one line at a time
        try {
            String readLine;
            bufferedReader = Files.newBufferedReader(inputPath, StandardCharsets.UTF_8);

            while ((readLine = bufferedReader.readLine()) != null) {
                // Current weather
                Request request = new Request(readLine, EE, metric);
                Repository repository = new Repository();
                WeatherData currentWeather = repository.getCurrentWeather(request);

                Path outputPath = Paths.get(absolutePath + "\\src\\main\\java\\filereader\\cityresults\\"
                        + currentWeather.getCity() + ".txt");
                bufferedWriter = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8);
                bufferedWriter.write(currentWeather.toString() + "\n");

                ArrayList<WeatherData> forecastList = repository.getForecastWeather(request);
                for (WeatherData data : forecastList) {
                    bufferedWriter.write(data.toString() + "\n");
                }
                bufferedWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
