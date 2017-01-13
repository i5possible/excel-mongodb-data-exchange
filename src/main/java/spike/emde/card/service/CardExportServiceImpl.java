package spike.emde.card.service;


import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import spike.emde.card.adapter.FileAdapter;
import spike.emde.card.converter.ExportModelConverter;
import spike.emde.card.exception.CannotWriteToWorkbookException;
import spike.emde.card.model.CardInfo;
import spike.emde.card.repository.CardRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class CardExportServiceImpl implements CardExportService {

    private final CardRepository cardRepository;
    private final FileAdapter adapter;
    private final ExportModelConverter converter;

    @Value(value = "${export.home}")
    private String homePath;

    @Value(value = "${export.excel.suffix}" )
    private String suffix;

    @Autowired
    public CardExportServiceImpl(CardRepository cardRepository, FileAdapter adapter, ExportModelConverter converter) {
        this.cardRepository = cardRepository;
        this.adapter = adapter;
        this.converter = converter;
    }

    @Override
    public Optional<Resource> exportCardsExcel(Map<String, String> filterMap) throws CannotWriteToWorkbookException {
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

    @Override
    public void exportCardsExcelToLocal(Map<String, String> varMap) {
        String cardId = varMap.get("cardId");
        String fileName = varMap.get("fileName");
        Optional<CardInfo> cardInfo = cardRepository.findById(cardId);
        Optional<Resource> resource = cardInfo.map(converter::convert)
                .map(cardExport -> adapter.write(fileName, cardExport))
                .orElse(Optional.empty());
        resource.ifPresent(r -> {
            File file = new File(homePath + fileName + suffix);
            FileOutputStream fileOutputStream = null;
            InputStream inputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file);
                inputStream = r.getInputStream();
                IOUtils.copy(inputStream, fileOutputStream);
            } catch (IOException ignored) {
            } finally {
                try {
                    inputStream.close();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
