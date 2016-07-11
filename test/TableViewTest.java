import calendar.InputDataParser;
import calendar.MonthCalendar;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by employee on 7/6/16.
 */
public class TableViewTest {
    private InputDataParser inputDataParser = new InputDataParser();
    private static final String NEW_LINE_SYMBOL = "\\n";
    private static final String WEEK_END_DAY_COLOR = "31;1m";
    private static final String TODAY_COLOR = "[42m";

    @Test
    public void isWeekStartFromMondayTest() {
        assertThat(MonthCalendar.getDaysOfWeek(), startsWith("Mon"));
    }

    @Test
    public void isFirstRowIsMonth() {
        LocalDate date = LocalDate.of(2016, 7, 7);
        MonthCalendar monthCalendar = new MonthCalendar(date);

        String[] rows = monthCalendar.getCalendar().toString().split(NEW_LINE_SYMBOL);
        assertThat(rows[0], containsString("JULY"));
    }

    @Test
    public void isSecondRowIsDaysOfWeek() {
        LocalDate date = LocalDate.of(2016, 7, 7);
        MonthCalendar monthCalendar = new MonthCalendar(date);

        String[] rows = monthCalendar.getCalendar().toString().split(NEW_LINE_SYMBOL);
        assertThat(rows[1], containsString("Mon  Tue  Wen  Thu  Fri  Sut  Sun"));
    }

    @Test
    public void isLastDayOfWeekInRed() {
        LocalDate date = LocalDate.of(2016, 7, 7);
        MonthCalendar monthCalendar = new MonthCalendar(date);

        String[] rows = monthCalendar.getCalendar().toString().split(NEW_LINE_SYMBOL);
        String[] columns = rows[3].split("   ");

        //проверяет есть ли в 6 колонке символы для закраски седьмой колонки
        assertThat(columns[6], containsString(WEEK_END_DAY_COLOR));
    }

    // TODO: 7/11/16 Избавится от привязки к "сегодня", написанием метода для установки сегоднящней даты
    @Ignore
    @Test
    public void isTodayInGreen() {
        LocalDate date = LocalDate.now();
        MonthCalendar monthCalendar = new MonthCalendar(date);
        //replacing in text row needs because of different lengths of spaces in numbers with 1 and 2 symbols length
        String calendarInStringWithoutSpaces = monthCalendar.getCalendar().toString().replace(" ","");
        assertThat(calendarInStringWithoutSpaces, is(containsString(TODAY_COLOR + (date.getDayOfMonth()))));
    }

    @Test
    public void isLastDaysOfPreviousMonthIsMissed() {
        LocalDate date = LocalDate.of(2016, 7, 1);
        MonthCalendar monthCalendar = new MonthCalendar(date);

        String[] rows = monthCalendar.getCalendar().toString().split(NEW_LINE_SYMBOL);
        String previousDays = rows[2].substring(0, rows[2].indexOf("1"));
        assertThat(previousDays, not(containsString("30")));
    }

    @Test
    public void isFirstDaysOfNextMonthIsMissed() {
        LocalDate date = LocalDate.of(2016, 6, 1);
        MonthCalendar monthCalendar = new MonthCalendar(date);

        String[] rows = monthCalendar.getCalendar().toString().split(NEW_LINE_SYMBOL);
        String previousDays = rows[6].substring(rows[6].indexOf("30"));
        assertThat(previousDays, not(containsString("1")));
    }


    @Test
    public void isCalendarInTableFormat() {
        LocalDate date = LocalDate.of(2016, 7, 1);
        MonthCalendar monthCalendar = new MonthCalendar(date);

        String[] rows = monthCalendar.getCalendar().toString().split(NEW_LINE_SYMBOL + "   ");//empty symbols needs for correct splits in several situations
        String[] columns = rows[4].split("   "); //empty symbols is the spaces between numbers (columns)
        assertThat(columns.length, is(7));
    }
}
