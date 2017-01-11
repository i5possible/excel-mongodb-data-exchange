package spike.emde.card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spike.emde.card.model.CardInfo;
import spike.emde.card.service.CardServices;

import javax.validation.Valid;

@RestController
public class CardController {
    private CardServices cardServices;

    @Autowired
    public CardController(CardServices cardServices) {
        this.cardServices = cardServices;
    }

    @GetMapping(value = "/cards/{cardId}")
    public ResponseEntity getCard(@PathVariable(value = "cardId") String cardId) {
        return ResponseEntity.ok(cardServices.getCard(cardId).get());
    }

    @GetMapping(value = "/cards/")
    public ResponseEntity getCardBySize(@RequestParam(value = "size") String size) {
        return ResponseEntity.ok(cardServices.getCardsBySize(size));
    }

    @PostMapping(value = "/cards", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createCard(@RequestBody @Valid CardInfo cardInfo) {
        cardServices.createCard(cardInfo);
        return ResponseEntity.accepted().build();
    }
}
