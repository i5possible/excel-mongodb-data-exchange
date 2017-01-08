package spike.emde.card.converter;

import org.junit.Before;
import org.junit.Test;
import spike.emde.card.model.Card;
import spike.emde.card.model.CardExport;
import spike.emde.card.model.CardTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ExportModelConverterImplTest {
    private Card dummyCard;

    @Before
    public void setUp() throws Exception {
        dummyCard = CardTest.getDummyCard();
    }
    @Test
    public void shouldBeSameAsCard () {
        ExportModelConverter exportModelConverter = new ExportModelConverterImpl();
        CardExport cardExport = exportModelConverter.convert(dummyCard);
        assertEquals(dummyCard.getId(), cardExport.getId());
        assertEquals(dummyCard.getBrief(), cardExport.getBrief());
        assertEquals(dummyCard.getContent(), cardExport.getContent());
        // List and LocalDate need to convert to String.
        assertEquals(dummyCard.getAssignedToString(), cardExport.getAssignedTo());
        assertEquals(dummyCard.getDueDateString(), cardExport.getDueDate());
        // The card contains null, the cardExport contains ''
        assertNotEquals(dummyCard.getSize(), cardExport.getSize());
    }
}