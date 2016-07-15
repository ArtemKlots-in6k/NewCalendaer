import calendar.Calendar;
import calendar.HtmlCalendar;
import calendar.MonthCalendar;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


import java.time.LocalDate;
import java.time.YearMonth;

/**
 * Created by employee on 7/15/16.
 */
public class ChangeDateTest {

    @Test
    public void currentDayDate() throws Exception {
        HtmlCalendar htmlCalendar = new HtmlCalendar(LocalDate.of(2016, 7, 5), YearMonth.now());
        assertThat(htmlCalendar.generateCalendar(YearMonth.of(2016, 7)), containsString("<td class=\"today-day\">5</td>"));
    }

    @Test
    public void changeDate() throws Exception {
        HtmlCalendar htmlCalendar = new HtmlCalendar(LocalDate.of(2016, 7, 5), YearMonth.now());
        htmlCalendar.setToday(LocalDate.of(2016, 7, 1));
        assertThat(htmlCalendar.generateCalendar(YearMonth.of(2016, 7)), containsString("<td class=\"today-day\">1</td>"));
    }

    @Test
    public void interfaceTest() throws Exception {
        Calendar calendar = new MonthCalendar(LocalDate.of(2016, 7, 5), YearMonth.of(2016, 7));
        assertThat(calendar.generateCalendar(YearMonth.of(2016, 7)), containsString("\u001B[42m    5"));
    }
}
