package spike.emde.card.model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CardExportTest {
    private CardExport myCard;

    @Before
    public void setUp() throws Exception {
        myCard = new CardExport.Builder()
                .setId("001")
                .setBrief("This is brief")
                .setContent("This is content")
                .setAssignedTo(Arrays.asList("HL", "YCZ"))
                .setDueDate(LocalDate.of(2017, 1, 17))
                .setSize("S").build();
    }

    @Test
    public void shouldReturnCardExportAsString() {
        String expected = "id:001\n" +
                "brief:This is brief\n" +
                "content:This is content\n" +
                "assignedTo:HL, YCZ\n" +
                "dueDate:2017-01-17\n" +
                "size:S";
        String actual = myCard.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCardExportAsList () {
        List<String> expected = new ArrayList<>();
        expected.add("001");
        expected.add("This is brief");
        expected.add("This is content");
        expected.add("HL, YCZ");
        expected.add("2017-01-17");
        expected.add("S");

        List<String> actual = myCard.toList();
        assertEquals(expected,actual);
    }
}