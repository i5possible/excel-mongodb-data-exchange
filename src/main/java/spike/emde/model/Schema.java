package spike.emde.model;

import spike.emde.utils.FieldValueGetter;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public interface Schema {
    default List<String> getSchemaList() {
        return Arrays.stream(this.getClass().getDeclaredFields())
                .filter(field -> field.getAnnotation(ToExportField.class) != null)
                .map(field -> field.getName())
                .collect(Collectors.toList());
    }

    default Map<String, String> getSchemaNameMap() {
        return Arrays.stream(this.getClass().getDeclaredFields())
                .filter(field -> field.getAnnotation(ToExportField.class) != null)
                .collect(Collectors.toMap(Field::getName,
                        field -> field.getAnnotation(ToExportField.class).exportName()
                ));
    }

    default Map<String, Object> getSchemaValueMap() {
        Map<String, Object> map = new HashMap<>();
        List<Field> list = Arrays.stream(this.getClass().getDeclaredFields())
                .filter(field -> field.getAnnotation(ToExportField.class) != null)
                .map(field -> field)
                .collect(Collectors.toList());
        list.forEach(field -> {
            map.put(field.getName(), FieldValueGetter.runGetter(field,this));
        });
        return map;
    }
}
