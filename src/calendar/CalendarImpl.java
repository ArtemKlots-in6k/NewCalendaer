package calendar;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

import static java.time.DayOfWeek.*;

/**
 * Created by Artem Klots on 7/13/16.
 */

public abstract class CalendarImpl implements Calendar {
    private List<DayOfWeek> weekendDays = Arrays.asList(SATURDAY, SUNDAY);
    private DayOfWeek firstDayOfWeek = MONDAY;
    private LocalDate today;
    private YearMonth month;
    private LocalDate firstDayOfMonth;
    private StringBuilder stringBuilder = new StringBuilder("");
    private Supplier<LocalDate> supplier;

    CalendarImpl(LocalDate today, YearMonth yearMonth) {
        //сохранил эту структуру для примера
        supplier = new Supplier<LocalDate>() {
            @Override
            public LocalDate get() {
                return today;
            }
        };
        this.month = yearMonth;
        this.firstDayOfMonth = LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), 1);
    }

    CalendarImpl(YearMonth yearMonth) {
        this(LocalDate::now, yearMonth);
    }

    CalendarImpl(Supplier<LocalDate> supplier, YearMonth yearMonth) {
        this.supplier = supplier;
        this.month = yearMonth;
        this.today = supplier.get();
        this.firstDayOfMonth = LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), 1);
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

    public final String generate(YearMonth month) throws IOException {
        this.today = supplier.get();
        setSettings(month);
        int lastDayOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
        append("\n");
        renderHeader(month.getMonth());
        generateEmptyDaysInsteadDaysFromPreviousMonth();
        renderBody(lastDayOfMonth);
        renderFooter();
        return stringBuilder.toString();
    }

    private void setSettings(YearMonth month) {
        stringBuilder = new StringBuilder();
        this.month = month;
        this.firstDayOfMonth = month.atDay(1);
    }

    private void generateEmptyDaysInsteadDaysFromPreviousMonth() {
        for (int i = 1; i < countDaysFromPreviousMonth(); i++) {
            if (i >= firstDayOfWeek.getValue()) {
                renderEmptyDay();
            }
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

    List<String> getDaysOfWeek() {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            result.add(i - 1, firstDayOfWeek.plus(i - 1).getDisplayName(TextStyle.SHORT, new Locale("en")));
        }
        return result;
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
        return date.getDayOfWeek() == firstDayOfWeek.minus(1);
    }

    private boolean isToday(LocalDate date) {
        return date.equals(today) && isThisMonth(date);
    }

    private boolean isThisMonth(LocalDate date) {
        return date.getMonth().equals(month.getMonth());
    }

    private boolean isWeekend(LocalDate date) {
        return weekendDays.contains(date.getDayOfWeek());
    }

    private boolean isTodayAndWeekend(LocalDate date) {
        return isToday(date) && isWeekend(date);
    }

    private int countDaysFromPreviousMonth() {
        return firstDayOfMonth.getDayOfWeek().getValue();
    }

    public void setWeekends(List<DayOfWeek> weekends) {
        weekendDays = weekends;
    }

    public void setFirstDayOfWeek(DayOfWeek firstDayOfWeek) {
        this.firstDayOfWeek = firstDayOfWeek;
    }

    public void setToday(final LocalDate today) {
        supplier = () -> today;
    }

    public void setToday(Supplier<LocalDate> supplier) {
        this.supplier = supplier;
    }
}
