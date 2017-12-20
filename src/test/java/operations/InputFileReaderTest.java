package operations;

import data.InputDataList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.*;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class InputFileReaderTest {
    private String absolutePath = Paths.get("").toAbsolutePath().toString();
    private File inputFile = new File(absolutePath + "\\src\\test\\java\\operations\\input.txt");

    @Mock
    private operations.InputFileReader inputFileReader;
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
    public void getFileReaderShouldReturnNewFileReader() throws IOException {
        InputDataList inputDataList = new InputDataList();
        inputDataList.addToCities("Tallinn");
        inputFileReader.setInputDataList(inputDataList);
        File inputFile = mock(File.class);
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(inputFileReader.createBufferedReader(inputFile)).thenReturn(bufferedReader);

        assertEquals(inputFileReader.getFileReader(inputFile), any(FileReader.class));
    }

    @Test
    public void inputFileReader(){
        InputDataList inputDataList = new InputDataList();
        inputDataList.addToCities("Tallinn");
        new InputFileReader(inputDataList);
    }

    @Test
    public void setInputDataListShouldSetList(){
        InputDataList inputDataList = new InputDataList();
        InputFileReader inputFileReader = new InputFileReader(inputDataList);
        inputFileReader.setInputDataList(inputDataList);

        assertEquals(inputFileReader.getInputDataList(), inputDataList);
    }

    @Test
    public void createBufferedReaderShouldGetFileReader() throws FileNotFoundException {
        BufferedReader bufferedReader = inputFileReader.createBufferedReader(inputFile);

        assertEquals(bufferedReader, any(BufferedReader.class));
    }

    @Test
    public void readFromFileShouldCreateBufferedReader() {
        inputFileReader.readFromFile(inputFile);

        try {
            verify(inputFileReader).createBufferedReader(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test(expected=FileNotFoundException.class)
    public void bufferedReaderShouldNotReturnReaderIfFileNotFound(){
        inputFileReader.readFromFile(new File("random/path"));
    }

}