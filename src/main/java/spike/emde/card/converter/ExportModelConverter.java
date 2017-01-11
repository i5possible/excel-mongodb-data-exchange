package spike.emde.card.converter;

import spike.emde.card.model.CardInfo;
import spike.emde.card.model.CardExport;

public interface ExportModelConverter {
    CardExport convert(CardInfo cardInfo);
}
