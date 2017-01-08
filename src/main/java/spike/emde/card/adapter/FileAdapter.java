package spike.emde.card.adapter;

import org.springframework.core.io.Resource;
import spike.emde.card.model.CardExport;

import java.util.Optional;

public interface FileAdapter {
    Optional<Resource> write(CardExport... cardExport);
}
