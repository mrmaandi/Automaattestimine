package filereader;

import data.InputDataList;

import java.io.*;

public class InputFileReader {

    private InputDataList inputDataList;

    public InputFileReader(InputDataList inputDataList){
        this.inputDataList = inputDataList;
    }

    public void readFromFile(File inputFile){
        BufferedReader bufferedReader;
        try {
            String readLine;
            bufferedReader = createBufferedReader(inputFile);
            while ((readLine = bufferedReader.readLine()) != null) {
                System.out.println("Reading line...");
                inputDataList.addToCities(readLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    BufferedReader createBufferedReader(File inputFile) throws FileNotFoundException {
        BufferedReader bufferedReader;
        FileReader reader = getFileReader(inputFile);
        bufferedReader = new BufferedReader(reader);
        return bufferedReader;
    }

    FileReader getFileReader(File inputFile) throws FileNotFoundException {
        return new FileReader(inputFile);
    }

}
