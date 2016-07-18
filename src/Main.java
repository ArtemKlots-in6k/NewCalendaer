import calendar.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

import static calendar.Interactive.Command.*;

/**
 * Created by Artem Klots on 7/6/16.
 */
public class Main {
    static Calendar calendar = null;
    static Controller controller;

    public static void main(String[] args) throws IOException {
        YearMonth date = InputArgumentParser.parseDate(args);

        switch (InputArgumentParser.parseCalendarType(args)) {
            case Console:
                calendar = new ConsoleCalendar(LocalDate.now(), date);
                break;
            case Html:
                calendar = new HtmlCalendar(LocalDate.now(), date);
                break;
        }
        controller = new Controller(calendar,date);
        System.out.println(calendar.generate(date));
        runCommandHandling();
    }

    public static void runCommandHandling() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String command;
        while (!(command = scanner.next().toUpperCase()).equals("Q")){
            switch (command){
                case "D":
                    System.out.println(controller.handleCommand(D));
                    break;
                case "A":
                    System.out.println(controller.handleCommand(A));
                    break;
                case "W":
                    System.out.println(controller.handleCommand(W));
                    break;
                case "S":
                    System.out.println(controller.handleCommand(S));
                    break;
            }
        }
    }
}
