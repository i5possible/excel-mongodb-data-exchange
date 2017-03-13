package spike.emde.excel.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCellValue extends CellValue{
    private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    public DateCellValue(DateCellValue dateCellValue) {
        super(dateCellValue);
    }

    @Override
    public String toString() {
        return dateFormat.format((Date) value);
    }
}
