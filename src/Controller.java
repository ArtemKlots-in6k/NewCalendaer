import calendar.Calendar;
import calendar.ConsoleCalendar;
import calendar.Interactive.Command;
import calendar.Interactive.MonthPeriod;
import calendar.Interactive.PeriodForMonth;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

/**
 * Created by Artem Klots on 17.07.2016.
 */

public class Controller {
    private Calendar calendar;
    private MonthPeriod monthPeriod;

    public Controller(LocalDate today, YearMonth yearMonth) {
        calendar = new ConsoleCalendar(today, yearMonth);
        monthPeriod = new PeriodForMonth(yearMonth);
    }

    public Controller(Calendar calendar, YearMonth yearMonth) {
        this.calendar = calendar;
        monthPeriod = new PeriodForMonth(yearMonth);
    }

    public String handleCommand(Command command) throws IOException {
        useCommand(command);
        return generateOutput(monthPeriod.getMonths());
    }

    private void useCommand(Command command) throws IOException {
        switch (command) {
            case D:
                this.monthPeriod = monthPeriod.next();
                break;

            case A:
                this.monthPeriod = monthPeriod.previous();
                break;

            case W: //увеличить период
                this.monthPeriod = monthPeriod.increase();
                break;

            case S:
                this.monthPeriod = monthPeriod.decrease();
                break;

            default:
                break;
        }
    }

    private String generateOutput(List<YearMonth> period) throws IOException {
        String output = "";
        for (YearMonth yearMonth : period) {
            output += calendar.generate(yearMonth);
        }
        return output;
    }
}
