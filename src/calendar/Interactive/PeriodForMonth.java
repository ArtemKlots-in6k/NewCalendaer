package calendar.Interactive;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Artem Klots on 7/15/16.
 */
public class PeriodForMonth extends MonthPeriod {

    public PeriodForMonth() {
        super(YearMonth.now());
    }

    public PeriodForMonth(YearMonth month) {
        super(month);
    }

    @Override
    public MonthPeriod next() {
        return new PeriodForMonth(month.plusMonths(1));
    }

    @Override
    public MonthPeriod previous() {
        return new PeriodForMonth(month.minusMonths(1));
    }

    @Override
    public MonthPeriod increase() {
        return new PeriodForYear(month);
    }

    @Override
    public MonthPeriod decrease() {
        return this;
    }

    @Override
    public List<YearMonth> getMonths() {
        return Collections.singletonList(month);
    }
}

