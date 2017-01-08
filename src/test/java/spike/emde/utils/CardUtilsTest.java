package spike.emde.utils;

import org.junit.Test;
import spike.emde.card.model.Card;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
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

    @Test
    public void convertStringArrayToCards () {
        String path = "cards.xlsx";
        List<Card> cardList = new ArrayList<>();
        try {
            cardList = CardUtils.convertStringArrayToCards(ExcelUtils.readFromExcel(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(2,cardList.size());
    }
}