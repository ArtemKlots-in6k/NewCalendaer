package calendar.Interactive;

import java.time.YearMonth;
import java.util.List;

/**
 * Created by Artem Klots on 7/15/16.
 */
public abstract class MonthPeriod {
    protected final YearMonth month;

    public MonthPeriod() {
        this(YearMonth.now());
    }

    public MonthPeriod(YearMonth month) {
        this.month = month;
    }

    public abstract MonthPeriod next();

    public abstract MonthPeriod previous();

    public abstract MonthPeriod increase();

    public abstract MonthPeriod decrease();

    public abstract List<YearMonth> getMonths();
}
