package spike.emde.card.adapter;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import spike.emde.card.model.CardExport;

import java.util.Optional;

@Service
public interface FileAdapter {
    Optional<Resource> write(CardExport... cardExport);
}
