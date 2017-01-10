package spike.emde.card.model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CardInfoExportTest {
    private CardExport completeCardExport;
    private CardExport simplestCardExport;

    @Before
    public void setUp() throws Exception {
        completeCardExport = new CardExport.Builder()
                .setId("001")
                .setBrief("This is brief")
                .setContent("This is content")
                .setAssignedTo(Arrays.asList("HL", "YCZ"))
                .setDueDate(LocalDate.of(2017, 1, 17))
                .setSize("S")
                .build();
        simplestCardExport = new CardExport.Builder()
                .setId("002")
                .setSize("M")
                .build();
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
        List<String> actual = completeCardExport.toList();
        assertEquals(expected,actual);
    }

    @Test
    public void shouldReturnCardExportAsString() {
        String expected = "id:001\n" +
                "brief:This is brief\n" +
                "content:This is content\n" +
                "assignedTo:HL, YCZ\n" +
                "dueDate:2017-01-17\n" +
                "size:S";
        String actual = completeCardExport.toString();
        assertEquals(expected, actual);
    }


    @Test
    public void shouldReturnFullListWithEmptyProperties () {
        List<String> expected = new ArrayList<>();
        expected.add("002");
        expected.add("");
        expected.add("");
        expected.add("");
        expected.add("");
        expected.add("M");
        List<String> actual = simplestCardExport.toList();
        assertEquals(expected,actual);
    }

    @Test
    public void shouldReturnCardExportAsStringWithoutEmptyProperties () {
        String expected = "id:002\n" +
                "size:M";
        String actual = simplestCardExport.toString();
        assertEquals(expected, actual);
    }
}