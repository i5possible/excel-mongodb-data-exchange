package spike.emde.card.converter;

import org.springframework.stereotype.Component;
import spike.emde.card.model.Card;
import spike.emde.card.model.CardExport;

@Component
public class ExportModelConverterImpl implements ExportModelConverter {
    @Override
    public CardExport convert(Card card) {
        CardExport export = new CardExport();
        export.setId(card.getId());
        export.setBrief(card.getBrief());
        export.setContent(card.getContent());
        export.setAssignedTo(card.getAssignedTo());
        export.setDueDate(card.getDueDate());
        export.setSize(card.getId());
        return export;
    }
}
