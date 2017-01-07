package spike.emde.card.adapter;

import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.util.List;

public class ExcelAdapter implements FileAdapter {
    @Override
    public FileSystemResource write(List<String> content, File path) {
        return null;
    }
}
