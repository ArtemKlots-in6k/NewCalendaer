import calendar.Interactive.MonthPeriod;
import calendar.Interactive.PeriodForMonth;
import calendar.Interactive.PeriodForYear;
import org.junit.Test;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by Artem Klots on 7/15/16.
 */
public class MonthPeriodTest {
    private final static List<YearMonth> YEAR_OF_2016 = Arrays.asList(
            YearMonth.of(2016, 1), YearMonth.of(2016, 2),
            YearMonth.of(2016, 3), YearMonth.of(2016, 4),
            YearMonth.of(2016, 5), YearMonth.of(2016, 6),
            YearMonth.of(2016, 7), YearMonth.of(2016, 8),
            YearMonth.of(2016, 9), YearMonth.of(2016, 10),
            YearMonth.of(2016, 11), YearMonth.of(2016, 12)
    );
    private final static List<YearMonth> YEAR_OF_2017 = Arrays.asList(
            YearMonth.of(2017, 1), YearMonth.of(2017, 2),
            YearMonth.of(2017, 3), YearMonth.of(2017, 4),
            YearMonth.of(2017, 5), YearMonth.of(2017, 6),
            YearMonth.of(2017, 7), YearMonth.of(2017, 8),
            YearMonth.of(2017, 9), YearMonth.of(2017, 10),
            YearMonth.of(2017, 11), YearMonth.of(2017, 12)
    );

    private final static YearMonth YEAR_MONTH = YearMonth.of(2016, 7);

    @Test
    public void nextMonth() throws Exception {
        MonthPeriod monthPeriod = new PeriodForMonth(YEAR_MONTH);
        assertThat(monthPeriod.next().getMonths(), is(Collections.singletonList(YEAR_MONTH.plusMonths(1))));
    }

    @Test
    public void nextYear() throws Exception {
        MonthPeriod monthPeriod = new PeriodForYear(YEAR_MONTH);
        assertThat(monthPeriod.next().getMonths(), is(YEAR_OF_2017));
    }

    @Test
    public void nextMonthUsedTwoTimes() throws Exception {
        MonthPeriod monthPeriod = new PeriodForMonth(YEAR_MONTH);
        monthPeriod = monthPeriod.next();
        assertThat(monthPeriod.next().getMonths(), is(Collections.singletonList(YEAR_MONTH.plusMonths(2))));
    }

    @Test
    public void previousMonth() throws Exception {
        MonthPeriod monthPeriod = new PeriodForMonth(YearMonth.of(2016, 8));
        assertThat(monthPeriod.previous().getMonths(), is(Collections.singletonList(YEAR_MONTH)));
    }

    @Test
    public void previousYear() throws Exception {
        MonthPeriod monthPeriod = new PeriodForYear(YEAR_MONTH.plusYears(1));
        assertThat(monthPeriod.previous().getMonths(), is(YEAR_OF_2016));
    }

    @Test
    public void increase() throws Exception {
        MonthPeriod monthPeriod = new PeriodForMonth(YEAR_MONTH);
        assertThat(monthPeriod.increase().getMonths(), is(YEAR_OF_2016));
    }

    @Test
    public void decrease() throws Exception {
        MonthPeriod monthPeriod = new PeriodForYear(YEAR_MONTH);
        assertThat(monthPeriod.decrease().getMonths(), is(Collections.singletonList(YEAR_MONTH)));
    }

    @Test
    public void getOneMonth() throws Exception {
        MonthPeriod monthPeriod = new PeriodForMonth(YearMonth.of(2016, 8));
        assertThat(monthPeriod.getMonths(), is(Collections.singletonList(YearMonth.of(2016, 8))));
    }

    @Test
    public void getAllMonthsInYear() throws Exception {
        MonthPeriod monthPeriod = new PeriodForYear(YEAR_MONTH);
        assertThat(monthPeriod.getMonths(), is(YEAR_OF_2016));
    }
}
