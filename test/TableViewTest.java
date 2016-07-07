import calendar.InputDataValidator;
import calendar.MonthCalendar;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by employee on 7/6/16.
 */
public class TableViewTest {
    private InputDataValidator inputDataValidator = new InputDataValidator();

    //Done
    @Test
    public void isWeekStartFromMondayTest() {
        assertThat(MonthCalendar.getDaysOfWeek(), startsWith("Mon"));
    }

    @Test
    public void isFirstRowIsMonth() {
        LocalDateTime date = LocalDateTime.of(2016, 7, 7, 1, 1);
        MonthCalendar monthCalendar = new MonthCalendar(date);

        String[] rows = monthCalendar.getCalendar().toString().split("\\n");
        assertThat(rows[0], containsString(date.getMonth().toString()));
    }

    //done
    @Test
    public void isSecondRowIsDaysOfWeek() {
        LocalDateTime date = LocalDateTime.of(2016, 7, 7, 1, 1);
        MonthCalendar monthCalendar = new MonthCalendar(date);

        String[] rows = monthCalendar.getCalendar().toString().split("\\n");
        assertThat(rows[1], containsString(MonthCalendar.getDaysOfWeek()));
    }

    @Test
    public void isLastDaysOfWeekInRed() {
        LocalDateTime date = LocalDateTime.of(2016, 7, 7, 1, 1);
        MonthCalendar monthCalendar = new MonthCalendar(date);

        String[] rows = monthCalendar.getCalendar().toString().split("\\n");
        String[] columns = rows[3].split("   ");

        //проверяет есть ли в 6 колонке символы для закраски седьмой колонки
        assertThat(columns[6], containsString("31;1m"));
    }

    @Test
    public void isTodayInGreen() {
        int day = 7;
        LocalDateTime date = LocalDateTime.of(2016, 7, day, 1, 1);
        MonthCalendar monthCalendar = new MonthCalendar(date);
        String calendarInString = monthCalendar.getCalendar().toString();

        assertThat(calendarInString, containsString(day-1 + "\u001B[42m"));
    }

    // TODO: 7/7/16 Теста нет. Сделать!
    @Test
    public void isHereIsLastDaysOfPreviousMonth() {
        LocalDateTime date = LocalDateTime.of(2016, 7, 7, 1, 1);
        MonthCalendar monthCalendar = new MonthCalendar(date);

        String[] rows = monthCalendar.getCalendar().toString().split("\\n");
        String[] columns = rows[2].split("   ");


//        assertThat(columns[6], containsString("31;1m"));

    }



    //// TODO: 7/7/16 Переделать
    @Test
    public void isCalendarInTableFormat() {
        LocalDateTime date = LocalDateTime.of(2016, 7, 9, 1, 1);
        MonthCalendar monthCalendar = new MonthCalendar(date);
        StringBuilder calendar = monthCalendar.getCalendar();

        int lineCounter = 0;

        for (int i = 0; i < calendar.length(); i++) {
            if (calendar.substring(i, i + 1).matches("\\n")) {
                lineCounter++;
                System.out.println(calendar.substring(i,i+1));
            }
        }
        assertThat(lineCounter, is(7));
    }












}
