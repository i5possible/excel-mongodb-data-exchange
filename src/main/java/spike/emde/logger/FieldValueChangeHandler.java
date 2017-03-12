package spike.emde.logger;

import spike.emde.entity.FieldValueChange;

import java.lang.reflect.Field;
import java.util.List;

@FunctionalInterface
public interface FieldValueChangeHandler {
    void apply(Field loggableField, Object beforeUpdate, Object afterUpdate, List<FieldValueChange> valueChangeList);
}
