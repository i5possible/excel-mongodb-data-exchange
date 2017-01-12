package spike.emde.card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import spike.emde.card.service.CardImportService;

import java.io.IOException;
import java.util.Optional;

public class CardImportController {
    @Autowired
    CardImportService importCardService;

    @PostMapping(value = "cards/import", consumes = "multipart/form-data")
    public ResponseEntity importCard(@PathVariable(value = "file") MultipartFile file) throws IOException {
        Optional<Resource> resource = (file.getBytes().length == 0)
                ? Optional.empty() : Optional.of(new ByteArrayResource(file.getBytes()));
        ((Runnable) () -> importCardService.writeCardToDB(resource)).run();
        return resource.map(r -> ResponseEntity.accepted().build()).orElse(ResponseEntity.notFound().build());
    }
}
