package spike.emde.excel.entity;

public abstract class CellValue {
    public Object value;

    public CellValue(Object value) {
        this.value = value;
    }

    public CellValue(CellValue cellValue) {
        this.value = cellValue.getValue();
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public abstract String toString();
}
