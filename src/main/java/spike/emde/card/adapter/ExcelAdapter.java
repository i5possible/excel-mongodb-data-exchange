package spike.emde.card.adapter;

import org.springframework.core.io.FileSystemResource;
import spike.emde.card.model.CardExport;

public class ExcelAdapter implements FileAdapter {
    @Override
    public FileSystemResource write(CardExport cardExport) {
        return null;
    }
}
