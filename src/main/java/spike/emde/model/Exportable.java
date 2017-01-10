package spike.emde.model;

import spike.emde.utils.FieldValueGetter;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public interface Exportable {
    // Can't define static var in interface.
//    static Class FIELD_ANNOTATION_CLASS = ToExportField.class;
//    static Class TYPE_ANNOTATION_CLASS = ToExportField.class;
    List<String> toList();

    /*
    We need a list of sorted schema string. --> write the schema as column head. (fieldExportNameInOrder)
    We need a map of the schema and value. --> get the value of the schema. (fieldName, value)
     */


    static <T extends  Exportable> List<String> getSchemaNameListInOrder(Class<T> type) {
        return Arrays.stream(type.getDeclaredFields())
                .filter(field -> field.getAnnotation(ToExportField.class) != null)
                .sorted((a,b) -> {
                    int aIndex = a.getAnnotation(ToExportField.class).exportIndex();
                    int bIndex = b.getAnnotation(ToExportField.class).exportIndex();
                    return aIndex > bIndex ? 1 : -1;
                } )
                .map(field -> field.getAnnotation(ToExportField.class).exportName())
                .collect(Collectors.toList());
    }

    static <T extends  Exportable> List<FieldInfo> getSchemaInfoList(Class<T> type) {
        return Arrays.stream(type.getDeclaredFields())
                .filter(field -> field.getAnnotation(ToExportField.class) != null)
                .map(field -> {
                    FieldInfo fieldInfo = new FieldInfo();
                    fieldInfo.setField(field);
                    fieldInfo.setAnnotation(field.getAnnotation(ToExportField.class));
                    return fieldInfo;
                })
                .collect(Collectors.toList());
    }

    static  <T extends Exportable> Map<String, Object> getSchemaValueMap(T instance) {
        Map<String, Object> map = new HashMap<>();
        List<Field> list = Arrays.stream(instance.getClass().getDeclaredFields())
                .filter(field -> field.getAnnotation(ToExportField.class) != null)
                .collect(Collectors.toList());
        list.forEach(field -> map.put(field.getName(), FieldValueGetter.runGetter(field,instance)));
        return map;
    }

    static <T extends Exportable> String getModelExportName(Class<T> type) {
        return type.getAnnotation(ToExportClass.class).exportName();
    }

    static <T extends  Exportable> List<String> getValueListInOrder(T instance) {
        List<FieldInfo> schemaList = getSchemaInfoList(instance.getClass());
        Map<String, Object> schemaValueMap = getSchemaValueMap(instance);
        return schemaList.stream()
                .sorted(Comparator.comparingInt(fieldInfo -> fieldInfo.getAnnotation().exportIndex()))
                .map(fieldInfo -> schemaValueMap.get(fieldInfo.getField().getName()).toString())
                .collect(Collectors.toList());
    }
}
