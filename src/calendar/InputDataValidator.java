package calendar;

import java.time.LocalDateTime;

/**
 * Created by employee on 7/6/16.
 */
public class InputDataValidator {
    //date in which i`m interested for
    private static LocalDateTime currentDate;
    //today
    private static LocalDateTime now;

    /**
     * The validate method receive incoming parameters in numeric format.
     * @param args - args[0] - month; args[1] - year.
     */
    public boolean validate(String[] args){
        /**
         * In the code below wrote checking for arguments and their validity.
         * If arguments is empty, program will select the current month.
         * If arguments is incorrect, program will stop.
         */
        if (args.length < 1) {
            currentDate = now;
        } else {
            try {
                int month = Integer.parseInt(args[0]);
                int year = Integer.parseInt(args[1]);
                if ((month < 1) || (month > 12))
                    throw new IllegalArgumentException();
                currentDate = LocalDateTime.of(year, month, 1, 1, 0);

            } catch (NumberFormatException e) {
                System.out.println("Wrong type of arguments!");
                return false;
            } catch (IllegalArgumentException e) {
                System.out.println("Wrong input!");
                return false;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong number of arguments!");
                return false;
            }
        }
        return true;
    }

    public InputDataValidator(){
        now = LocalDateTime.now();
    }

    public LocalDateTime getCurrentDate(){
        return currentDate;
    }

    public LocalDateTime getNow() {
        return now;
    }
}

