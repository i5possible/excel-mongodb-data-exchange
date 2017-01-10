package spike.emde.card.converter;

import spike.emde.card.model.CardInfo;
import spike.emde.card.model.CardImport;

public interface ImportModelConvert {
    CardInfo convert(CardImport cardImport);
}
