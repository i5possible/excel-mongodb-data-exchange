package spike.emde.card.adapter;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import spike.emde.card.model.CardImport;
import spike.emde.card.model.Exportable;

import java.util.List;
import java.util.Optional;

@Service
public interface FileAdapter {
    Optional<Resource> write(Exportable... cardExport);
    List<CardImport> read(Resource resource);
}
