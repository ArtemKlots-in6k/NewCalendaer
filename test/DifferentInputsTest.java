import calendar.InputDataValidator;
import calendar.MonthCalendar;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by employee on 7/6/16.
 */
public class DifferentInputsTest {
    private InputDataValidator inputDataValidator = new InputDataValidator();



    @Test
    public void todayTest(){
        LocalDateTime date = inputDataValidator.getDate();
        LocalDateTime now = inputDataValidator.getNow();
        MonthCalendar monthCalendar = new MonthCalendar(date, now);

        if (inputDataValidator.validate(new String[]{})){
            assertThat(monthCalendar.isItToday(now), is(true));
        }
    }




}
