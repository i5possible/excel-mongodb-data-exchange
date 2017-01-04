package spike.emde.card;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spike.emde.ExcelMongoDataExchangeApplication;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ExcelMongoDataExchangeApplication.class)
public class CardFunctionalTest {
    // TODO: 03/01/2017 Complete the funcitonal test.
    @Test
    public void shouldReturnCardInfoGivenId() {
        String cardId = "586607261f719b619c8ad42b";
    }
}
