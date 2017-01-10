package spike.emde.card.adapter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import spike.emde.card.model.CardImport;
import spike.emde.card.model.Exportable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class ExcelAdapter implements FileAdapter {

    @Override
    public Optional<Resource> write(Exportable... toExportResources) { // TODO: rename here
        List<List<String>> content = new ArrayList<>();
//        content.add(CardExport.getSchema()); // TODO: annotation
        Arrays.asList(toExportResources).stream().map(Exportable::toList).forEach(content::add);
        Arrays.stream(toExportResources).map(Exportable::getSchemaList);
        // Arrays.asList(cardExport).stream().collect(groupBy)

        // card card people people label
        // group by

        // schema data data data -> sheet 1
        // schema data data -> sheet 2
        // schema data -> sheet 3
        // version -> sheet 4 version 4


        /*
         * Each toExportResource create a sheet in workbook.
         */
        String fileName = "";
        return getWorkbookResource(content, fileName);
    }

    @Override
    public List<CardImport> read(Resource resource) {
        return null;
    }

    private Optional<Resource> getWorkbookResource(List<List<String>> content, String description) {
        SXSSFWorkbook sheets = new SXSSFWorkbook(100);
        writeToSheet(sheets, content,description);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            sheets.write(byteArrayOutputStream);
            // Optional.of or Optional.ofNullable
            return Optional.of(new ByteArrayResource(byteArrayOutputStream.toByteArray(), description));
        } catch (IOException e) {
            return Optional.empty();
            //e.printStackTrace(); // TODO: log
            // Pretend there is a log.
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException ignored) {
            }
        }
    }

    private <T extends Exportable> Workbook writeToWorkbook(List<T> toExportResource) {
        Workbook workbook = new SXSSFWorkbook(100);
        return workbook;
    }

    private Sheet writeToSheet(SXSSFWorkbook workbook, List<List<String>> content, String sheetName) {
        SXSSFSheet sheet = workbook.createSheet(sheetName);
        int rowSeq = 0;
        for (List<String> listString : content) {
            Row row = sheet.createRow(rowSeq++);
            int cellSeq = 0;
            for (String cellString : listString) {
                Cell cell = row.createCell(cellSeq++);
                cell.setCellValue(cellString);
            }
        }
        return sheet;
    }
}
