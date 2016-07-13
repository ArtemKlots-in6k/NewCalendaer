package calendar;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;

/**
 * Created by Artem Klots on 04.06.2016.
 */
public class MonthCalendar {
    private LocalDate firstDayOfMonth;
    private LocalDate now;
    private StringBuilder stringBuilder = new StringBuilder("");

    private static final String DEFAULT_TEXT_COLOR = "\033[39;49m";
    private static final String WEEKEND_TEXT_COLOR = "\033[31;1m";
    private static final String TODAY_FULFILLING_COLOR = (char) 27 + "[42m";

    private void addFormattedData(int data) {
            stringBuilder.append(String.format("%5s",data));
    }

    private void addData(String data) {
        stringBuilder.append(data);
    }

    /**
     * This function checks if selected day is today.
     *
     * @param currentDay - verifiable data.
     * @return - is it today, or not.
     */
    public boolean isItToday(LocalDate currentDay) {
        return (currentDay.getDayOfMonth() == now.getDayOfMonth())
                && (currentDay.getYear() == now.getYear() && (currentDay.getMonth() == now.getMonth()));
    }

    public static String getDaysOfWeek() {
        return "Mon  Tue  Wen  Thu  Fri  Sut  Sun";
    }

    /**
     * This method writes title of month and days of week into the top of calendar, and empty cells at the beginning of month.
     */
    private void generateCalendarHeader() {
        addData("\t\t\t\t" + firstDayOfMonth.getMonth() + "\n");
        addData("  " + getDaysOfWeek() + "\n");

        for (int i = 1; i < firstDayOfMonth.with(TemporalAdjusters.firstDayOfMonth()).getDayOfWeek().getValue(); i++)
            addData("     ");
    }

    /**
     * This method writes all days of month.
     */
    private void generateCalendarBodyPart() {
        int lastDayOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();

        // Loop for all days of month
        for (int i = 1; i <= lastDayOfMonth; i++) {
            LocalDate currentDay = firstDayOfMonth.plusDays(i - 1);

            if (currentDay.getDayOfWeek().getValue() >= 6) {
                addData(WEEKEND_TEXT_COLOR);
            }
            if (isItToday(currentDay)) {
                addData(TODAY_FULFILLING_COLOR);
                addFormattedData(i);
                //switch to default color
                addData(DEFAULT_TEXT_COLOR);
            } else addFormattedData(i);

            if (currentDay.getDayOfWeek().getValue() >= 6) {
                addData(DEFAULT_TEXT_COLOR);
            }

            if (((currentDay.getDayOfWeek().getValue())) % 7 == 0) {
                addData("\n");
            }
        }
    }

    /**
     * This method joins all printing method in one.
     */
    public void printCalendar() {
        char[] calendar = this.getCalendar().toString().toCharArray();
        Arrays.asList(calendar).stream().forEach(System.out::print);
    }

    public StringBuilder getCalendar() {
        //cleaning stringBuilder
        stringBuilder = new StringBuilder();
        generateCalendarHeader();
        generateCalendarBodyPart();
        return stringBuilder;
    }

    public MonthCalendar(LocalDate currentDay) {
        now = LocalDate.now();
        this.firstDayOfMonth = LocalDate.of(currentDay.getYear(), currentDay.getMonth(), 1);
    }

    public void setNow(LocalDate localDate) {
        now = localDate;
    }
}
