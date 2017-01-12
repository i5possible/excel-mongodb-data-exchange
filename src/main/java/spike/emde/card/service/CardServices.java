package spike.emde.card.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spike.emde.card.model.CardInfo;
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

    public Optional<CardInfo> getCard(String cardId) {
        return Optional.ofNullable(cardRepository.findOne(cardId));
    }

    public List<CardInfo> getCardsBySize(String size) {
        List<CardInfo> cardInfos = cardRepository.findBySize(size);
        return cardInfos;
    }

    public void createCard(CardInfo cardInfo) {
        cardRepository.save(cardInfo);
    }

/*    public Optional<FileSystemResource> getCardFileResourceBySize (String size) {
        List<CardInfo> cardsBySize = getCardsBySize(size);
        if (cardsBySize.size() == 0) {
            return Optional.empty();
        } else {
            CardInfo[] cards = cardsBySize.toArray(new CardInfo[cardsBySize.size()]);
            return Optional.of(getFileSystemResource(cards));
        }
    }

    private FileSystemResource getFileSystemResource(CardInfo... cards) {
        String filePath = CardUtils.getCardFilePathByName("cards.xlsx");
        CardUtils.writeCardsToExcel(filePath, cards);
        return new FileSystemResource(new File(filePath));
    }*/

    /**
     * Read the card from Excel.
     * Write the card to MongoDB one by one.
     *
     * @param file
     */
    public void importCardFromExcel(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            String[][] strings = readFromInputStream(inputStream);
            List<CardInfo> cardInfoList = CardUtils.convertStringArrayToCards(strings);
            cardInfoList.stream().forEach(cardRepository::save);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
