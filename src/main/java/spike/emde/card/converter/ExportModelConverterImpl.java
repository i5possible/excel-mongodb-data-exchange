package spike.emde.card.converter;

import org.springframework.stereotype.Component;
import spike.emde.card.model.CardInfo;
import spike.emde.card.model.CardExport;

@Component
public class ExportModelConverterImpl implements ExportModelConverter {
    @Override
    public CardExport convert(CardInfo cardInfo) {
        CardExport export = new CardExport();
        export.setId(cardInfo.getId());
        export.setBrief(cardInfo.getBrief());
        export.setContent(cardInfo.getContent());
        export.setAssignedTo(cardInfo.getAssignedTo());
//        export.setDueDate(cardInfo.getDueDate());
        export.setSize(cardInfo.getId());
        return export;
    }
}
