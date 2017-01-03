package spike.emde.card.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spike.emde.card.model.Card;
import spike.emde.card.repository.CardRepository;

import java.util.Optional;

@Service
public class CardServicesImpl implements CardServices {
    private CardRepository cardRepository;

    @Autowired
    public CardServicesImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Optional<Card> getCard(String cardId) {
        return Optional.ofNullable(cardRepository.findOne(cardId));
    }

    @Override
    public void createCard(Card card) {
        cardRepository.save(card);
    }
}