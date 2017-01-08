package spike.emde.utils;

import spike.emde.card.model.Card;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardUtils {

    private static final String cardDir = new String("src/main/resources/card/");

    public static void writeCardsToExcel(String filePath, Card... cards) {
        String[][] cardsInfo = convertCardsToStringArray(cards);
        try {
            ExcelUtils.writeToExcel(cardsInfo, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getCardFilePathByName(String fileName) {
        return CardUtils.cardDir + fileName;
    }

    public static String[][] convertCardsToStringArray(Card... cards) {
        List<String[]> cardList = new ArrayList<>();

        Class<Card> cardClass = Card.class;
        Field[] fields = cardClass.getDeclaredFields();

        List<String> scheme = new ArrayList<>();
        for (Field field : fields) {
            scheme.add(field.getName());
        }

        cardList.add(scheme.toArray(new String[scheme.size()]));
        for (Card card : cards) {
            cardList.add(card.toStringArray());
        }
        return cardList.toArray(new String[cardList.size()][]);
    }

    public static String[][] convertCardsToStringArray(Card card) {
        Card[] cards = {card};
        return convertCardsToStringArray(cards);
    }

    public static List<Card> convertStringArrayToCards(String[][] strings) {
        int rowLen = strings.length;
        String[] schema = strings[0];
        List<Card> cardList = new ArrayList<>();
        int columnLen = schema.length;
        for (int i = 1; i < rowLen; i++) {
            String[] string = strings[i];
            Map<String, String> map = new HashMap<>();
            for (int j = 0; j < columnLen; j++) {
                map.put(schema[j], string[j]);
            }
            try {
                Card card = new Card(map);
                cardList.add(card);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return cardList;
    }
}
