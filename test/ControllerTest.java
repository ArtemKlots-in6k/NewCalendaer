import calendar.Calendar;
import calendar.Controller;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static calendar.Interactive.Command.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import static org.mockito.Mockito.*;


/**
 * Created by Artem Klots on 17.07.2016.
 */


public class ControllerTest implements Calendar {
    private Controller controller;
    private YearMonth yearMonth = YearMonth.of(2016, 7);
    private List<YearMonth> calling = new ArrayList<>();
//    private Calendar calendar;

    @Override
    public String generateCalendar(YearMonth yearMonth) throws IOException {
        calling.add(yearMonth);
        return yearMonth.toString();
    }

    @Before
    public void setUp() throws Exception {
        controller = new Controller(this, yearMonth);
    }

    @Test
    public void next() throws Exception {
        assertThat(controller.handleCommand(D), containsString("2016-08"));
    }

    @Test
    public void twoTimeNext() throws Exception {
        controller.handleCommand(D);
        assertThat(controller.handleCommand(D), containsString("2016-09"));
    }

    @Test
    public void previous() throws Exception {
        assertThat(controller.handleCommand(A), containsString("2016-06"));
    }

    @Test
    public void twoTimePrevious() throws Exception {
        controller.handleCommand(A);
        assertThat(controller.handleCommand(A), containsString("2016-05"));
    }

    @Test
    public void increase() throws Exception {
        controller.handleCommand(W);
        System.out.println(Arrays.toString(calling.toArray()));
//        assertThat(controller.handleCommand(W), containsString("2016-05"));
    }


    /*
    @Before
    public void setUp() throws Exception {
//        calendar = new MonthCalendar(LocalDate.of(2016, 7, 17), yearMonth);
        controller = new Controller(this);
    }

    @Test
    public void next() throws Exception {
//        controller = new Controller(this);
//        calendar = mock(Calendar.class);
//        controller = mock(Controller.class);
//        when(controller.next()).thenReturn("1");
        System.out.println(controller.next());
    }

    @Test
    public void nextWorkingLotsOfTimes() throws Exception {
        controller.next();
        String calendar = controller.next();
        assertThat(calendar, containsString("SEPTEMBER"));
    }


    @Test
    public void previous() throws Exception {
        assertThat(controller.previous(), containsString("JUNE"));
    }

    @Test
    public void previousWorkingLotsOfTimes() throws Exception {
        controller.previous();
        assertThat(controller.previous(), containsString("MAY"));
    }

    @Test
    public void increase() throws Exception {
        assertThat(controller.increase(), both(containsString("JANUARY")).and(containsString("DECEMBER")));
    }


    @Test
    public void decrease() throws Exception {
        assertThat(controller.decrease(), containsString("JULY"));
    }

    @Test
    public void decreaseWorkingLotsOfTimes() throws Exception {
        controller.decrease();
        assertThat(controller.decrease(), containsString("JULY"));
    }


    @Test
    public void increaseAndDecrease() throws Exception {
        controller.increase();
        assertThat(controller.decrease(), containsString("JANUARY"));
    }

*/
}

