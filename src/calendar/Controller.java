package calendar;

import calendar.Interactive.MonthPeriod;
import calendar.Interactive.MonthPeriodImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

/**
 * Created by Artem Klots on 17.07.2016.
 */

public class Controller {
    String output;
    Calendar calendar = new MonthCalendar(LocalDate.now(), YearMonth.now());
    MonthPeriod monthPeriod = new MonthPeriodImpl(YearMonth.now());

    public String next() throws IOException {
        return generateOutput(monthPeriod.next());
    }

    public String previous() throws IOException {
        return output = generateOutput(monthPeriod.previous());
    }

    public String increase() throws IOException {
        return output = generateOutput(monthPeriod.increase());
    }

    public String decrease() throws IOException {
        return output = generateOutput(monthPeriod.decrease());
    }

    private String generateOutput(List<YearMonth> period) throws IOException {
        String output = "";
        for (YearMonth yearMonth : period) {
            output += calendar.generateCalendar(yearMonth);
        }
        return output;
    }


}
