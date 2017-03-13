package spike.emde.excel.entity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CellValueFactory {
    public static CellValue createCellValue(Object value, Field field) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        String name = field.getType().getName();
        Class cellValueClass = null;
        cellValueClass = cellValueClass.forName(genCellValueClassName(name));
        CellValue cellValue = (CellValue) cellValueClass.getDeclaredConstructor(cellValueClass).newInstance(value);
        return cellValue;
    }

    private static String genCellValueClassName(String className) {
        return className + "CellValue";
    }
}
