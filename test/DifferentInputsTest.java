import calendar.InputDataValidator;
import calendar.MonthCalendar;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;


import java.time.LocalDateTime;

/**
 * Created by employee on 7/6/16.
 */
public class DifferentInputsTest {
    private InputDataValidator inputDataValidator = new InputDataValidator();

    @Before()
    public void before(){

    }

    @Test
    public void todayTest(){
        LocalDateTime date = inputDataValidator.getDate();
        LocalDateTime now = inputDataValidator.getNow();
        MonthCalendar monthCalendar = new MonthCalendar(date, now);

        if (inputDataValidator.validate(new String[]{})){
            assertThat(monthCalendar.isItToday(now), is(true));
        }
    }

    //Done
    @Test
    public void isWeekStartFromMondayTest(){
        assertThat(MonthCalendar.getDaysOfWeek(), startsWith("Mon"));
    }

    @Test
    public void isFirstRowIsMonth(){

    }


}
