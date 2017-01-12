package spike.emde.card.converter;

import org.junit.Before;
import org.junit.Test;
import spike.emde.card.model.CardExport;
import spike.emde.card.model.CardInfo;
import spike.emde.card.model.CardInfoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ExportModelConverterImplTest {
    private CardInfo dummyCardInfo;

    @Before
    public void setUp() throws Exception {
        dummyCardInfo = CardInfoTest.getDummyCard();
    }

    @Test
    public void shouldBeSameAsCard() {
        ExportModelConverter exportModelConverter = new ExportModelConverterImpl();
        CardExport cardExport = exportModelConverter.convert(dummyCardInfo);
        assertEquals(dummyCardInfo.getId(), cardExport.getId());
        assertEquals(dummyCardInfo.getBrief(), cardExport.getBrief());
        assertEquals(dummyCardInfo.getContent(), cardExport.getContent());
        // List and LocalDate need to convert to String.
        assertEquals(dummyCardInfo.getAssignedToString(), cardExport.getAssignedTo());
        assertEquals(dummyCardInfo.getDueDateString(), cardExport.getDueDate());
        // The card contains null, the cardExport contains ''
        assertNotEquals(dummyCardInfo.getSize(), cardExport.getSize());
    }
}