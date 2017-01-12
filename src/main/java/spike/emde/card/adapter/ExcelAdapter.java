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
import spike.emde.model.AbstractExportable;
import spike.emde.model.Exportable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ExcelAdapter implements FileAdapter {

    @Override
    public Optional<Resource> write(String resourcesName, AbstractExportable... toExportResources) { // TODO: rename here
        return getWorkbookResource(resourcesName, new ArrayList<>(
                Arrays.stream(toExportResources)
                        .collect(Collectors.groupingBy(Exportable::getClass))
                        .values())
        );
    }

    @Override
    public List<CardImport> read(Resource resource) {
        return null;
    }

    public <T extends AbstractExportable> Optional<Resource> getWorkbookResource(String resourcesName, List<List<T>> groupedResources) {
        Workbook workbook = writeToWorkbook(groupedResources);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            workbook.write(byteArrayOutputStream);
            return Optional.of(new ByteArrayResource(byteArrayOutputStream.toByteArray(), resourcesName));
        } catch (IOException e) {
            return Optional.empty();
            // Pretend there is a log.// TODO: log
        } finally {
            try {
                byteArrayOutputStream.close();
                workbook.close();
            } catch (IOException ignored) {
            }
        }
    }

    private <T extends AbstractExportable> Workbook writeToWorkbook(List<List<T>> groupedResources) {
        SXSSFWorkbook workbook = new SXSSFWorkbook(100);
        groupedResources.forEach(resources -> writeToSheet(workbook, resources));
        return workbook;
    }

    private <T extends AbstractExportable> Sheet writeToSheet(SXSSFWorkbook workbook, List<T> resources) {
        ArrayList<List<String>> content = new ArrayList<>();
        content.add(resources.get(0).fetchSchema());
        content.addAll(resources.stream()
                .map(AbstractExportable::toList)
                .collect(Collectors.toList()));
        return writeToSheet(workbook, content, resources.get(0).fetchTitle());
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
