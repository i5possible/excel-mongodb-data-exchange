package spike.emde.card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import spike.emde.card.exception.CannotWriteToWorkbookException;
import spike.emde.card.service.ExportCardService;

import java.util.Optional;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
public class ExportCardController {
    @Autowired
    ExportCardService exportCardService;

    @GetMapping(value = "cards/{cardId}/export", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public ResponseEntity exportCardToExcel(@PathVariable(value = "cardId") String cardId) {
        Optional<Resource> resource;
        try {
            resource = exportCardService.exportCardToExcel(cardId);
        } catch (CannotWriteToWorkbookException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Cannot write to workbook!");
        }
        return resource.map(r ->this.buildExportResponse(r,cardId)).orElse(ResponseEntity.notFound().build());
    }

 /*   @GetMapping(value = "card/exportBySize/{size}", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public ResponseEntity getCardExcelBySize(@PathVariable(value = "size") String size) {
        Optional<FileSystemResource> cardFileResourceBySize = exportCardService.exportCardsToExcel(size);
        if (cardFileResourceBySize.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .header("Content-Disposition", "attachment; filename=\"" + size + ".xlsx\"")
                    .body(cardFileResourceBySize.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

    private ResponseEntity buildExportResponse(Resource resource, String fileName) {
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Disposition", "attachment; filename=\"" + fileName + ".xlsx\"")
                .body(resource);
    }
}
