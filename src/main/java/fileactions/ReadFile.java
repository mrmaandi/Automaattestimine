package fileactions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {
    private ArrayList<String> cities;
    private String inputFile;
    private String path;

    public ReadFile(String inputFile) {
        this.inputFile = inputFile;
        cities = new ArrayList<>();
        path = "C:\\Users\\Vill-\\IdeaProjects\\Automaattestimine\\src\\main\\java\\filereader\\";
    }

    public void setPath(String pathname) {
        this.path = pathname;
    }

    public ArrayList<String> getCities() {
        return cities;
    }

    public void readFile() throws FileNotFoundException {
        String input = path + "input.txt";
        BufferedReader reader = null;
        FileReader fileReader = new FileReader(input);

        // This will reference one line at a time
        String city;
        try {
            reader = new BufferedReader(fileReader);

            while ((city = reader.readLine()) != null) {
                cities.add(city);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + input + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + input + "'");
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
