package spike.emde.utils;

import org.junit.Test;
import spike.emde.card.model.Card;

import static spike.emde.card.model.CardTest.getDummyCard;

public class CardUtilsTest {
    @Test
    public void convertCardsToStringArray() throws Exception {
        //given
        Card dummyCard = getDummyCard();
        //when
        String[][] actual = CardUtils.convertCardsToStringArray();
        //then
        String[][] expected = ;
    }

}