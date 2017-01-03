package spike.emde.card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value = "/card/{cardId}")
    public ResponseEntity getCard(@PathVariable(value = "cardId") String cardId) {
        return ResponseEntity.ok(cardServices.getCard(cardId).get());
    }

    @PostMapping(value = "/card", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createCard(@RequestBody @Valid Card card) {
        cardServices.createCard(card);
        return ResponseEntity.accepted().build();
    }

    @GetMapping(value = "card/export/{cardId}")
    public ResponseEntity getCardExcel(@PathVariable(value = "cardId") String cardId) {
        /*
        Get the card info.
        Write the cardInfo to an excel.
        Send the excel file to eh user.
         */
        Optional<Card> card = cardServices.getCard(cardId);

        return ResponseEntity.ok().build();
    }
}
