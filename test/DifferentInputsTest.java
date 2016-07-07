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

    //Done
    @Test
    public void todayTest(){
        LocalDateTime date = LocalDateTime.now();
        MonthCalendar monthCalendar = new MonthCalendar(date);
            assertThat(monthCalendar.isItToday(LocalDateTime.now()), is(true));
    }

}
