package calendar;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

/**
 * Created by Artem Klots on 04.06.2016.
 */
public class ConsoleCalendar extends CalendarImpl {
    private static final String DAY_FORMAT = "%5s";

    private static final String DEFAULT_TEXT_COLOR = "\033[39;49m";
    private static final String WEEKEND_TEXT_COLOR = "\033[31;1m";
    private static final String TODAY_FULFILLING_COLOR = (char) 27 + "[42m";

    public ConsoleCalendar(LocalDate today, YearMonth yearMonth) {
        super(today, yearMonth);
    }

    @Override
    void renderHeader(Month month) {
        append("\t" + month.toString() + "\n");
        for (String day : super.getDaysOfWeek()) {
            append(String.format(DAY_FORMAT, day));
        }
        append("\n");
    }

    @Override
    void renderFooter() {

    }

    @Override
    void renderEmptyDay() {
        append(String.format(DAY_FORMAT, ""));
    }

    @Override
    void renderWeekend(LocalDate date) {
        append(WEEKEND_TEXT_COLOR + String.format(DAY_FORMAT, +date.getDayOfMonth()));
        append(DEFAULT_TEXT_COLOR);
    }

    @Override
    void renderToday(LocalDate date) {
        append(TODAY_FULFILLING_COLOR + String.format(DAY_FORMAT, +date.getDayOfMonth()));
        append(DEFAULT_TEXT_COLOR);
    }

    @Override
    void renderTodayWeekend(LocalDate date) {
        append(WEEKEND_TEXT_COLOR + TODAY_FULFILLING_COLOR +
                String.format(DAY_FORMAT, date.getDayOfMonth()));
        append(DEFAULT_TEXT_COLOR);
    }

    @Override
    void renderDay(LocalDate date) {
        append(String.format(DAY_FORMAT, date.getDayOfMonth()));
    }

    @Override
    void startNewWeek() {
        append("\n");
    }
}
