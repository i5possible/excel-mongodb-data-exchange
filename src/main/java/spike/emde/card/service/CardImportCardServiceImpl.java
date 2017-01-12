package spike.emde.card.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import spike.emde.card.adapter.ExcelAdapter;
import spike.emde.card.converter.ImportModelConvert;
import spike.emde.card.repository.CardRepository;

import java.util.Optional;

public class CardImportCardServiceImpl implements CardImportService {
    @Autowired
    ExcelAdapter excelAdapter;

    @Autowired
    ImportModelConvert importModelConvert;

    @Autowired
    CardRepository cardRepository;

    @Override
    public void writeCardToDB(Optional<Resource> resource) {
        resource.map(excelAdapter::read).map(list -> list
                .stream()
                .map(importModelConvert::convert)
                .peek(cardRepository::save));
    }
}
