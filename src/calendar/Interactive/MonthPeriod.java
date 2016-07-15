package calendar.Interactive;

import java.time.YearMonth;
import java.util.List;

/**
 * Created by Artem Klots on 7/15/16.
 */
public interface MonthPeriod {

    List<YearMonth> next();

    List<YearMonth> previous();

    List<YearMonth> increase();

    List<YearMonth> decrease();

    List<YearMonth> getMonths();
}
