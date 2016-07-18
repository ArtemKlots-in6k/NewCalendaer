import calendar.CalendarType;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;

import static calendar.CalendarType.Console;
import static calendar.CalendarType.Html;

/**
 * Created by Artem Klots on 7/6/16.
 */
public class InputArgumentParser {
    public static YearMonth parseDate(String[] args) {
        if (args.length == 0)
            return YearMonth.now();
        if (args.length <= 2)
            return YearMonth.of(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        return YearMonth.now();
    }

    public static CalendarType parseCalendarType(String[] args) throws IOException {
        if (args.length < 2) {
            return Console;
        } else if (args[2].toLowerCase().equals(Console.name().toLowerCase())) {
            return Console;
        } else if (args[2].toLowerCase().equals(Html.name().toLowerCase())) {
            return Html;
        } else throw new IOException();
    }
}


