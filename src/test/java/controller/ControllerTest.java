package controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.reset;
import static org.mockito.MockitoAnnotations.initMocks;

public class ControllerTest {
    @Mock
    private Controller controller;

    @Before
    public void setUp() {
        initMocks(this);
        reset(controller);
    }

    @Test
    public void displayWeatherResult() throws Exception {
        controller.displayWeatherResults("Tallinn");
    }

    @Test
    public void askUserInputShouldDisplayWeatherResults() throws Exception {
        controller.askUserInput();

//        verify(controller).displayWeatherResults(anyString());
    }

}
