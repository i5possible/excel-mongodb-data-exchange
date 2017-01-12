package spike.emde.card.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import spike.emde.card.adapter.FileAdapter;
import spike.emde.card.converter.ExportModelConverter;
import spike.emde.card.exception.CannotWriteToWorkbookException;
import spike.emde.card.model.CardInfo;
import spike.emde.card.repository.CardRepository;

import java.util.Map;
import java.util.Optional;

@Service
public class CardExportServiceImpl implements CardExportService {

    private final CardRepository cardRepository;
    private final FileAdapter adapter;
    private final ExportModelConverter converter;

    @Autowired
    public CardExportServiceImpl(CardRepository cardRepository, FileAdapter adapter, ExportModelConverter converter) {
        this.cardRepository = cardRepository;
        this.adapter = adapter;
        this.converter = converter;
    }

    @Override
    public Optional<Resource> exportCardsToExcel(Map<String, String> filterMap) throws CannotWriteToWorkbookException {
        // TODO: 10/01/2017 Implement the findBy... method.
        String cardId = filterMap.get("cardId");
        String fileName = filterMap.get("fileName");
        Optional<CardInfo> card = cardRepository.findById(cardId);
        if (card.isPresent()) {
            return card.map(converter::convert)
                    .map(cardExport -> adapter.write(fileName, cardExport))
                    .orElseThrow(CannotWriteToWorkbookException::new);
        } else {
            return Optional.empty();
        }
    }
}
