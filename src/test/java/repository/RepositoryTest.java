package repository;

import org.junit.Ignore;
import request.Request;

import static helpers.Constants.COUNTRY_CODE.EE;
import static helpers.Constants.UNIT.metric;

public class RepositoryTest {

    @Ignore
    public void repositoryGetCurrentWeatherShouldThrowJSONExceptionWhenRequestNull() throws Exception {
        Repository repository = new Repository();
        Request request = new Request("Tallinn", EE, metric);
        repository.getCurrentWeather(request);
    }

}
