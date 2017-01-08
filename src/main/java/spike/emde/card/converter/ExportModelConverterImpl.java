package spike.emde.card.converter;

import spike.emde.card.model.Card;
import spike.emde.card.model.CardExport;

public class ExportModelConverterImpl implements ExportModelConverter {
    @Override
    public CardExport convert(Card card) {
        return new CardExport.Builder()
                .setId(card.getId())
                .setBrief(card.getBrief())
                .setContent(card.getContent())
                .setAssignedTo(card.getAssignedTo())
                .setDueDate(card.getDueDate()).build();
    }
}
