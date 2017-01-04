package spike.emde.utils;

import org.junit.Test;
import spike.emde.card.model.Card;

import static org.junit.Assert.assertArrayEquals;
import static spike.emde.card.model.CardTest.getDummyCard;

public class CardUtilsTest {
    @Test
    public void convertCardsToStringArray() throws Exception {
        //given
        Card dummyCard = getDummyCard();
        //when
        String[][] actual = CardUtils.convertCardsToStringArray(dummyCard);
        //then
        String[][] expected = {
                {
                        "id", "brief", "content", "assignedTo", "dueDate"
                },
                {
                        "1", "First Card", "This is content.", "Hong Liang, Yuchen Zhang", "2017-01-04"
                }
        };
        assertArrayEquals(expected, actual);
    }

}