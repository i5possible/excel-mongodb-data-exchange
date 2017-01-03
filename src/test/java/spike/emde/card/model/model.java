package spike.emde.card.model;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class model {
    @Test
    public void shouldReturnCardStringAsExpected () {
        Card card = new Card();
        card.setId("1");
        card.setBrief("First Card");
        card.setContent("This is content.");
        String[] expectedStringArray = { "1","First Card","This is content.","",""};
        assertArrayEquals(expectedStringArray, card.toStringArray());
    }
}
