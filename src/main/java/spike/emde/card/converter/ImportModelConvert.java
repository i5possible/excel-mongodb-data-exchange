package spike.emde.card.converter;

import spike.emde.card.model.CardImport;
import spike.emde.card.model.CardInfo;

public interface ImportModelConvert {
    CardInfo convert(CardImport cardImport);
}
