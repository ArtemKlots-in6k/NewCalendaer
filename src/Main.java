import calendar.*;
import designer.ConsoleDesigner;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Objects;
import java.util.Scanner;

import static calendar.Interactive.Command.*;

/**
 * Created by Artem Klots on 7/6/16.
 */
public class Main {
    static Calendar calendar = null;
    static Controller controller;
    static CalendarType calendarType;

    public static void main(String[] args) throws IOException {
        YearMonth date = InputArgumentParser.parseDate(args);
        calendarType = parseType(args);
        switch (calendarType) {
            case Console:
                calendar = new ConsoleCalendar(LocalDate.now(), date);
                break;
            case Html:
                calendar = new HtmlCalendar(LocalDate.now(), date);
                break;
        }
        controller = new Controller(calendar, date);
        System.out.println(calendar.generate(date));
        runCommandHandling();
    }

    public static void runCommandHandling() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String command;
        while (!(command = scanner.next().toUpperCase()).equals("Q")) {
            switch (command) {
                case "D":
                    renderCalendar(controller.handleCommand(D));
                    break;
                case "A":
                    renderCalendar(controller.handleCommand(A));
                    break;
                case "W":
                    renderCalendar(controller.handleCommand(W));
                    break;
                case "S":
                    renderCalendar(controller.handleCommand(S));
                    break;
            }
        }
    }

    public static void renderCalendar(String calendar) {
        if (calendarType.equals(CalendarType.Console) && calendar.length() > 500) {
            System.out.println(new ConsoleDesigner().redesignCalendar(calendar));
        } else System.out.println(calendar);
    }

    public static CalendarType parseType(String[] args) throws IOException {
        return InputArgumentParser.parseCalendarType(args);
    }
}
