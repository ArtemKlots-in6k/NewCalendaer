import calendar.InputDataValidator;
import calendar.MonthCalendar;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by employee on 7/6/16.
 */
public class TableViewTest {
    private InputDataValidator inputDataValidator = new InputDataValidator();
    private static final String NEW_LINE_SYMBOL = "\\n";
    private static final String WEEK_END_DAY_COLOR = "31;1m";
    private static final String TODAY_COLOR = "[42m";

    @Test
    public void isWeekStartFromMondayTest() {
        assertThat(MonthCalendar.getDaysOfWeek(), startsWith("Mon"));
    }

    @Test
    public void isFirstRowIsMonth() {
        LocalDateTime date = LocalDateTime.of(2016, 7, 7, 1, 1);
        MonthCalendar monthCalendar = new MonthCalendar(date);

        String[] rows = monthCalendar.getCalendar().toString().split(NEW_LINE_SYMBOL);
        assertThat(rows[0], containsString("JULY"));
    }

    @Test
    public void isSecondRowIsDaysOfWeek() {
        LocalDateTime date = LocalDateTime.of(2016, 7, 7, 1, 1);
        MonthCalendar monthCalendar = new MonthCalendar(date);

        String[] rows = monthCalendar.getCalendar().toString().split(NEW_LINE_SYMBOL);
        assertThat(rows[1], containsString("Mon  Tue  Wen  Thu  Fri  Sut  Sun"));
    }

    @Test
    public void isLastDayOfWeekInRed() {
        LocalDateTime date = LocalDateTime.of(2016, 7, 7, 1, 1);
        MonthCalendar monthCalendar = new MonthCalendar(date);

        String[] rows = monthCalendar.getCalendar().toString().split(NEW_LINE_SYMBOL);
        String[] columns = rows[3].split("   ");

        //проверяет есть ли в 6 колонке символы для закраски седьмой колонки
        assertThat(columns[6], containsString(WEEK_END_DAY_COLOR));
    }

    @Test
    public void isTodayInGreen() {
        LocalDateTime date = LocalDateTime.now();
        MonthCalendar monthCalendar = new MonthCalendar(date);
        //replacing in text row needs because of different lengths of spaces in numbers with 1 and 2 symbols length
        String calendarInStringWithoutSpaces = monthCalendar.getCalendar().toString().replace(" ","");
        assertThat(calendarInStringWithoutSpaces, is(containsString(TODAY_COLOR + (date.getDayOfMonth()))));
    }

    @Test
    public void isLastDaysOfPreviousMonthIsMissed() {
        LocalDateTime date = LocalDateTime.of(2016, 7, 1, 1, 1);
        MonthCalendar monthCalendar = new MonthCalendar(date);

        String[] rows = monthCalendar.getCalendar().toString().split(NEW_LINE_SYMBOL);
        String previousDays = rows[2].substring(0, rows[2].indexOf("1"));
        assertThat(previousDays, not(containsString("30")));
    }

    @Test
    public void isFirstDaysOfNextMonthIsMissed() {
        LocalDateTime date = LocalDateTime.of(2016, 6, 1, 1, 1);
        MonthCalendar monthCalendar = new MonthCalendar(date);

        String[] rows = monthCalendar.getCalendar().toString().split(NEW_LINE_SYMBOL);
        String previousDays = rows[6].substring(rows[6].indexOf("30"));
        assertThat(previousDays, not(containsString("1")));
    }


    @Test
    public void isCalendarInTableFormat() {
        LocalDateTime date = LocalDateTime.of(2016, 7, 1, 1, 1);
        MonthCalendar monthCalendar = new MonthCalendar(date);

        String[] rows = monthCalendar.getCalendar().toString().split(NEW_LINE_SYMBOL + "   ");//empty symbols needs for correct splits in several situations
        String[] columns = rows[4].split("   "); //empty symbols is the spaces between numbers (columns)
        assertThat(columns.length, is(7));
    }
}
