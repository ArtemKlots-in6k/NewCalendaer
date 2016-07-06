import calendar.InputDataValidator;
import calendar.MonthCalendar;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;

/**
 * Created by employee on 7/6/16.
 */
public class TableViewTest {
    private InputDataValidator inputDataValidator = new InputDataValidator();

    //Done
    @Test
    public void isWeekStartFromMondayTest() {
        assertThat(MonthCalendar.getDaysOfWeek(), startsWith("Mon"));
    }

    @Test
    public void isFirstRowIsMonth() {
        inputDataValidator.validate(new String[]{});
        LocalDateTime date = inputDataValidator.getDate();
        LocalDateTime now = inputDataValidator.getNow();
        MonthCalendar monthCalendar = new MonthCalendar(date, now);

        StringBuilder calendar = monthCalendar.getCalendar();

        int positionOfEndingFirstRow = 0;
        //find row with month
        for (int i = 0; i < calendar.length(); i++) {
            if (calendar.substring(i, i + 1).matches("\\n")) {
                if (positionOfEndingFirstRow == 0) {
                    positionOfEndingFirstRow = i;
                }
            }
        }
        assertThat(calendar.substring(0, positionOfEndingFirstRow), containsString(date.getMonth().toString()));
    }

    //done
    @Test
    public void isSecondRowIsDaysOfWeek() {
        inputDataValidator.validate(new String[]{});
        LocalDateTime date = inputDataValidator.getDate();
        LocalDateTime now = inputDataValidator.getNow();
        MonthCalendar monthCalendar = new MonthCalendar(date, now);

        StringBuilder calendar = monthCalendar.getCalendar();

        int positionOfFirstEndl = 0, positionOfSecondEndl = 0;
        //find row with days of week
        for (int i = 0; i < calendar.length(); i++) {
            if (calendar.substring(i, i + 1).matches("\\n")) {
                if (positionOfFirstEndl == 0) {
                    positionOfFirstEndl = i;
                } else {
                    if (positionOfSecondEndl == 0) {
                        positionOfSecondEndl = i;
                    }
                }
            }
        }

        assertThat(calendar.substring(positionOfFirstEndl, positionOfSecondEndl), containsString(MonthCalendar.getDaysOfWeek()));
    }

    @Test
    public void isLastDaysOfWeekInRed() {
        inputDataValidator.validate(new String[]{});
        LocalDateTime date = inputDataValidator.getDate();
        LocalDateTime now = inputDataValidator.getNow();
        MonthCalendar monthCalendar = new MonthCalendar(date, now);

        StringBuilder calendar = monthCalendar.getCalendar();

        int endLineCounters = 0;
        int positionOfTheRowEnd = 0;

        //find holidays
        for (int i = 0; i < calendar.length(); i++) {
            if (calendar.substring(i, i + 1).matches("\\n")) {
                endLineCounters++;
                positionOfTheRowEnd = i;
                if ((endLineCounters > 2) && (positionOfTheRowEnd == i)) {
                    assertThat(calendar.substring(positionOfTheRowEnd - 37, positionOfTheRowEnd), containsString("31;1m"));
                }
            }
        }
    }


}
