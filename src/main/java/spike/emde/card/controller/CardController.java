package spike.emde.card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spike.emde.card.model.Card;
import spike.emde.card.service.CardServices;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class CardController {
    private CardServices cardServices;

    @Autowired
    public CardController(CardServices cardServices) {
        this.cardServices = cardServices;
    }

    @GetMapping(value = "/card/get/{cardId}")
    public ResponseEntity getCard(@PathVariable(value = "cardId") String cardId) {
        return ResponseEntity.ok(cardServices.getCard(cardId).get());
    }

    @GetMapping(value = "/card/getBySize/{size}")
    public ResponseEntity getCardBySize(@PathVariable(value = "size") String size) {
        return ResponseEntity.ok(cardServices.getCardsBySize(size));
    }

    @PostMapping(value = "/card", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createCard(@RequestBody @Valid Card card) {
        cardServices.createCard(card);
        return ResponseEntity.accepted().build();
    }

    @GetMapping(value = "card/export/{cardId}", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public ResponseEntity getCardExcel(@PathVariable(value = "cardId") String cardId) {
        Optional<FileSystemResource> cardFileResource = cardServices.getCardFileResource(cardId);
        if (cardFileResource.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .header("Content-Disposition", "attachment; filename=\"" + cardId + ".xlsx\"")
                    .body(cardFileResource.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "card/exportBySize/{size}", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public ResponseEntity getCardExcelBySize(@PathVariable(value = "size") String size) {
        Optional<FileSystemResource> cardFileResourceBySize = cardServices.getCardFileResourceBySize(size);
        if (cardFileResourceBySize.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .header("Content-Disposition", "attachment; filename=\"" + size + ".xlsx\"")
                    .body(cardFileResourceBySize.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "card/import/", consumes = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public ResponseEntity importCard(@PathVariable(value = "file") MultipartFile file) {
        cardServices.importCardFromExcel(file);
        return ResponseEntity.accepted().body("");
    }
}
