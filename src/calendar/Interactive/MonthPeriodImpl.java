package calendar.Interactive;

import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Artem Klots on 7/15/16.
 */
public class MonthPeriodImpl implements MonthPeriod {
    private final static int MONTH_IN_YEAR = 12;

    private List<YearMonth> yearMonth;

    public MonthPeriodImpl(YearMonth yearMonth) {
        this.yearMonth = Collections.singletonList(yearMonth);
    }

    public MonthPeriodImpl(List<YearMonth> yearMonth) {
        this.yearMonth = yearMonth;
    }


    @Override
    public List<YearMonth> next() {
        return nextPeriod();
    }

    private List<YearMonth> nextPeriod() {
        if (yearMonth.size() == 1) {
            return nextMonth();
        } else {
            return nextYear();
        }
    }

    private List<YearMonth> nextMonth() {
        yearMonth = Collections.singletonList(yearMonth.get(0).plusMonths(1));
        return yearMonth;
    }

    private List<YearMonth> nextYear() {
        List<YearMonth> result = new ArrayList<>();
        for (int i = 0; i < MONTH_IN_YEAR; i++) {
            result.add(yearMonth.get(i).plusYears(1));
        }
        yearMonth = result;
        return yearMonth;
    }

    @Override
    public List<YearMonth> previous() {
        if (yearMonth.size() == 1) {
            return previousMonth();
        } else {
            return previousYear();
        }
    }

    private List<YearMonth> previousMonth() {
        yearMonth = Collections.singletonList(yearMonth.get(0).minusMonths(1));
        return yearMonth;
    }

    private List<YearMonth> previousYear() {
        List<YearMonth> result = new ArrayList<>();
        for (int i = 0; i < MONTH_IN_YEAR; i++) {
            result.add(yearMonth.get(i).minusYears(1));
        }
        yearMonth = result;
        return yearMonth;
    }

    @Override
    public List<YearMonth> increase() {
        if (yearMonth.size() == 1) {
            return generateMonthsOfNextYear(Year.of(yearMonth.get(0).getYear()));
        } else return yearMonth;
    }

    private List<YearMonth> generateMonthsOfNextYear(Year year) {
        List<YearMonth> result = new ArrayList<>();
        for (int i = 1; i <= MONTH_IN_YEAR; i++) {
            result.add(YearMonth.of(year.getValue(), i));
        }
        yearMonth = result;
        return yearMonth;
    }

    @Override
    public List<YearMonth> decrease() {
        if (yearMonth.size() == MONTH_IN_YEAR) {
            yearMonth = Collections.singletonList(yearMonth.get(0));
            return yearMonth;
        } else return yearMonth;
    }

    @Override
    public List<YearMonth> getMonths() {
        return yearMonth;
    }
}
