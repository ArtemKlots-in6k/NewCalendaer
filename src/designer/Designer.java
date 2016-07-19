package designer;

/**
 * Created by Artem Klots on 19.07.2016.
 */
public abstract class Designer {
    String calendar;
    StringBuilder stringBuilder = new StringBuilder();

    public abstract String redesignCalendar(String calendar);

    abstract void generateRow(int start, int finish);


}
