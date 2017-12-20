package controller;

import data.InputDataList;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
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
    }

    @Ignore
    public void initializeShouldAskUserInput() throws IOException {
        doNothing().when(controller).askUserInput();

        controller.initialize();

        verify(controller).createInputFileReader(any(InputDataList.class));
        verify(controller).askUserInput();
    }

}
