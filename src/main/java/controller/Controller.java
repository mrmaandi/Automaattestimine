package controller;

import data.InputDataList;
import data.WeatherData;
import filereader.InputFileReader;
import filereader.OutputFileWriter;
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
    private File inputFile = new File(absolutePath + "\\src\\main\\java\\filereader\\input.txt");
    private String outputDestination = "\\src\\main\\java\\filereader\\cityresults\\";

    public void initialize() throws IOException {
        // Read from file.
        InputDataList inputDataList = new InputDataList();

        InputFileReader inputFileReader = new InputFileReader(inputDataList);
        inputFileReader.readFromFile(inputFile);

        // Write to file
        OutputFileWriter outputFileWriter = new OutputFileWriter(inputDataList);
        outputFileWriter.writeToFile(outputDestination);

        askUserInput();
    }

    void askUserInput() throws IOException {
        // Ask user input.
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter city:");
        String userInput = bufferedReader.readLine();

        displayWeatherResults(userInput);
    }

    void displayWeatherResults(String userInput) {
        // Print current weather
        Request request = new Request(userInput, EE, metric);
        Repository repository = new Repository();
        WeatherData currentWeather = repository.getCurrentWeather(request);
        System.out.println(currentWeather.toString());

        // Print forecast weather
        ArrayList<WeatherData> forecastList = repository.getForecastWeather(request);
        for (WeatherData data : forecastList) {
            System.out.println(data.toString());
        }
    }
}