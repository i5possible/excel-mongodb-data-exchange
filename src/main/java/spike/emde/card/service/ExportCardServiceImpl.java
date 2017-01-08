package spike.emde.card.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import spike.emde.card.adapter.FileAdapter;
import spike.emde.card.converter.ExportModelConverter;
import spike.emde.card.model.Card;
import spike.emde.card.repository.CardRepository;

import java.util.Optional;

@Service
public class ExportCardServiceImpl implements ExportCardService {

    private final CardRepository cardRepository;
    private final FileAdapter adapter;
    private final ExportModelConverter converter;

    @Autowired
    public ExportCardServiceImpl(CardRepository cardRepository, FileAdapter adapter, ExportModelConverter converter) {
        this.cardRepository = cardRepository;
        this.adapter = adapter;
        this.converter = converter;
    }

    @Override
    public Optional<Resource> exportCardToExcel(String cardId) {
        Optional<Card> card = cardRepository.findById(cardId);
        return card.map(converter::convert).map(adapter::write).get();
    }
}
