package spike.emde.card.model;

import java.util.List;

public interface Exportable extends AnnotatedSchema{
    List<String> toList();
}
