package calendar;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.function.Supplier;

/**
 * Created by Artem Klots on 7/15/16.
 */
public interface Calendar {
    String generateCalendar(YearMonth yearMonth) throws IOException;
}
