package spike.emde.card.converter;

import org.springframework.stereotype.Component;
import spike.emde.card.model.Card;
import spike.emde.card.model.CardExport;

@Component
public class ExportModelConverterImpl implements ExportModelConverter {
    @Override
    public CardExport convert(Card card) {
        return new CardExport.Builder()
                .setId(card.getId())
                .setBrief(card.getBrief())
                .setContent(card.getContent())
                .setAssignedTo(card.getAssignedTo())
                .setDueDate(card.getDueDate())
                .setSize(card.getSize())
                .build();
    }
}
