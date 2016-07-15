import calendar.HtmlCalendar;
import calendar.MonthCalendar;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;

import static java.time.DayOfWeek.*;
import static java.time.Month.*;

/**
 * Created by Artem Klots on 7/6/16.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        InputDataParser inputDataParser = new InputDataParser();
//        MonthCalendar monthCalendar = new MonthCalendar(inputDataParser.parse(args));
        MonthCalendar monthCalendar = new MonthCalendar(LocalDate.now(),
                YearMonth.of(LocalDate.now().getYear(), LocalDate.now().getMonth()));
        monthCalendar.setWeekends(Arrays.asList(SATURDAY, SUNDAY));
        monthCalendar.setFirstDayOfWeek(MONDAY);

        monthCalendar.setToday(LocalDate::now);

        System.out.println(monthCalendar.generateCalendar(YearMonth.now()));

//        HtmlCalendar htmlCalendar = new HtmlCalendar(LocalDate.now(), LocalDate.now().getMonth());
//        htmlCalendar.generateCalendar();
//        htmlCalendar.setWeekends(Arrays.asList(TUESDAY));
//        htmlCalendar.generateHtmlFile();

    }
}
