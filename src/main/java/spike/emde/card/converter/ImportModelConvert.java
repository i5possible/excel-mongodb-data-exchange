package spike.emde.card.converter;

import spike.emde.card.model.Card;
import spike.emde.card.model.CardImport;

public interface ImportModelConvert {
    Card convert(CardImport cardImport);
}
