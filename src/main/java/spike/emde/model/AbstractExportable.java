package spike.emde.model;

import spike.emde.utils.FieldValueGetter;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractExportable implements Exportable {
    /*
    We need a list of sorted schema string. --> write the schema as column head(Should be converted.). (fieldNameInOrder)
    We need a map of the schema and value. --> get the value of the schema. (fieldName, value)
     */
    private List<String> getSchemaNameList() {
        return Arrays.stream(this.getClass().getDeclaredFields())
                .filter(field -> field.getAnnotation(Column.class) != null)
                .sorted(Comparator.comparingInt(field->field.getAnnotation(Column.class).index()))
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    private Map<String, Object> getSchemaValueMap() {
        Map<String, Object> map = new HashMap<>();
        List<Field> list = Arrays.stream(this.getClass().getDeclaredFields())
                .filter(field -> field.getAnnotation(Column.class) != null)
                .collect(Collectors.toList());
        list.forEach(field -> map.put(field.getName(), FieldValueGetter.runGetter(field,this)));
        return map;
    }

    public String fetchTitle() {
        return Optional.ofNullable(this.getClass().getAnnotation(Title.class)).map(Title::name).orElse("");
    }

    public List<String> fetchSchema() {
        return this.getSchemaNameList();
    }

    public List<String> fetchSchema(List<String> exportField) {
        List<String> schemaNameList = this.getSchemaNameList();
        return schemaNameList.stream()
                .filter(exportField::contains)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> toList() {
        return this.toList(new ArrayList<>());
    }

    // TODO: 11/01/2017 schemaValueMap.get(field) : when to convert to string.
    public List<String> toList(List<String> exportField) {
        Map<String, Object> schemaValueMap = this.getSchemaValueMap();
        List<String> schema = fetchSchema(exportField);
        return schema.stream()
                .map(field -> schemaValueMap.get(field).toString())
                .collect(Collectors.toList());
    }

}
