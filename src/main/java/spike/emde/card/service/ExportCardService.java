package spike.emde.card.service;

import org.springframework.core.io.Resource;
import spike.emde.card.exception.CannotWriteToWorkbookException;

import java.util.Map;
import java.util.Optional;

public interface ExportCardService {
    Optional<Resource> exportCardsToExcel(Map<String, String> filterMap) throws CannotWriteToWorkbookException;
}
