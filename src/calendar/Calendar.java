package calendar;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.function.Supplier;

/**
 * Created by Artem Klots on 7/15/16.
 */
public interface Calendar {

    // TODO: 7/18/16 Переименовать в generate
    String generateCalendar(YearMonth yearMonth) throws IOException;
}
