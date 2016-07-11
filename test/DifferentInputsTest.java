import calendar.InputDataParser;
import calendar.MonthCalendar;
import org.junit.Ignore;
import org.junit.Test;

import java.security.InvalidParameterException;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by employee on 7/6/16.
 */
public class DifferentInputsTest {
    private InputDataParser inputDataParser = new InputDataParser();

    @Ignore
    @Test
    public void todayTest() {
        LocalDate date = LocalDate.now();
        MonthCalendar monthCalendar = new MonthCalendar(date);
        assertThat(monthCalendar.isItToday(LocalDate.now()), is(true));
    }

    @Test
    public void parseData() {
        InputDataParser inputDataParser = new InputDataParser();
        assertThat(inputDataParser.parse(new String[]{"07", "2016"}), is(LocalDate.of(2016, 07, 1)));
    }

    @Test(expected = InvalidParameterException.class)
    public void parseDataWithOneParameter() {
        InputDataParser inputDataParser = new InputDataParser();
        inputDataParser.parse(new String[]{"07"});
    }

    @Test(expected = InvalidParameterException.class)
    public void parseDataWithThreeParameter() {
        InputDataParser inputDataParser = new InputDataParser();
        inputDataParser.parse(new String[]{"07", "2016", "123"});
    }

    @Test(expected = InvalidParameterException.class)
    public void parseDataWithWrongTypeOfParameters() {
        InputDataParser inputDataParser = new InputDataParser();
        inputDataParser.parse(new String[]{"mm", "2016"});
    }

    @Test
    public void parseDataWithoutArguments() {
        InputDataParser inputDataParser = new InputDataParser();
        assertThat(inputDataParser.parse(new String[]{}), is(LocalDate.now()));
    }

}
