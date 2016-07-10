package calendar;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

/**
 * Created by Artem Klots on 04.06.2016.
 */
public class MonthCalendar {
    private LocalDateTime firstDayOfMonth;
    private LocalDateTime currentDay;
    private StringBuilder stringBuilder = new StringBuilder("");

    private void addFormattedData(int data) {
        if (data < 10) {
            stringBuilder.append("    ").append(data);
        } else {
            stringBuilder.append("   ").append(data);
        }
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
    public boolean isItToday(LocalDateTime currentDay) {
        return (currentDay.getDayOfMonth() == LocalDateTime.now().getDayOfMonth()) && (currentDay.getYear() == LocalDateTime.now().getYear() && (currentDay.getMonth() == LocalDateTime.now().getMonth()));
    }

    public static String getDaysOfWeek() {
        return "Mon  Tue  Wen  Thu  Fri  Sut  Sun";
    }

    /**
     * This method writes title of month and days of week into the top of calendar, and empty cells at the beginning of month.
     */
    private void generateCalendarHeader() {
//        System.out.printf("%20s", firstDayOfMonth.getMonth() + "\n");
        addData("\t\t\t\t" + firstDayOfMonth.getMonth() + "\n");
//        System.out.println("  " + getDaysOfWeek());
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
            LocalDateTime currentDay = firstDayOfMonth.plusDays(i - 1);
            // generate "today" in green rectangle (if it today)
            if (isItToday(currentDay)) {
                //add red if it today (and weekend)
                if (currentDay.getDayOfWeek().getValue() >= 6) {
                    addData("\033[31;1m");
                }
                // Write current day in green rectangle if it is today.
                addData((char) 27 + "[42m");

                addFormattedData(i);
                //swith to default color
                addData("\033[39;49m");

            } else {
                // Write Sut. and Sun. in red color
                if (currentDay.getDayOfWeek().getValue() >= 6) {
                    addData("\033[31;1m");
                    addFormattedData(i);
                    addData("\033[39;49m");
                } else {
                    // Write normal weekday.

                    addFormattedData(i);


                }
            }
            // Go to a new line, if week is over.
            if (((currentDay.getDayOfWeek().getValue())) % 7 == 0) {
                addData("\n");
            }
        }
    }

    /**
     * This method joins all printing method in one.
     */
    public void printCalendar() {
        System.out.println(this.getCalendar());
    }

    public StringBuilder getCalendar() {
        //cleaning stringBuilder
        stringBuilder = new StringBuilder();
        generateCalendarHeader();
        generateCalendarBodyPart();
        return stringBuilder;
    }

    public MonthCalendar(LocalDateTime currentDay) {
        this.currentDay = currentDay;
        this.firstDayOfMonth = LocalDateTime.of(currentDay.getYear(), currentDay.getMonth(), 1, 1, 1);
    }
}
