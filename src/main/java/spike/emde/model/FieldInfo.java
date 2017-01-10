package spike.emde.model;

import java.lang.reflect.Field;

public class FieldInfo {
    private Field field;
    private ToExportField annotation;

    public FieldInfo() {
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public ToExportField getAnnotation() {
        return annotation;
    }

    public void setAnnotation(ToExportField annotation) {
        this.annotation = annotation;
    }
}
