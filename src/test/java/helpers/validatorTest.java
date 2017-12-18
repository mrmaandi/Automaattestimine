package helpers;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;

public class validatorTest {
    @Before
    public void setupTests(){

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
}
