package calendar.Interactive;


import java.time.Month;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Artem Klots on 7/18/16.
 */
public class PeriodForYear extends MonthPeriod {

    public PeriodForYear(YearMonth month) {
        super(month);
    }

    @Override
    public MonthPeriod next() {
        return new PeriodForYear(YearMonth.of(month.getYear(), 1).plusYears(1));
    }

    @Override
    public MonthPeriod previous() {
        return new PeriodForYear(month.minusYears(1));
    }

    @Override
    public MonthPeriod increase() {
        return this;
    }

    @Override
    public MonthPeriod decrease() {
        return new PeriodForMonth(month);
    }

    @Override
    public List<YearMonth> getMonths() {
        return Arrays.stream(Month.values())
                .map(m -> YearMonth.of(month.getYear(), m))
                .collect(Collectors.toList());
    }
}
