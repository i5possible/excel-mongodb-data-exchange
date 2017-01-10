package spike.emde.card.model;

import spike.emde.annotation.Schema;

import java.util.List;

public interface Exportable extends Schema{
    List<String> toList();
}
