package calendar;

import java.io.IOException;
import java.time.YearMonth;

/**
 * Created by Artem Klots on 7/15/16.
 */
public interface Calendar {
    String generate(YearMonth yearMonth) throws IOException;
}
