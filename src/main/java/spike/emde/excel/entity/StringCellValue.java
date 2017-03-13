package spike.emde.excel.entity;

public class StringCellValue extends CellValue{

    public StringCellValue(StringCellValue cellValue) {
        super(cellValue);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
