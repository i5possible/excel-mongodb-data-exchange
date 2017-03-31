package spike.emde.excel;

import spike.emde.card.model.CardInfo;
import spike.emde.excel.entity.CellValue;
import spike.emde.excel.entity.CellValueFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hibernate.validator.internal.util.CollectionHelper.newHashMap;

public class ExcelData<T> {
    private List<Map<String, String>> rowData;

    public ExcelData(ExcelHeader excelHeader, List<T> toExportEntities) {
        Map<String, Field> fieldMap = excelHeader.getFieldMap();
        List fieldList = excelHeader.getFieldList();
        toExportEntities.forEach(entity -> {
            HashMap<String, String> cellValueMap = newHashMap();
            fieldList.forEach(fieldName -> {
                Field field = fieldMap.get(fieldName);
                if (field == null) {
                    return;
                }
                field.setAccessible(true);
                try {
                    Object value = field.get(entity);
                    CellValue cellValue = CellValueFactory.createCellValue(value, field);
                    if (cellValue == null) {
                        return;
                    }
                    cellValueMap.put(field.getName(), cellValue.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });
    }

    public static void main(String[] args) throws IntrospectionException {
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("content", CardInfo.class);
        Method readMethod = propertyDescriptor.getReadMethod();
        System.out.println(readMethod.getName());
    }
}
