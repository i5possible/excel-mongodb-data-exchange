package spike.emde.card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spike.emde.card.model.Card;
import spike.emde.card.service.CardServices;

import javax.validation.Valid;

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
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Disposition", "attachment; filename=\"" + cardId + "\"")
                .body(cardServices.getCardFileResource(cardId));
    }
}
