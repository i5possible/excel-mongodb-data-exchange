package spike.emde.card.converter;

import spike.emde.card.model.CardExport;
import spike.emde.card.model.CardInfo;

public interface ExportModelConverter {
    CardExport convert(CardInfo cardInfo);
}
