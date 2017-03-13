package spike.emde.excel;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;
import static org.hibernate.validator.internal.util.CollectionHelper.newHashMap;

public class ExcelHeader<T> {
    private List<String> fieldList = newArrayList();
    private Map<String,Field> fieldMap = newHashMap();
    private List<String> notSuchFieldNameList = newArrayList();

    public ExcelHeader(Class<T> clazz, List<String> toExportFieldNameList) {
        toExportFieldNameList.forEach(name -> {
            try {
                Field field = clazz.getDeclaredField(name);
                if (field == null) {
                    notSuchFieldNameList.add(name);
                }
                fieldList.add(name);
                fieldMap.put(name, field);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                notSuchFieldNameList.add(name);
            }
        });
    }

    public Map<String, Field> getFieldMap() {
        return fieldMap;
    }

    public List<String> getNotSuchFieldNameList() {
        return notSuchFieldNameList;
    }

    public List<String> getFieldList() {
        return fieldList;
    }
}
