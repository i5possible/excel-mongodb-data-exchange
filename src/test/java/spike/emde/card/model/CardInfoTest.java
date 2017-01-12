package spike.emde.card.model;

import org.junit.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class CardInfoTest {

    public static CardInfo getDummyCard() {
        CardInfo cardInfo = new CardInfo();
        List<String> owner = new ArrayList();
        owner.add("Hong Liang");
        owner.add("Yuchen Zhang");
        cardInfo.setId("1");
        cardInfo.setBrief("First CardInfo");
        cardInfo.setContent("This is content.");
        cardInfo.setAssignedTo(owner);
        cardInfo.setDueDate(LocalDate.of(2017, 1, 4));
        cardInfo.setSize("S");
        return cardInfo;
    }

    @Test
    public void shouldReturnCardStringAsExpected() throws ParseException {
        CardInfo cardInfo = getDummyCard();
        String[] expectedStringArray = {"1", "First CardInfo", "This is content.", "Hong Liang, Yuchen Zhang", "2017-01-04", "S"};
        assertArrayEquals(expectedStringArray, cardInfo.toStringArray());
    }
}
