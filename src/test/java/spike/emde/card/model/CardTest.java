package spike.emde.card.model;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class CardTest {
    public static Card getDummyCard() throws ParseException {
        Card card = new Card();
        List<String> owner = new ArrayList();
        owner.add("Hong Liang");
        owner.add("Yuchen Zhang");
        card.setId("1");
        card.setBrief("First Card");
        card.setContent("This is content.");
        card.setAssignedTo(owner);
        card.setDueDate((new SimpleDateFormat("yyyy-mm-dd").parse("2017-01-04")));
        return card;
    }

    @Test
    public void shouldReturnCardStringAsExpected() throws ParseException {
        Card card = getDummyCard();
        String[] expectedStringArray = {"1", "First Card", "This is content.", "Hong Liang, Yuchen Zhang", "2017-01-04"};
        assertArrayEquals(expectedStringArray, card.toStringArray());
    }
}
