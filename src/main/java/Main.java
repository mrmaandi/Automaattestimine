import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        HTTPConnection con = new HTTPConnection();
        try {
            con.makeHttpUrlConnection(con.createCurrentWeatherApiURL());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
