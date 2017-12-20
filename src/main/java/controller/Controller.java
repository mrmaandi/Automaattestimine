package controller;

import data.InputDataList;
import data.WeatherData;
import operations.InputFileReader;
import operations.OutputFileWriter;
import repository.Repository;
import request.Request;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;

import static helpers.Constants.COUNTRY_CODE.EE;
import static helpers.Constants.UNIT.metric;

public class Controller {

    private String absolutePath = Paths.get("").toAbsolutePath().toString();
    private File inputFile = new File(absolutePath + "\\src\\main\\java\\operations\\input.txt");
    private String outputDestination = "\\src\\main\\java\\operations\\cityresults\\";

    public void initialize() throws IOException {
        // Read from file.
        InputDataList inputDataList = new InputDataList();

        InputFileReader inputFileReader = createInputFileReader(inputDataList);
        inputFileReader.readFromFile(inputFile);

        // Write to file
        OutputFileWriter outputFileWriter = createOutputFileWriter(inputDataList);
        outputFileWriter.writeToFile(outputDestination);

        try {
            askUserInput();
        } catch (NullPointerException e) {
            System.out.println("The program will stop.");
        }

    }

     OutputFileWriter createOutputFileWriter(InputDataList inputDataList) {
        return new OutputFileWriter(inputDataList);
    }

     InputFileReader createInputFileReader(InputDataList inputDataList) {
        return new InputFileReader(inputDataList);
    }

    void askUserInput() throws IOException {
        // Ask user input.
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter city:");
        String userInput = bufferedReader.readLine();

        try {
            displayWeatherResults(userInput);
        } catch (Exception e) {
            System.out.println("Some values are incorrect.");
        }
    }

    void displayWeatherResults(String userInput) throws Exception {
        if(userInput != null){
            // Print current weather
            Request request = new Request(userInput, EE, metric);
            Repository repository = new Repository();
            WeatherData currentWeather = repository.getCurrentWeather(request);

            System.out.println("Here are your results for the next 3 days:");
            System.out.println("City: " + currentWeather.getCity() + "\n" + "Coordinates: "
                    + currentWeather.getLat() + ", " + currentWeather.getLon());

            // Print forecast weather
            ArrayList<WeatherData> forecastList = repository.getForecastWeather(request);
            for (WeatherData data : forecastList) {
                System.out.println(data.toString());
            }

            System.out.println(currentWeather.getCurrentWeatherToString());
        }
    }
}
