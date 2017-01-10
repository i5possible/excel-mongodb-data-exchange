package spike.emde.card.adapter;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExcelAdapterTest {
    @Test
    public void should() throws Exception {
        //given
        //when
        //then

    }

    /*
    This test is not a unit test.
     */
    @Test
    public void shouldResourceContainSheetAsGiven() throws Exception {
        //given
        ExcelAdapter excelAdapter = new ExcelAdapter();
        List<List<String>> content = getDummyContent();
        //when
        Optional<Resource> resource = excelAdapter.getWorkbookResource(content, "name");
        //then
        assertTrue(resource.isPresent());
        XSSFWorkbook sheets = new XSSFWorkbook(resource.get().getInputStream());
        sheets.getNumberOfSheets();
        XSSFSheet sheetAt = sheets.getSheetAt(0);
        int rowNum = sheetAt.getLastRowNum();
        assertEquals(1,rowNum);
        XSSFRow row = sheetAt.getRow(0);
        short lastCellNum = row.getLastCellNum();
        assertEquals(2,lastCellNum);
    }

    private List<List<String>> getDummyContent() {
        List<List<String>> content = new ArrayList<>();
        List<String> row1 = new ArrayList();
        row1.add("A1");
        row1.add("B1");
        content.add(row1);
        List<String> row2 = new ArrayList<>();
        row2.add("A2");
        row2.add("B2");
        content.add(row2);
        return content;
    }
}