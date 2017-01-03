package spike.emde.card.service;

import org.junit.Test;
import spike.emde.card.model.Card;
import spike.emde.card.repository.CardRepository;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CardServicesImplTest {

    @Test
    public void getCard() throws Exception {
        //given
        String cardId = "586607261f719b619c8ad42b";
        CardRepository cardRepository = mock(CardRepository.class);
        when(cardRepository.findOne(cardId)).thenReturn(new Card());
        CardServices cardServices = new CardServicesImpl(cardRepository);
        //when
        Optional<Card> card = cardServices.getCard(cardId);
        //then
        assertTrue(card.isPresent());
    }

}