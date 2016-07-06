import calendar.InputDataValidator;
import calendar.MonthCalendar;

/**
 * Created by employee on 7/6/16.
 */
public class Main {
    public static void main(String[] args) {
        InputDataValidator inputDataValidator = new InputDataValidator();
        if (inputDataValidator.validate(args)){
            MonthCalendar monthCalendar = new MonthCalendar(inputDataValidator.getDate(), inputDataValidator.getNow());
            monthCalendar.printCalendar();
        }
    }
}
