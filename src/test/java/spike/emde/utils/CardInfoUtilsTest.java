package spike.emde.utils;

import org.junit.Test;
import spike.emde.card.model.CardInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static spike.emde.card.model.CardInfoTest.getDummyCard;

public class CardInfoUtilsTest {
    @Test
    public void convertCardsToStringArray() throws Exception {
        //given
        CardInfo dummyCardInfo = getDummyCard();
        //when
        String[][] actual = CardUtils.convertCardsToStringArray(dummyCardInfo);
        //then
        String[][] expected = {
                {
                        "id", "brief", "content", "assignedTo", "dueDate"
                },
                {
                        "1", "First CardInfo", "This is content.", "Hong Liang, Yuchen Zhang", "2017-01-04"
                }
        };
        assertArrayEquals(expected, actual);
    }

    @Test
    public void convertStringArrayToCards() {
        String path = "cards.xlsx";
        List<CardInfo> cardInfoList = new ArrayList<>();
        try {
            cardInfoList = CardUtils.convertStringArrayToCards(ExcelUtils.readFromExcel(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(2, cardInfoList.size());
    }
}