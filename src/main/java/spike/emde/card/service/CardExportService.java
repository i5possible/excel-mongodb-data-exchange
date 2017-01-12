package spike.emde.card.service;

import org.springframework.core.io.Resource;
import spike.emde.card.exception.CannotWriteToWorkbookException;

import java.util.Map;
import java.util.Optional;

public interface CardExportService {
    Optional<Resource> exportCardsExcel(Map<String, String> filterMap) throws CannotWriteToWorkbookException;
}
