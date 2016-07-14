import calendar.HtmlCalendar;
import calendar.MonthCalendar;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

/**
 * Created by Artem Klots on 7/6/16.
 */
public class Main {
    public static void main(String[] args) throws IOException {
//        InputDataParser inputDataParser = new InputDataParser();
//        MonthCalendar monthCalendar = new MonthCalendar(inputDataParser.parse(args));
//        monthCalendar.printCalendar();

        HtmlCalendar htmlCalendar = new HtmlCalendar(LocalDate.now(), LocalDate.now().getMonth());
//        htmlCalendar.generateCalendar();
        htmlCalendar.generateHtmlFile();
    }
}
