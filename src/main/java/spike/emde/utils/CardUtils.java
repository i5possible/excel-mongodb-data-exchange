package spike.emde.utils;

import spike.emde.card.model.CardInfo;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardUtils {

    private static final String cardDir = new String("src/main/resources/card/");

    public static String getCardFilePathByName(String fileName) {
        return CardUtils.cardDir + fileName;
    }

    public static String[][] convertCardsToStringArray(CardInfo... cardInfos) {
        List<String[]> cardList = new ArrayList<>();

        Class<CardInfo> cardClass = CardInfo.class;
        Field[] fields = cardClass.getDeclaredFields();

        List<String> scheme = new ArrayList<>();
        for (Field field : fields) {
            scheme.add(field.getName());
        }

        cardList.add(scheme.toArray(new String[scheme.size()]));
        for (CardInfo cardInfo : cardInfos) {
            cardList.add(cardInfo.toStringArray());
        }
        return cardList.toArray(new String[cardList.size()][]);
    }

    public static String[][] convertCardsToStringArray(CardInfo cardInfo) {
        CardInfo[] cardInfos = {cardInfo};
        return convertCardsToStringArray(cardInfos);
    }

    public static List<CardInfo> convertStringArrayToCards(String[][] strings) {
        int rowLen = strings.length;
        String[] schema = strings[0];
        List<CardInfo> cardInfoList = new ArrayList<>();
        int columnLen = schema.length;
        for (int i = 1; i < rowLen; i++) {
            String[] string = strings[i];
            Map<String, String> map = new HashMap<>();
            for (int j = 0; j < columnLen; j++) {
                map.put(schema[j], string[j]);
            }
            try {
                CardInfo cardInfo = new CardInfo(map);
                cardInfoList.add(cardInfo);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return cardInfoList;
    }
}
