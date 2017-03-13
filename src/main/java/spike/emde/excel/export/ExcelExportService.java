package spike.emde.excel.export;

import org.springframework.stereotype.Service;
import spike.emde.excel.ExcelData;
import spike.emde.excel.ExcelHeader;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelExportService<T> {
    private ThreadLocal<ExcelHeader<T>> excelHeader;
    private ThreadLocal<ExcelData<T>> excelData;

    private File ExportExcel(String fileName, List<T> toExportEntities, Class<T> clazz,
                                 List<String> toExportFieldNameList) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            boolean fileCreated = file.createNewFile();
        }
        this.excelHeader.set(new ExcelHeader(clazz, toExportFieldNameList));
        this.excelData.set(new ExcelData(new ExcelHeader(clazz, toExportFieldNameList), toExportEntities));
        return file;
    }
}
