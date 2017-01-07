package spike.emde.card.adapter;

import org.springframework.core.io.FileSystemResource;
import spike.emde.card.model.CardExport;

public interface FileAdapter {
    FileSystemResource write(CardExport cardExport);
}
