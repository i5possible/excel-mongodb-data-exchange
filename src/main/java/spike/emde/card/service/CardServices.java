package spike.emde.card.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spike.emde.card.model.Card;
import spike.emde.card.repository.CardRepository;
import spike.emde.utils.CardUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import static spike.emde.utils.ExcelUtils.readFromInputStream;

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

/*    public Optional<FileSystemResource> getCardFileResourceBySize (String size) {
        List<Card> cardsBySize = getCardsBySize(size);
        if (cardsBySize.size() == 0) {
            return Optional.empty();
        } else {
            Card[] cards = cardsBySize.toArray(new Card[cardsBySize.size()]);
            return Optional.of(getFileSystemResource(cards));
        }
    }

    private FileSystemResource getFileSystemResource(Card... cards) {
        String filePath = CardUtils.getCardFilePathByName("cards.xlsx");
        CardUtils.writeCardsToExcel(filePath, cards);
        return new FileSystemResource(new File(filePath));
    }*/

    /**
     * Read the card from Excel.
     * Write the card to MongoDB one by one.
     * @param file
     */
    public void importCardFromExcel (MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            String[][] strings = readFromInputStream(inputStream);
            List<Card> cardList = CardUtils.convertStringArrayToCards(strings);
            cardList.stream().forEach(cardRepository::save);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
