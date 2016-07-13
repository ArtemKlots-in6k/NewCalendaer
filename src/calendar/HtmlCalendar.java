package calendar;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.Stream;

/**
 * Created by Artem Klots on 7/13/16.
 */
public class HtmlCalendar extends Calendar {

    private static final String STYLE = "td{\n" +
            "\tpadding: 5px;\n" +
            "\tmin-width: 20px;\n" +
            "}\n" +
            "\n" +
            ".today-cell{\n" +
            "\tborder: 3px solid green;\n" +
            "\tpadding-top: 3px;\n" +
            "\tpadding-bottom: 3px;\n" +
            "\n" +
            "}\n" +
            "\n" +
            ".weekend-cell{\n" +
            "\tbackground-color: #DC143C;\n" +
            "}\n";

    public HtmlCalendar(LocalDate currentDay) {
        super(currentDay);
    }

    @Override
    void renderHeader(LocalDate firstDayOfMonth) {
        generateMonthTitle(firstDayOfMonth);
        generateDaysOfWeek();

    }

    private void generateMonthTitle(LocalDate firstDayOfMonth) {
        append("<html>\n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\n" +
                "  <body>\n" +
                "   <table>\n" +
                "      <tr>\n" +
                "        <td colspan=\"7\"> \n" +
                "            " +
                firstDayOfMonth.getMonth() + " <br>\n" +
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

    public void generateHtmlFile() throws IOException {
        writeCalendarInfile();
        writeStyleSheetInFile();
    }

    private void writeCalendarInfile() throws IOException {
        FileWriter f = new FileWriter("index.html", false);
        f.append(generateCalendar());
        f.close();
    }

    private void writeStyleSheetInFile() throws IOException {
        FileWriter f = new FileWriter("style.css", false);
        f.append(STYLE);
        f.close();
    }

    @Override
    void renderEmptyCell() {
        append("\n<td>\n</td>");
    }

    @Override
    void renderWeekend(LocalDate date) {
        append("\n<td class=\"weekend-cell\">" + date.getDayOfMonth() + "</td>");
    }

    @Override
    void renderToday(LocalDate date) {
        append("\n<td class=\"today-cell\">" + date.getDayOfMonth() + "</td>");
    }

    @Override
    void renderTodayWeekend(LocalDate date) {
        append("\n<td class=\"today-cell weekend-cell\">" + date.getDayOfMonth() + "</td>");
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
