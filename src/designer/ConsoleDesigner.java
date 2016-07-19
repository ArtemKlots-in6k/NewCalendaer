package designer;

/**
 * Created by Владелец on 19.07.2016.
 */
public class ConsoleDesigner extends Designer {
    @Override
    public String redesignCalendar(String calendar) {
        this.calendar = calendar;

        generateRow(0,3);
        System.out.println();
        generateRow(3,6);
        System.out.println();
        generateRow(6,9);
        System.out.println();
        generateRow(9,12);
        return stringBuilder.toString();
    }

    @Override
    public void generateRow(int start, int finish) {
        String[] month = calendar.split("-----------------------------------");
        for (int i = 0; i < month[i].split("\n").length; i++) {
            for (int j = start; j < finish; j++) {

                stringBuilder.append(String.format("%37.237s", month[j].split("\n")[i] + "\t|"));
//                stringBuilder.append();
//                System.out.println(month[j].split("\\n")[i].replace("\\n", "$"));
            }
            stringBuilder.append("\n");
        }
    }
}
