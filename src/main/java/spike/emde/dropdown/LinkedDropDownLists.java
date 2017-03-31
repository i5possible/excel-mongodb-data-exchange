package spike.emde.dropdown;
   /* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

          http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
   ==================================================================== */

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Demonstrates one technique that may be used to create linked or dependent
 * drop down lists. This refers to a situation in which the selection made
 * in one drop down list affects the options that are displayed in the second
 * or subsequent drop down list(s). In this example, the value the user selects
 * from the down list in cell A1 will affect the values displayed in the linked
 * drop down list in cell B1. For the sake of simplicity, the data for the drop
 * down lists is included on the same worksheet but this does not have to be the
 * case; the data could appear on a separate sheet. If this were done, then the
 * names for the regions would have to be different, they would have to include
 * the name of the sheet.
 * <p>
 * There are two keys to this technique. The first is the use of named area or
 * regions of cells to hold the data for the drop down lists and the second is
 * making use of the INDIRECT() function to convert a name into the addresses
 * of the cells it refers to.
 * <p>
 * Note that whilst this class builds just two linked drop down lists, there is
 * nothing to prevent more being created. Quite simply, use the value selected
 * by the user in one drop down list to determine what is shown in another and the
 * value selected in that drop down list to determine what is shown in a third,
 * and so on. Also, note that the data for the drop down lists is contained on
 * contained on the same sheet as the validations themselves. This is done simply
 * for simplicity and there is nothing to prevent a separate sheet being created
 * and used to hold the data. If this is done then problems may be encountered
 * if the sheet is opened with OpenOffice Calc. To prevent these problems, it is
 * better to include the name of the sheet when calling the setRefersToFormula()
 * method.
 *
 * @author Mark Beardsley [msb at apache.org]
 * @version 1.00 30th March 2012
 */
public class LinkedDropDownLists {

    public static void main(String[] args) throws IOException {
//        LinkedDropDownLists linkedDropDownLists = new LinkedDropDownLists("dropdown.xlsx");
        LinkedDropDownLists.AddBorder("border.xlsx");
    }


    static void AddBorder(String workbookName) throws  IOException {
        Workbook workbook;
        if (workbookName.endsWith(".xlsx")) {
            workbook = new XSSFWorkbook();
        } else {
            workbook = new HSSFWorkbook();
        }

        Sheet sheet = workbook.createSheet("Border");
        CellRangeAddressList addressList = new CellRangeAddressList(0, 0, 0, 0);
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        CellStyle medium = workbook.createCellStyle();
        medium.setBorderBottom(BorderStyle.MEDIUM);
        medium.setBorderLeft(BorderStyle.MEDIUM);
        medium.setBorderRight(BorderStyle.MEDIUM);
        medium.setBorderTop(BorderStyle.MEDIUM);
        sheet.setDefaultColumnStyle(0, medium);

        CellStyle thin = workbook.createCellStyle();
        thin.setBorderBottom(BorderStyle.THIN);
        thin.setBorderLeft(BorderStyle.THIN);
        thin.setBorderRight(BorderStyle.THIN);
        thin.setBorderTop(BorderStyle.THIN);
        sheet.setDefaultColumnStyle(2, thin);

        CreationHelper creationHelper = workbook.getCreationHelper();
        CellStyle dataFormat = workbook.createCellStyle();
        ExtendedColor extendedColor = creationHelper.createExtendedColor();
        extendedColor.setARGBHex("b8b8b8");
        System.out.println("indexed ? " + extendedColor.isIndexed());

        Row row = sheet.createRow(0);
        XSSFCellStyle background = (XSSFCellStyle) workbook.createCellStyle();

//        CellStyle background = row.getRowStyle();
        XSSFColor xssfColor = new XSSFColor(new java.awt.Color(184, 184, 184));
        background.setFillForegroundColor(xssfColor);
        background.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        row.setRowStyle(background);
        Cell cell = row.createCell(0);
        cell.setCellValue(123);

        Row row1 = sheet.createRow(1);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        row1.setRowStyle(cellStyle);
        Cell cell1 = row1.createCell(0);
        cell1.setCellValue(333);

        Cell test = CellUtil.createCell(row, 0, "2017-01-13");
        test.setCellType(CellType.STRING);

        CellRangeAddress region = new CellRangeAddress(
                4, //first row (0-based)
                6, //last row  (0-based)
                4, //first column (0-based)
                6  //last column  (0-based)
        );
        NotesService notesService = new NotesService();
        notesService.addRegionBorder(sheet, region);

        System.out.println(sheet.getLastRowNum());

        FileOutputStream fos = new FileOutputStream(workbookName);
        workbook.write(fos);
        fos.close();
        workbook.close();
    }



    LinkedDropDownLists(String workbookName) throws IOException {
        // Using the ss.usermodel allows this class to support both binary
        // and xml based workbooks. The choice of which one to create is
        // made by checking the file extension.
        Workbook workbook;
        if (workbookName.endsWith(".xlsx")) {
            workbook = new XSSFWorkbook();
        } else {
            workbook = new HSSFWorkbook();
        }

        // Build the sheet that will hold the data for the validations. This
        // must be done first as it will create names that are referenced
        // later.
        Sheet sheet = workbook.createSheet("Linked Validations");
        LinkedDropDownLists.buildDataSheet(sheet);

        // Build the first data validation to occupy cell A1. Note
        // that it retrieves it's data from the named area or region called
        // CHOICES. Further information about this can be found in the
        // static buildDataSheet() method below.
        CellRangeAddressList addressList = new CellRangeAddressList(0, 0, 0, 0);
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint("CHOICES");
//        DataValidationConstraint dvConstraint = dvHelper.
//                createExplicitListConstraint(new String[]{"input1", "input2", "input3"});
        DataValidation validation = dvHelper.createValidation(dvConstraint, addressList);
        sheet.addValidationData(validation);

        // Now, build the linked or dependent drop down list that will
        // occupy cell B1. The key to the whole process is the use of the
        // INDIRECT() function. In the buildDataSheet(0 method, a series of
        // named regions are created and the names of three of them mirror
        // the options available to the user in the first drop down list
        // (in cell A1). Using the INDIRECT() function makes it possible
        // to convert the selection the user makes in that first drop down
        // into the addresses of a named region of cells and then to use
        // those cells to populate the second drop down list.
        addressList = new CellRangeAddressList(0, 3000, 1, 1);
        dvConstraint = dvHelper.createFormulaListConstraint(
                "INDIRECT(UPPER($A$1))");
        validation = dvHelper.createValidation(dvConstraint, addressList);
        sheet.addValidationData(validation);
        // freeze top t
        sheet.createFreezePane(0, 2);

        FileOutputStream fos = new FileOutputStream(workbookName);
        workbook.write(fos);
        fos.close();
        workbook.close();
    }

    /**
     * Called to populate the named areas/regions. The contents of the cells on
     * row one will be used to populate the first drop down list. The contents of
     * the cells on rows two, three and four will be used to populate the second
     * drop down list, just which row will be determined by the choice the user
     * makes in the first drop down list.
     * <p>
     * In all cases, the approach is to create a row, create and populate cells
     * with data and then specify a name that identifies those cells. With the
     * exception of the first range, the names that are chosen for each range
     * of cells are quite important. In short, each of the options the user
     * could select in the first drop down list is used as the name for another
     * range of cells. Thus, in this example, the user can select either
     * 'Animal', 'Vegetable' or 'Mineral' in the first drop down and so the
     * sheet contains ranges named 'ANIMAL', 'VEGETABLE' and 'MINERAL'.
     *
     * @param dataSheet An instance of a class that implements the Sheet Sheet
     *                  interface (HSSFSheet or XSSFSheet).
     */
    private static final void buildDataSheet(Sheet dataSheet) {
        Row row = null;
        Cell cell = null;
        Name name = null;
        String sheetName = dataSheet.getSheetName();
        String sheetNamePrefix = "'" + sheetName + "'!";

        // The first row will hold the data for the first validation.
        row = dataSheet.createRow(10);
        cell = row.createCell(0);
        cell.setCellValue("Animal");
        cell = row.createCell(1);
        cell.setCellValue("Vegetable");
        cell = row.createCell(2);
        cell.setCellValue("Mineral");
        name = dataSheet.getWorkbook().createName();
        /*
        It's very import to add the sheet prefix, or the name name will be lost.
         */
        name.setRefersToFormula(sheetNamePrefix + "$A$11:$C$11");
        name.setNameName("CHOICES");

        // The next three rows will hold the data that will be used to
        // populate the second, or linked, drop down list.
        row = dataSheet.createRow(11);
        cell = row.createCell(0);
        cell.setCellValue("Lion");
        cell = row.createCell(1);
        cell.setCellValue("Tiger");
        cell = row.createCell(2);
        cell.setCellValue("Leopard");
        cell = row.createCell(3);
        cell.setCellValue("Elephant");
        cell = row.createCell(4);
        cell.setCellValue("Eagle");
        cell = row.createCell(5);
        cell.setCellValue("Horse");
        cell = row.createCell(6);
        cell.setCellValue("Zebra");
        name = dataSheet.getWorkbook().createName();
        name.setRefersToFormula(sheetNamePrefix + "$A$12:$G$12");
        name.setNameName("ANIMAL");

        row = dataSheet.createRow(12);
        cell = row.createCell(0);
        cell.setCellValue("Cabbage");
        cell = row.createCell(1);
        cell.setCellValue("Cauliflower");
        cell = row.createCell(2);
        cell.setCellValue("Potato");
        cell = row.createCell(3);
        cell.setCellValue("Onion");
        cell = row.createCell(4);
        cell.setCellValue("Beetroot");
        cell = row.createCell(5);
        cell.setCellValue("Asparagus");
        cell = row.createCell(6);
        cell.setCellValue("Spinach");
        cell = row.createCell(7);
        cell.setCellValue("Chard");
        name = dataSheet.getWorkbook().createName();
        name.setRefersToFormula(sheetNamePrefix + "$A$13:$H$13");
        name.setNameName("VEGETABLE");

        row = dataSheet.createRow(13);
        cell = row.createCell(0);
        cell.setCellValue("Bauxite");
        cell = row.createCell(1);
        cell.setCellValue("Quartz");
        cell = row.createCell(2);
        cell.setCellValue("Feldspar");
        cell = row.createCell(3);
        cell.setCellValue("Shist");
        cell = row.createCell(4);
        cell.setCellValue("Shale");
        cell = row.createCell(5);
        cell.setCellValue("Mica");
        name = dataSheet.getWorkbook().createName();
        name.setRefersToFormula(sheetNamePrefix + "$A$14:$F$14");
        name.setNameName("MINERAL");
    }
}
