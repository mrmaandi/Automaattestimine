package filereader;

import data.InputDataList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class InputFileReaderTest {
    private String absolutePath = Paths.get("").toAbsolutePath().toString();
    private File inputFile = new File(absolutePath + "\\src\\test\\java\\filereader\\input.txt");

    @Mock
    private InputFileReader inputFileReader;
    @Mock
    private BufferedReader bufferedReader;
    @Mock
    private InputDataList inputDataList;

    @Before
    public void setUp(){
        initMocks(this);
        reset(inputFileReader, bufferedReader, inputDataList);
    }

    @Test
    public void readFromFile() throws IOException {
        FileReader fileReader = new FileReader(inputFile);

        when(inputFileReader.createBufferedReader(inputFile)).thenReturn(bufferedReader);
        when(inputFileReader.getFileReader(inputFile)).thenReturn(fileReader);
        when(bufferedReader.readLine()).thenReturn("Tallinn");

        inputFileReader.readFromFile(inputFile);

        verify(inputFileReader).readFromFile(inputFile);

//        verify(inputDataList).addToCities(anyString());
    }
}