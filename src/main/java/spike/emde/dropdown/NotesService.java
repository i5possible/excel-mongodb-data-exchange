package spike.emde.dropdown;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.RegionUtil;

import java.util.List;

public class NotesService {
    public void addNotesTosheet(Sheet sheet, int rowNumber, int columnNum, List<Note> notes, CellStyle cellStyle) {
        if (CollectionUtils.isEmpty(notes)) {
            return;
        }



        int size = notes.size();
        int currentRowNum = rowNumber;
        for (int i = 0; i < size; i++) {
            Note note = notes.get(i);
            Row row = CellUtil.getRow(currentRowNum, sheet);
            CellUtil.createCell(row, columnNum, note.getName(), cellStyle);
            int count = note.count();
            CellRangeAddress cellRangeAddress = new CellRangeAddress(
                    currentRowNum, currentRowNum + count - 1, columnNum, columnNum);
            List<Note> children = note.getChildren();
            addNotesTosheet(sheet, currentRowNum, columnNum + 1, children, cellStyle);

            if (note.isTopLevel()) {
                currentRowNum += count + 1;
            } else if (note.isLeaf()) {
                currentRowNum++;
            } else {
                currentRowNum += count;
            }

            if (count > 1) {
                addRegionBorder(sheet, cellRangeAddress);
            }
        }

    }

    public void addRegionBorder(Sheet sheet, CellRangeAddress region) {
        RegionUtil.setBorderBottom(BorderStyle.THIN.getCode(), region, sheet);
        RegionUtil.setBorderLeft(BorderStyle.THIN.getCode(), region, sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN.getCode(), region, sheet);
        RegionUtil.setBorderTop(BorderStyle.THIN.getCode(), region, sheet);
        sheet.addMergedRegion(region);
    }
}
