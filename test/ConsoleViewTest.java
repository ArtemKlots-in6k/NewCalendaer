import calendar.ConsoleCalendar;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by Artem Klots on 7/6/16.
 */
public class ConsoleViewTest {
    private static final String NEW_LINE_SYMBOL = "\\n";
    private static final String WEEKEND_DAY_COLOR = "\033[31;1m";
    private static final String TODAY_COLOR = (char) 27 + "[42m";

    @Test
    public void isWeekStartFromMondayTest() throws IOException {
        LocalDate date = LocalDate.of(2016, 7, 7);
        ConsoleCalendar monthCalendar = new ConsoleCalendar(date, YearMonth.of(date.getYear(), date.getMonth()));
        String[] rows = monthCalendar.generate(YearMonth.of(2016, 7)).split(NEW_LINE_SYMBOL);
        assertThat(rows[2], startsWith(String.format("%5s", "Mon")));
    }

    @Test
    public void isFirstRowIsMonth() throws IOException {
        LocalDate date = LocalDate.of(2016, 7, 7);
        ConsoleCalendar monthCalendar = new ConsoleCalendar(date, YearMonth.of(date.getYear(), date.getMonth()));

        String[] rows = monthCalendar.generate(YearMonth.of(2016, 7)).split(NEW_LINE_SYMBOL);
        assertThat(rows[1], containsString("JULY"));
    }


    @Test
    public void isSecondRowIsDaysOfWeek() throws IOException {
        LocalDate date = LocalDate.of(2016, 7, 7);
        ConsoleCalendar monthCalendar = new ConsoleCalendar(date, YearMonth.of(date.getYear(), date.getMonth()));

        String[] rows = monthCalendar.generate(YearMonth.of(2016, 7)).split(NEW_LINE_SYMBOL);
        assertThat(rows[2], containsString("Thu"));
    }

    @Test
    public void isLastDayOfWeekInRed() throws IOException {
        LocalDate date = LocalDate.of(2016, 7, 7);
        ConsoleCalendar monthCalendar = new ConsoleCalendar(date, YearMonth.of(date.getYear(), date.getMonth()));
        String[] rows = monthCalendar.generate(YearMonth.of(2016, 7)).split(NEW_LINE_SYMBOL);

        //проверяет есть ли в 6 колонке символы для закраски седьмой колонки
        assertThat(rows[3].replace(" ", ""), containsString(WEEKEND_DAY_COLOR + "3"));
    }

    @Test
    public void isTodayInGreen() throws IOException {
        LocalDate date = LocalDate.of(2016, 7, 7);
        ConsoleCalendar monthCalendar = new ConsoleCalendar(date, YearMonth.of(date.getYear(), date.getMonth()));


//        monthCalendar.setNow(date);


        //replacing in text row needs because of different lengths of spaces in numbers with 1 and 2 symbols length
        String calendarInStringWithoutSpaces = monthCalendar.generate(YearMonth.of(2016, 7)).replace(" ", "");
        assertThat(calendarInStringWithoutSpaces, is(containsString(TODAY_COLOR + (date.getDayOfMonth()))));
    }

    @Test
    public void isLastDaysOfPreviousMonthIsMissed() throws IOException {
        LocalDate date = LocalDate.of(2016, 7, 1);
        ConsoleCalendar monthCalendar = new ConsoleCalendar(date, YearMonth.of(date.getYear(), date.getMonth()));

        String[] rows = monthCalendar.generate(YearMonth.of(2016, 7)).split(NEW_LINE_SYMBOL);
        String previousDays = rows[3].substring(0, rows[3].indexOf("1"));
        assertThat(previousDays, not(containsString("30")));
    }

    @Test
    public void isFirstDaysOfNextMonthIsMissed() throws IOException {
        LocalDate date = LocalDate.of(2016, 6, 1);
        ConsoleCalendar monthCalendar = new ConsoleCalendar(date, YearMonth.of(date.getYear(), date.getMonth()));

        String[] rows = monthCalendar.generate(YearMonth.of(2016, 7)).split(NEW_LINE_SYMBOL);
        String previousDays = rows[7].substring(rows[7].indexOf("30"));
        assertThat(previousDays, not(containsString(" 1 ")));
    }

    @Test
    public void isCalendarInTableFormat() throws IOException {
        LocalDate date = LocalDate.of(2016, 7, 1);
        ConsoleCalendar monthCalendar = new ConsoleCalendar(date, YearMonth.of(date.getYear(), date.getMonth()));

        String[] rows = monthCalendar.generate(YearMonth.of(2016, 7)).split(NEW_LINE_SYMBOL + "   ");//empty symbols needs for correct splits in several situations
        String[] columns = rows[4].split("   "); //empty symbols is the spaces between numbers (columns)
        assertThat(columns.length, is(7));
    }

    @Test
    public void isTodayWeekdayInGreen() throws IOException {
        LocalDate date = LocalDate.of(2016, 7, 16);
        ConsoleCalendar monthCalendar = new ConsoleCalendar(date, YearMonth.of(date.getYear(), date.getMonth()));

        //replacing in text row needs because of different lengths of spaces in numbers with 1 and 2 symbols length
        String calendarWithoutSpaces = monthCalendar.generate(YearMonth.of(2016, 7)).replace(" ", "");
        assertThat(calendarWithoutSpaces, is(containsString(WEEKEND_DAY_COLOR +
                TODAY_COLOR +
                (date.getDayOfMonth()))));
    }
}
