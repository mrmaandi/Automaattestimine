package fileactions;

import data.WeatherData;
import helpers.Constants;
import repository.Repository;
import request.Request;

import java.io.*;
import java.util.ArrayList;

public class WriteFile {
    private String path;

    public WriteFile(){
        path = "C:\\Users\\Vill-\\IdeaProjects\\Automaattestimine\\src\\main\\java\\filereader\\";
    }

    public void writeToFile(ArrayList<String> cityList) throws FileNotFoundException {
        String path = "C:\\Users\\Vill-\\IdeaProjects\\Automaattestimine\\src\\main\\java\\filereader\\";
        String input = path + "input.txt";
        BufferedWriter writer = null;
        FileWriter outputWriter = null;

        // This will reference one line at a time
        try {
            for (String city : cityList) {
                // Current weather
                Request request = new Request(city, Constants.COUNTRY_CODE.EE, Constants.UNIT.metric);
                Repository repository = new Repository();
                WeatherData currentWeather = repository.getCurrentWeather(request);

                String outputpath = "C:\\Users\\Vill-\\IdeaProjects\\Automaattestimine\\src\\main\\java\\filereader\\city_results\\";
                outputWriter = new FileWriter(outputpath + currentWeather.getCity()+".txt");
                writer = new BufferedWriter(outputWriter);
                writer.write(currentWeather.toString() + "\n");

                ArrayList<WeatherData> forecastList = repository.getForecastWeather(request);
                for (WeatherData data : forecastList) {
                    writer.write(data.toString() + "\n");
                }
                writer.close();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + input + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + input + "'");
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}
