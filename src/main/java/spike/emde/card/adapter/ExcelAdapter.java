package spike.emde.card.adapter;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import spike.emde.card.model.CardExport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static spike.emde.utils.ExcelUtils.writeToSheet;

@Component
public class ExcelAdapter implements FileAdapter {
    @Override
    public Optional<Resource> write(CardExport... cardExport) {
        List<List<String>> content = new ArrayList<>();
        content.add(CardExport.getScheme());
        content.addAll(Arrays.stream(cardExport)
                .map(cardExport1 -> cardExport1.toList())
                .collect(Collectors.toList()));
        Resource resource = null;
        resource = getSXSSFWorkbookResource(content);
        return Optional.ofNullable(resource);
    }

    public Resource getSXSSFWorkbookResource(List<List<String>> content) {
        SXSSFWorkbook sheets = new SXSSFWorkbook(100);
        Sheet sheet = sheets.createSheet();
        writeToSheet(sheet, content);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            sheets.write(byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ByteArrayResource(byteArrayOutputStream.toByteArray());
    }
}
