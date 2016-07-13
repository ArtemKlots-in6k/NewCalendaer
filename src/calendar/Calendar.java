package calendar;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;

import static java.time.DayOfWeek.*;

/**
 * Created by Artem Klots on 7/13/16.
 */

public abstract class Calendar {
    private static final List<DayOfWeek> WEEKEND_DAYS = Arrays.asList(SATURDAY, SUNDAY);
    private LocalDate today;
    private LocalDate firstDayOfMonth;
    private StringBuilder stringBuilder = new StringBuilder("");

    public Calendar(LocalDate today) {
        this.today = today;
        this.firstDayOfMonth = LocalDate.of(today.getYear(), today.getMonth(), 1);
    }

    abstract void renderHeader(LocalDate firstDayOfMonth);

    abstract void missEmptyCells(LocalDate firstDayOfMonth);

    abstract void renderWeekend(LocalDate date);

    abstract void renderToday(LocalDate date);

    abstract void renderTodayWeekend(LocalDate date);

    abstract void renderDay(LocalDate date);

    abstract void startNewWeek();

    final void append(String string) {
        stringBuilder.append(string);
    }

    public final String generateCalendar() throws IOException {
        int lastDayOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
        renderHeader(firstDayOfMonth);
        missEmptyCells(firstDayOfMonth);
        renderBody(lastDayOfMonth);
        return stringBuilder.toString();
    }

    private void renderBody(int lastDayOfMonth) {
        for (int i = 1; i <= lastDayOfMonth; i++) {
            LocalDate currentDay = firstDayOfMonth.plusDays(i - 1);
            generateCell(currentDay);

            if (isLastDayInWeek(currentDay)) {
                startNewWeek();
            }
        }
    }

    String[] getDaysOfWeek() {
        return new String[]{"Mon", "Tue", "Wen", "Thu", "Fri", "Sut", "Sun"};
    }

    private void generateCell(LocalDate date) {
        if (isTodayAndWeekend(date)) {
            renderTodayWeekend(date);

        } else if (isWeekend(date)) {
            renderWeekend(date);

        } else if (isToday(date)) {
            renderToday(date);

        } else {
            renderDay(date);
        }
    }

    private boolean isLastDayInWeek(LocalDate date) {
        return date.getDayOfWeek() == SUNDAY;
    }

    private boolean isToday(LocalDate date) {
        return date.equals(today);
    }

    private boolean isWeekend(LocalDate date) {
        return WEEKEND_DAYS.contains(date.getDayOfWeek());
    }

    private boolean isTodayAndWeekend(LocalDate date) {
        return isToday(date) && isWeekend(date);
    }

    public final void printCalendar() throws IOException {
        char[] calendar = this.generateCalendar().toCharArray();
        Arrays.asList(calendar).stream().forEach(System.out::print);
    }
}
