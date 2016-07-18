import org.junit.Test;

import java.io.IOException;
import java.time.YearMonth;

import static calendar.CalendarType.Console;
import static calendar.CalendarType.Html;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Artem Klots on 7/6/16.
 */
public class DifferentInputsTest {

    @Test
    public void parseData() {
        assertThat(InputArgumentParser.parseDate(new String[]{"2016", "07"}), is(YearMonth.of(2016, 7)));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void parseDataWithOneParameter() {
        InputArgumentParser.parseDate(new String[]{"07"});
    }

    @Test(expected = NumberFormatException.class)
    public void parseDataWithWrongTypeOfParameters() {
        InputArgumentParser.parseDate(new String[]{"2016", "mm"});
    }

    @Test
    public void parseConsoleCalendarType() throws Exception {
        assertThat(InputArgumentParser.parseCalendarType(new String[]{"2016", "07", "ConsoLe"}), is(Console));
    }

    @Test
    public void parseHtmlCalendarType() throws Exception {
        assertThat(InputArgumentParser.parseCalendarType(new String[]{"2016", "07", "HtMl"}), is(Html));
    }

    @Test(expected = IOException.class)
    public void parseWrongCalendarType() throws Exception {
        InputArgumentParser.parseCalendarType(new String[]{"2016", "07", "Something"});
    }
}
