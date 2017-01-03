package spike.emde.utils;

import spike.emde.card.model.Card;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CardUtils {

    private static final String cardDir = new String("src/main/resources/card/");

    public static void WriteCardToExcel (Card card, String filePath) {
        Card[] cards = {card};
        String[][] cardsInfo = convertCardsToStringArray(cards);
        try {
            ExcelUtils.WriteToExcel(cardsInfo,filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getCardFilePathByName (String fileName) {
        return CardUtils.cardDir + fileName;
    }

    public static String[][] convertCardsToStringArray (Card[] cards) {
        List<String[]> cardList = new ArrayList<>();

        Class<Card> cardClass = Card.class;
        Field[] fields = cardClass.getDeclaredFields();

        List<String> scheme = new ArrayList<>();
        for (Field field: fields) {
            scheme.add(field.getName());
        }

        cardList.add(scheme.toArray(new String[scheme.size()]));
        for (Card card : cards) {
            cardList.add(card.toStringArray());
        }
        return  cardList.toArray(new String[cardList.size()][]);
    }
}
