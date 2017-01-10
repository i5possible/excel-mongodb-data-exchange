package spike.emde.model;

import java.util.List;

public interface Exportable extends Schema{
    List<String> toList();
}
