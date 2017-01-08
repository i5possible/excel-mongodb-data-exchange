package spike.emde.excel;

import org.junit.Test;
import spike.emde.utils.ExcelUtils;

import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ExcelUtilsTest {
    @Test
    public void shouldReturn3RowsAnd4ColumnStringArray() throws IOException {
        String fileName = "HelloWorld.xlsx";
        String[][] strings = ExcelUtils.readFromExcel(fileName);
        String[][] expectedStrings = {
                {"A1", "B1", "C1", "D1"},
                {"A2", "B2", "C2", "D2"},
                {"A3", "B3", "C3", "D3"}
        };
        assertEquals(strings.length, 3);
        assertArrayEquals(expectedStrings, strings);
    }

    // TODO: 03/01/2017 A test for write to the excel is needed.
}
