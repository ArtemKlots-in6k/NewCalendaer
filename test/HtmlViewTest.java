import calendar.HtmlCalendar;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;

/**
 * Created by Artem Klots on 7/13/16.
 */
public class HtmlViewTest {
    private static final String WEEKEND_CSS_CLASS = "weekend-cell";
    private static final String TODAY_CSS_CLASS = "today-cell";
    private HtmlCalendar htmlCalendar;

    @Test
    public void firstRowIsMonth() throws Exception {
        htmlCalendar = new HtmlCalendar(LocalDate.of(2016, 7, 1));
        String[] cells = htmlCalendar.generateCalendar().split("/n");
        assertThat(cells[0], containsString("JULY"));
    }

    @Test
    public void secondRowIsDaysOfWeek() throws Exception {
        htmlCalendar = new HtmlCalendar(LocalDate.of(2016, 7, 1));
        String[] cells = htmlCalendar.generateCalendar().split("/tr");
        assertThat(cells[1], containsString("Sun"));
    }

    @Test
    public void isTodayCellWasPrinted() throws Exception {
        htmlCalendar = new HtmlCalendar(LocalDate.of(2016, 7, 3));
        assertThat(htmlCalendar.generateCalendar(), containsString("3"));
    }

    @Test
    public void isWeekendInNeededColor() throws Exception {
        htmlCalendar = new HtmlCalendar(LocalDate.of(2016, 7, 8));
        assertThat(htmlCalendar.generateCalendar(),
                containsString("<td class=\"" + WEEKEND_CSS_CLASS + "\">3</td>"));
    }

    @Test
    public void isTodayInNeededColor() throws Exception {
        htmlCalendar = new HtmlCalendar(LocalDate.of(2016, 7, 8));
        assertThat(htmlCalendar.generateCalendar(),
                containsString("<td class=\"" + WEEKEND_CSS_CLASS + "\">3</td>"));
    }

    @Test
    public void isTodayWeekendInTodayAndWeekendColors() throws Exception {
        htmlCalendar = new HtmlCalendar(LocalDate.of(2016, 7, 3));
        assertThat(htmlCalendar.generateCalendar(),
                containsString("<td class=\"" + TODAY_CSS_CLASS + " " + WEEKEND_CSS_CLASS + "\">3</td>"));
    }
}
