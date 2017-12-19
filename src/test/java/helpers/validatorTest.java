package helpers;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;

public class validatorTest {
    private UnitValidator unitValidator;

    @Before
    public void setupTests(){
        unitValidator = new UnitValidator();
    }

    @Test
    public void currentTimeTest(){
        LocalDateTime now = LocalDateTime.now(); // current date
        assertTrue(now.getYear() > 2016);
    }

    @Test
    public void currentRequestTimeValidTest(){
        LocalDate d = LocalDate.of(1,2,3);
        Date date = Date.valueOf(d);
    }

    @Test(expected = Exception.class)
    public void validateTemperatureShouldThrowExceptionIfRequestTempNull() throws Exception {
        unitValidator.validateTemperature(null);
    }

    @Test(expected = Exception.class)
    public void validateTemperatureShouldThrowExceptionIfRequestedTempUnderMinimum() throws Exception {
        unitValidator.validateTemperature((double) -71);
    }

    @Test(expected = Exception.class)
    public void validateTemperatureShouldThrowExceptionIfRequestedTempOverMaximum() throws Exception {
        unitValidator.validateTemperature((double) 71);
    }

    @Test(expected = Exception.class)
    public void validateGeoLocationShouldThrowExceptionWhenLatitudeNull() throws Exception {
        unitValidator.validateGeoLocation(null, (double) 123);
    }

    @Test(expected = Exception.class)
    public void validateGeoLocationShouldThrowExceptionWhenLongitudeNull() throws Exception {
        unitValidator.validateGeoLocation((double) 321, null);
    }

    @Test(expected = Exception.class)
    public void validateGeoLocationShouldThrowExceptionIfRequestedLatitudeUnderMinimum() throws Exception {
        unitValidator.validateGeoLocation((double)-91, (double) -90);
    }

    @Test(expected = Exception.class)
    public void validateGeoLocationShouldThrowExceptionIfRequestedLongitudeUnderMinimum() throws Exception {
        unitValidator.validateGeoLocation((double)90, (double) -181);
    }

    @Test(expected = Exception.class)
    public void validateGeoLocationShouldThrowExceptionIfRequestedLatitudeOverMaximum() throws Exception {
        unitValidator.validateGeoLocation((double)91, (double) -90);
    }

    @Test(expected = Exception.class)
    public void validateGeoLocationShouldThrowExceptionIfRequestedLongitudeOverMaximum() throws Exception {
        unitValidator.validateGeoLocation((double)90, (double) 181);
    }

    @Test(expected = Exception.class)
    public void validateCurrentDateShouldThrowExceptionIfDateNull() throws Exception {
        unitValidator.validateCurrentDate(null);
    }



}
