import fileactions.ReadFile;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static junit.framework.TestCase.assertTrue;

public class FileReadWriteMockTest {
    @Before
    public void setupTests(){

    }

    @Test
    public void readFileTest(){
        ReadFile readFile = new ReadFile("tartu_tallinn.txt");
        String path = "C:\\Users\\Vill-\\IdeaProjects\\Automaattestimine\\src\\test\\java\\files\\";
        try {
            readFile.readFile();
            readFile.setPath(path);
            assertTrue(readFile.getCities().contains("tallinn"));
            assertTrue(readFile.getCities().contains("tartu"));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
