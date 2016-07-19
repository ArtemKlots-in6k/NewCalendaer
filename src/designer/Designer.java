package designer;

/**
 * Created by Artem Klots on 19.07.2016.
 */
public abstract class Designer {
    String calendar;
    String topRow;
    String[] rows;
    StringBuilder stringBuilder = new StringBuilder();

    public abstract void redesignCalendar(String calendar);

    public abstract void generateRow(int start,int finish);


}
