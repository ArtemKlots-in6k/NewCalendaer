import calendar.MonthCalendar;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by Artem Klots on 7/6/16.
 */
public class TableViewTest {
    private static final String NEW_LINE_SYMBOL = "\\n";
    private static final String WEEKEND_DAY_COLOR = "\033[31;1m";
    private static final String TODAY_COLOR = (char) 27 + "[42m";

    @Test
    public void isWeekStartFromMondayTest() throws IOException {
        LocalDate date = LocalDate.of(2016, 7, 7);
        MonthCalendar monthCalendar = new MonthCalendar(date, YearMonth.of(date.getYear(), date.getMonth()));
        String[] rows = monthCalendar.generateCalendar().split(NEW_LINE_SYMBOL);
        assertThat(rows[1], startsWith(String.format("%5s", "Mon")));
    }

    @Test
    public void isFirstRowIsMonth() throws IOException {
        LocalDate date = LocalDate.of(2016, 7, 7);
        MonthCalendar monthCalendar = new MonthCalendar(date, YearMonth.of(date.getYear(), date.getMonth()));

        String[] rows = monthCalendar.generateCalendar().split(NEW_LINE_SYMBOL);
        assertThat(rows[0], containsString("JULY"));
    }

    @Test
    public void isSecondRowIsDaysOfWeek() throws IOException {
        LocalDate date = LocalDate.of(2016, 7, 7);
        MonthCalendar monthCalendar = new MonthCalendar(date, YearMonth.of(date.getYear(), date.getMonth()));

        String[] rows = monthCalendar.generateCalendar().split(NEW_LINE_SYMBOL);
        assertThat(rows[1], containsString("Mon  Tue  Wen  Thu  Fri  Sut  Sun"));
    }

    @Test
    public void isLastDayOfWeekInRed() throws IOException {
        LocalDate date = LocalDate.of(2016, 7, 7);
        MonthCalendar monthCalendar = new MonthCalendar(date, YearMonth.of(date.getYear(), date.getMonth()));

        String[] rows = monthCalendar.generateCalendar().split(NEW_LINE_SYMBOL);
        String[] columns = rows[3].split("   ");

        //проверяет есть ли в 6 колонке символы для закраски седьмой колонки
        assertThat(columns[6], containsString(WEEKEND_DAY_COLOR));
    }

    @Test
    public void isTodayInGreen() throws IOException {
        LocalDate date = LocalDate.of(2016, 7, 7);
        MonthCalendar monthCalendar = new MonthCalendar(date, YearMonth.of(date.getYear(), date.getMonth()));


//        monthCalendar.setNow(date);


        //replacing in text row needs because of different lengths of spaces in numbers with 1 and 2 symbols length
        String calendarInStringWithoutSpaces = monthCalendar.generateCalendar().replace(" ", "");
        assertThat(calendarInStringWithoutSpaces, is(containsString(TODAY_COLOR + (date.getDayOfMonth()))));
    }

    @Test
    public void isLastDaysOfPreviousMonthIsMissed() throws IOException {
        LocalDate date = LocalDate.of(2016, 7, 1);
        MonthCalendar monthCalendar = new MonthCalendar(date, YearMonth.of(date.getYear(), date.getMonth()));

        String[] rows = monthCalendar.generateCalendar().split(NEW_LINE_SYMBOL);
        String previousDays = rows[2].substring(0, rows[2].indexOf("1"));
        assertThat(previousDays, not(containsString("30")));
    }

    @Test
    public void isFirstDaysOfNextMonthIsMissed() throws IOException {
        LocalDate date = LocalDate.of(2016, 6, 1);
        MonthCalendar monthCalendar = new MonthCalendar(date, YearMonth.of(date.getYear(), date.getMonth()));

        String[] rows = monthCalendar.generateCalendar().split(NEW_LINE_SYMBOL);
        String previousDays = rows[6].substring(rows[6].indexOf("30"));
        assertThat(previousDays, not(containsString("1")));
    }

    @Test
    public void isCalendarInTableFormat() throws IOException {
        LocalDate date = LocalDate.of(2016, 7, 1);
        MonthCalendar monthCalendar = new MonthCalendar(date, YearMonth.of(date.getYear(), date.getMonth()));

        String[] rows = monthCalendar.generateCalendar().split(NEW_LINE_SYMBOL + "   ");//empty symbols needs for correct splits in several situations
        String[] columns = rows[4].split("   "); //empty symbols is the spaces between numbers (columns)
        assertThat(columns.length, is(7));
    }

    @Test
    public void isTodayWeekdayInGreen() throws IOException {
        LocalDate date = LocalDate.of(2016, 7, 16);
        MonthCalendar monthCalendar = new MonthCalendar(date, YearMonth.of(date.getYear(), date.getMonth()));


//        monthCalendar.setNow(date);


        //replacing in text row needs because of different lengths of spaces in numbers with 1 and 2 symbols length
        String calendarWithoutSpaces = monthCalendar.generateCalendar().replace(" ", "");
        assertThat(calendarWithoutSpaces, is(containsString(WEEKEND_DAY_COLOR +
                TODAY_COLOR +
                (date.getDayOfMonth()))));
    }
}
