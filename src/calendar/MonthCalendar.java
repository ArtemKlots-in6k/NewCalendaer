package calendar;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

/**
 * Created by Artem Klots on 04.06.2016.
 */
public class MonthCalendar {
    private LocalDateTime firstDayOfMonth;
    private LocalDateTime now;
    private StringBuilder stringBuilder = new StringBuilder("");

    private void addFormattedData(int data){
        if (data < 10){
            stringBuilder.append("    ").append(data);
        }else {
            stringBuilder.append("   ").append(data);
        }
    }

    private void addData(String data){
        stringBuilder.append(data);
    }

    /**
     * This function checks if selected day is today.
     *
     * @param currentDay - verifiable data.
     * @return - is it today, or not.
     */
    public boolean isItToday(LocalDateTime currentDay) {
        return (currentDay.getDayOfMonth() == now.getDayOfMonth()) && (currentDay.getYear() == now.getYear() && (currentDay.getMonth() == now.getMonth()));
    }

    public static String getDaysOfWeek(){
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
            // Write current day in green rectangle if it is today.
            if (isItToday(currentDay)) {
//                System.out.printf("%17s ", (char) 27 + "[42m  " + i + "\033[39;49m");
                addData((char) 27 + "[42m");
                addFormattedData(i);
                addData("\033[39;49m");
            } else {
                // Write Sut. and Sun. in red color
                if (currentDay.getDayOfWeek().getValue() >= 6) {
//                    System.out.print("\033[31;1m"); //change console color to red.
                    addData("\033[31;1m");
//                    System.out.printf("%4d ", i);
                    addFormattedData(i);
//                    System.out.print("\033[39;49m");//change console color to default.
                    addData("\033[39;49m");
                } else {
                    // Write normal weekday.

//                    System.out.printf("%4d ", i);
                    addFormattedData(i);


                }
            }
            // Go to a new line, if week is over.
            if (((currentDay.getDayOfWeek().getValue())) % 7 == 0) {
//                System.out.println();
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

    public StringBuilder getCalendar(){
        generateCalendarHeader();
        generateCalendarBodyPart();
        return stringBuilder;
    }

    public MonthCalendar(LocalDateTime firstDayOfMonth, LocalDateTime now){
        this.firstDayOfMonth = firstDayOfMonth;
        this.now = now;
    }

}
