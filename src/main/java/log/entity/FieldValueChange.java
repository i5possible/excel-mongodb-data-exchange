package log.entity;

public class FieldValueChange {
    private String fieldName;
    private FieldType fieldType;
    private String descriptiveName;
    private String valueBeforeChange;
    private String valueAfterChange;
    private boolean isDateDield = false;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public String getDescriptiveName() {
        return descriptiveName;
    }

    public void setDescriptiveName(String descriptiveName) {
        this.descriptiveName = descriptiveName;
    }

    public String getValueBeforeChange() {
        return valueBeforeChange;
    }

    public void setValueBeforeChange(String valueBeforeChange) {
        this.valueBeforeChange = valueBeforeChange;
    }

    public String getValueAfterChange() {
        return valueAfterChange;
    }

    public void setValueAfterChange(String valueAfterChange) {
        this.valueAfterChange = valueAfterChange;
    }

    public boolean isDateDield() {
        return isDateDield;
    }

    public void setDateDield(boolean dateDield) {
        isDateDield = dateDield;
    }
}
