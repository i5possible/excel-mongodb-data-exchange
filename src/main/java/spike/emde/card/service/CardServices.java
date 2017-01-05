package spike.emde.card.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import spike.emde.card.model.Card;
import spike.emde.card.repository.CardRepository;
import spike.emde.utils.CardUtils;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class CardServices {
    private CardRepository cardRepository;

    public CardServices(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Optional<Card> getCard(String cardId) {
        return Optional.ofNullable(cardRepository.findOne(cardId));
    }

    public List<Card> getCardsBySize(String size) {
        List<Card> cards = cardRepository.findBySize(size);
        return cards;
    }

    public void createCard(Card card) {
        cardRepository.save(card);
    }

    public FileSystemResource getCardFileResource (String cardId) {
        Card[] cards = {getCard(cardId).get()};
        String filePath = CardUtils.getCardFilePathByName("firstCard.xlsx");
        CardUtils.WriteCardToExcel(cards, filePath);
        return new FileSystemResource(new File(filePath));
    }
}