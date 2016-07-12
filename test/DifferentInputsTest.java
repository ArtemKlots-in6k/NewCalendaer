import calendar.InputDataParser;
import org.junit.Test;

import java.security.InvalidParameterException;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Artem Klots on 7/6/16.
 */
public class DifferentInputsTest {

    @Test
    public void parseData() {
        InputDataParser inputDataParser = new InputDataParser();
        assertThat(inputDataParser.parse(new String[]{"07", "2016"}), is(LocalDate.of(2016, 07, 1)));
    }

    @Test(expected = InvalidParameterException.class)
    public void parseDataWithOneParameter() {
        InputDataParser inputDataParser = new InputDataParser();
        inputDataParser.parse(new String[]{"07"});
    }

    @Test(expected = InvalidParameterException.class)
    public void parseDataWithThreeParameter() {
        InputDataParser inputDataParser = new InputDataParser();
        inputDataParser.parse(new String[]{"07", "2016", "123"});
    }

    @Test(expected = InvalidParameterException.class)
    public void parseDataWithWrongTypeOfParameters() {
        InputDataParser inputDataParser = new InputDataParser();
        inputDataParser.parse(new String[]{"mm", "2016"});
    }
}
