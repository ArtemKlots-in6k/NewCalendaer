package calendar;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.function.Supplier;

/**
 * Created by Artem Klots on 7/13/16.
 */
public class HtmlCalendar extends CalendarImpl {

    private static final String STYLE = "td{\n" +
            "\tpadding: 5px;\n" +
            "\tmin-width: 20px;\n" +
            "}\n" +
            "\n" +
            ".today-day{\n" +
            "\tborder: 3px solid green;\n" +
            "\tpadding: 3px;\n" +
            "\n" +
            "}\n" +
            "\n" +
            ".weekend-day{\n" +
            "\tbackground-color: #DC143C;\n" +
            "}\n";

    public HtmlCalendar(LocalDate today, YearMonth yearMonth) {
        super(today, yearMonth);
    }

    public HtmlCalendar(Supplier<LocalDate> supplier, YearMonth yearMonth) {
        super(supplier, yearMonth);
    }

    private void generateMonthTitle(Month month) {
        append("      <tr>\n" +
                "        <td colspan=\"7\"> \n" +
                "            " +
                month + " <br>\n" +
                "        </td>\n" +
                "      </tr>\n");
    }

    private void generateDaysOfWeek() {
        append("      <tr>\n");
        for (String day : super.getDaysOfWeek()) {
            append("        <td>"
                    + day
                    + "</td>\n");
        }
        append("      </tr>\n<tr>\n");
    }

    public void generateHtmlFile(YearMonth yearMonth) throws IOException {
        writeCalendarInfile(yearMonth);
        writeStyleSheetInFile();
    }

    private void writeCalendarInfile(YearMonth yearMonth) throws IOException {
        FileWriter f = new FileWriter("index.html", false);
        f.append(generateCalendar(yearMonth));
        f.close();
    }

    private void writeStyleSheetInFile() throws IOException {
        FileWriter f = new FileWriter("style.css", false);
        f.append(STYLE);
        f.close();
    }

    @Override
    void renderHeader(Month month) {
        append("<html>\n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\n" +
                "  <body>\n" +
                "   <table>\n");
        generateMonthTitle(month);
        generateDaysOfWeek();

    }

    @Override
    void renderFooter() {
        append("\n" +
                "</tr>\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>");
    }

    @Override
    void renderEmptyDay() {
        append("\n<td>\n</td>");
    }

    @Override
    void renderWeekend(LocalDate date) {
        append("\n<td class=\"weekend-day\">" + date.getDayOfMonth() + "</td>");
    }

    @Override
    void renderToday(LocalDate date) {
        append("\n<td class=\"today-day\">" + date.getDayOfMonth() + "</td>");
    }

    @Override
    void renderTodayWeekend(LocalDate date) {
        append("\n<td class=\"today-day weekend-day\">" + date.getDayOfMonth() + "</td>");
    }

    @Override
    void renderDay(LocalDate date) {
        append("\n<td>" + date.getDayOfMonth() + "</td>");
    }

    @Override
    void startNewWeek() {
        append("\n</tr><tr>");
    }
}
