import calendar.Calendar;
import calendar.CalendarImpl;
import calendar.ConsoleCalendar;
import calendar.HtmlCalendar;
import designer.ConsoleDesigner;
import designer.Designer;
import designer.HtmlDesigner;
import org.junit.Test;

import java.time.LocalDate;
import java.time.YearMonth;

import static calendar.Interactive.Command.D;
import static calendar.Interactive.Command.W;

/**
 * Created by Artem Klots on 19.07.2016.
 */
public class DesignerTest {
    static Calendar calendar = null;
    static Controller controller;

    @Test
    public void testName() throws Exception {
        Designer designer = new ConsoleDesigner();
        calendar = new ConsoleCalendar(LocalDate.now(), YearMonth.now());
        controller = new Controller(calendar, YearMonth.now());
        designer.redesignCalendar(controller.handleCommand(W));

    }


}
