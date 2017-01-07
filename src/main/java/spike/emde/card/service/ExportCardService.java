package spike.emde.card.service;

import org.springframework.core.io.FileSystemResource;

import java.util.Optional;

public interface ExportCardService {
    Optional<FileSystemResource> exportCard(String cardId);
}
