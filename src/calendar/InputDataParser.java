package calendar;


import java.security.InvalidParameterException;
import java.time.LocalDate;

/**
 * Created by Artem Klots on 7/6/16.
 */
public class InputDataParser {
    //date in which i`m interested for
    private LocalDate currentDate;
    private LocalDate now;

    /**
     * The validate method receive incoming parameters in numeric format.
     *
     * @param args - args[0] - month; args[1] - year.
     */
    private boolean validate(String[] args) {
        /**
         * In the code below wrote checking for arguments and their validity.
         * If arguments is empty, program will select the current month.
         * If arguments is incorrect, program will stop.
         */
        if (args.length < 1) {
            return true;
        } else {
            try {
                int month = Integer.parseInt(args[0]);

                if ((month < 1) || (month > 12))
                    return false;
            } catch (NumberFormatException e) {
                return false;
            }

            if (args.length != 2)
                return false;
        }
        return true;
    }


    public LocalDate parse(String[] args) {
        if (this.validate(args)) {
            if (args.length < 1)
                return now;
            int month = Integer.parseInt(args[0]);
            int year = Integer.parseInt(args[1]);
            currentDate = LocalDate.of(year, month, 1);
            return currentDate;
        } else throw (new InvalidParameterException("Wrong input"));
    }


    public InputDataParser() {
        now = LocalDate.now();
    }

    public LocalDate getParsingResult() {
        return currentDate;
    }
}

