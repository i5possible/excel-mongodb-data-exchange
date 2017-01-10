package spike.emde.card.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class FieldValueGetter {
    static Object runGetter(Field field, Object o) {
        // MZ: Find the correct method
        for (Method method : o.getClass().getMethods()) {
            if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3))) {
                if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
                    // MZ: Method found, run it
                    try {
                        return method.invoke(o);
                    } catch (Exception ignore) {
                    }

                }
            }
        }
        return null;
    }
}
