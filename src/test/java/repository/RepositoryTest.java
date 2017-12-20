package repository;

import connection.HTTPConnection;
import data.WeatherData;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import request.Request;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class RepositoryTest {
    @Mock
    private HTTPConnection httpConnection;
    @Mock
    private Repository repository;
    @Mock
    private Request request;

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test
    public void getCurrentWeather() throws Exception {
//        Request request = new Request("Tallinn", EE, metric);

        WeatherData weatherData = repository.getCurrentWeather(request);

        verify(request).fetchJsonCurrentWeatherString(repository.getConnection());

    }
}
