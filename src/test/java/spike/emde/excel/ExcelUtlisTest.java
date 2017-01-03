package spike.emde.excel;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ExcelUtlisTest {
    @Test
    public void shouldReturn3RowsAnd4ColumnStringArray () throws IOException {
        String fileName = "HelloWorld.xlsx";
        String[][] strings = ExcelUtils.ReadFromExcel(fileName);
        assertEquals(strings.length,3);
    }
}
