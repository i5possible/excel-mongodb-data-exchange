package spike.emde.card.service;

import org.junit.Test;
import spike.emde.card.model.CardInfo;
import spike.emde.card.repository.CardRepository;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CardInfoServicesTest {
    @Test
    public void getCard() throws Exception {
        //given
        String cardId = "586607261f719b619c8ad42b";
        CardRepository cardRepository = mock(CardRepository.class);
        when(cardRepository.findOne(cardId)).thenReturn(new CardInfo());
        CardServices cardServices = new CardServices(cardRepository);
        //when
        Optional<CardInfo> card = cardServices.getCard(cardId);
        //then
        assertTrue(card.isPresent());
    }

    // TODO: 03/01/2017 A test for read card using card services is needed.
}