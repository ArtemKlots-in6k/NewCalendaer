import calendar.Calendar;
import calendar.Controller;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static calendar.Interactive.Command.*;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


/**
 * Created by Artem Klots on 17.07.2016.
 */


public class ControllerTest implements Calendar {
    private Controller controller;
    private YearMonth yearMonth = YearMonth.of(2016, 7);
    private List<YearMonth> monthCalls = new ArrayList<>();

    @Override
    public String generate(YearMonth yearMonth) throws IOException {
        monthCalls.add(yearMonth);
        return yearMonth.toString();
    }

    @Before
    public void setUp() throws Exception {
        controller = new Controller(this, yearMonth);
    }

    @Test
    public void next() throws Exception {
        assertThat(controller.handleCommand(D),
                containsString(yearMonth.plusMonths(1).toString()));
    }

    @Test
    public void twoTimesNext() throws Exception {
        controller.handleCommand(D);
        assertThat(controller.handleCommand(D),
                containsString(yearMonth.plusMonths(2).toString()));
    }

    @Test
    public void previous() throws Exception {
        assertThat(controller.handleCommand(A),
                containsString(yearMonth.minusMonths(1).toString()));
    }

    @Test
    public void twoTimesPrevious() throws Exception {
        controller.handleCommand(A);
        assertThat(controller.handleCommand(A),
                containsString(yearMonth.minusMonths(2).toString()));
    }

    @Test
    public void increase() throws Exception {
        controller.handleCommand(W);
        assertThat(monthCalls, is(monthsFromYear()));
    }

    private List<YearMonth> monthsFromYear() {
        List<YearMonth> months = new LinkedList<>();
        for (Month month:Month.values()) {
            months.add(YearMonth.of(yearMonth.getYear(),month));
        }
        return months;
    }

    @Test
    public void decrease() throws Exception {
        controller.handleCommand(S);
        List<YearMonth> expectCalls = singletonList(yearMonth);
        assertThat(monthCalls, is(expectCalls));
    }
}

