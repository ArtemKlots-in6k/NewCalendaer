import calendar.HtmlCalendar;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.YearMonth;

/**
 * Created by Artem Klots on 7/13/16.
 */
public class HtmlViewTest {
    private static final String WEEKEND_CSS_CLASS = "weekend-day";
    private static final String TODAY_CSS_CLASS = "today-day";
    private HtmlCalendar htmlCalendar;

    @Test
    public void firstRowIsMonth() throws Exception {
        htmlCalendar = new HtmlCalendar(LocalDate.of(2016, 7, 1), YearMonth.now());
        String[] days = htmlCalendar.generateCalendar(YearMonth.of(2016, 7)).split("/n");
        assertThat(days[0], containsString("JULY"));
    }

    @Test
    public void secondRowIsDaysOfWeek() throws Exception {
        htmlCalendar = new HtmlCalendar(LocalDate.of(2016, 7, 1), YearMonth.now());
        String[] days = htmlCalendar.generateCalendar(YearMonth.of(2016, 7)).split("/tr");
        assertThat(days[1], containsString("Sun"));
    }

    @Test
    public void isTodayDayWasPrinted() throws Exception {
        htmlCalendar = new HtmlCalendar(LocalDate.of(2016, 7, 3), YearMonth.now());
        assertThat(htmlCalendar.generateCalendar(YearMonth.of(2016, 7)), containsString("3"));
    }

    @Test
    public void isWeekendInNeededColor() throws Exception {
        htmlCalendar = new HtmlCalendar(LocalDate.of(2016, 7, 8), YearMonth.now());
        assertThat(htmlCalendar.generateCalendar(YearMonth.of(2016, 7)),
                containsString("<td class=\"" + WEEKEND_CSS_CLASS + "\">3</td>"));
    }

    @Test
    public void isTodayInNeededColor() throws Exception {
        htmlCalendar = new HtmlCalendar(LocalDate.of(2016, 7, 8), YearMonth.now());
        htmlCalendar.generateHtmlFile(YearMonth.of(2016, 7));
        assertThat(htmlCalendar.generateCalendar(YearMonth.of(2016, 7)),
                containsString("<td class=\"" + TODAY_CSS_CLASS + "\">8</td>"));
    }

    @Test
    public void isTodayWeekendInTodayAndWeekendColors() throws Exception {
        htmlCalendar = new HtmlCalendar(LocalDate.of(2016, 7, 3), YearMonth.now());
        assertThat(htmlCalendar.generateCalendar(YearMonth.of(2016, 7)),
                containsString("<td class=\"" + TODAY_CSS_CLASS + " " + WEEKEND_CSS_CLASS + "\">3</td>"));
    }

    @Test
    public void changeDate() throws Exception {
        HtmlCalendar htmlCalendar = new HtmlCalendar(LocalDate::now, YearMonth.now());
        htmlCalendar.setToday(LocalDate.of(2016, 7, 1));
        htmlCalendar.generateCalendar(YearMonth.of(2016, 7));
    }

    @Test
    public void setFirstDayOfWeek() {
        htmlCalendar = new HtmlCalendar(LocalDate.of(2016, 7, 3), YearMonth.now());

    }
}
