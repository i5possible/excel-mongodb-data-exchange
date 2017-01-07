package spike.emde.card.converter;

import spike.emde.card.model.Card;
import spike.emde.card.model.CardExport;

public interface ExportModelConverter {
    CardExport convert(Card card);
}
