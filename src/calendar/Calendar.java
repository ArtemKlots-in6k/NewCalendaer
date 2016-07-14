package calendar;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;

import static java.time.DayOfWeek.*;

/**
 * Created by Artem Klots on 7/13/16.
 */

public abstract class Calendar {
    private List<DayOfWeek> weekendDays = Arrays.asList(SATURDAY, SUNDAY);
    private LocalDate today;
    private Month month;
    private LocalDate firstDayOfMonth;
    private StringBuilder stringBuilder = new StringBuilder("");

    Calendar(LocalDate today, Month month) {
        this.month = month;
        this.today = today;
        this.firstDayOfMonth = LocalDate.of(today.getYear(), month, 1);
    }

    abstract void renderHeader(Month month);

    abstract void renderFooter();

    abstract void renderEmptyDay();

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
        renderHeader(month);
        generateEmptyDaysInsteadDaysFromPreviousMonth();
        renderBody(lastDayOfMonth);
        renderFooter();
        return stringBuilder.toString();
    }

    private void generateEmptyDaysInsteadDaysFromPreviousMonth() {
        for (int i = 1; i < countDaysFromPreviousMonth(); i++) {
            renderEmptyDay();
        }
    }

    private void renderBody(int lastDayOfMonth) {
        for (int i = 1; i <= lastDayOfMonth; i++) {
            LocalDate currentDay = firstDayOfMonth.plusDays(i - 1);
            generateDay(currentDay);

            if (isLastDayInWeek(currentDay)) {
                startNewWeek();
            }
        }
    }

    String[] getDaysOfWeek() {
        return new String[]{"Mon", "Tue", "Wen", "Thu", "Fri", "Sut", "Sun"};
    }

    private void generateDay(LocalDate date) {
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
        return date.equals(today) && isThisMonth(date);
    }

    private boolean isThisMonth(LocalDate date) {
        return date.getMonth().equals(month);
    }

    private boolean isWeekend(LocalDate date) {
        return weekendDays.contains(date.getDayOfWeek());
    }

    private boolean isTodayAndWeekend(LocalDate date) {
        return isToday(date) && isWeekend(date);
    }

    public final void printCalendarIntoConsole() throws IOException {
        char[] calendar = this.generateCalendar().toCharArray();
        Arrays.asList(calendar).stream().forEach(System.out::print);
    }

    private int countDaysFromPreviousMonth() {
        return firstDayOfMonth.getDayOfWeek().getValue();
    }

    public void setWeekends(List <DayOfWeek> weekends){
        weekendDays = weekends;
    }


}
