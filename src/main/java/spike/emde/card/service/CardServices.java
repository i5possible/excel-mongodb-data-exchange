package spike.emde.card.service;

import spike.emde.card.model.Card;

import java.util.Optional;

public interface CardServices {
    Optional<Card> getCard(String cardId);

    void createCard(Card card);
}

