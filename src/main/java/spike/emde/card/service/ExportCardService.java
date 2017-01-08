package spike.emde.card.service;

import org.springframework.core.io.Resource;

import java.util.Optional;

public interface ExportCardService {
    Optional<Resource> exportCardToExcel(String cardId);
}
