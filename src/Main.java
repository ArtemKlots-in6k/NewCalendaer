import calendar.InputDataParser;
import calendar.MonthCalendar;

/**
 * Created by employee on 7/6/16.
 */
public class Main {
    public static void main(String[] args) {
        InputDataParser inputDataParser = new InputDataParser();
            MonthCalendar monthCalendar = new MonthCalendar(inputDataParser.getCurrentDate());
            monthCalendar.printCalendar();
    }
}
