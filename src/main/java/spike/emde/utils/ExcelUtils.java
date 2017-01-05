package spike.emde.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    private static final String excelSourcePath = "src/main/resources/excel/";

    /**
     * Assume that the content to write are String only.
     */
    public static void WriteToExcel(String[][] content, String filePath) throws IOException {
        SXSSFWorkbook sheets = new SXSSFWorkbook(100);
        int rows = content.length;
        Sheet sh = sheets.createSheet();
        for (int rowNum = 0; rowNum < rows; rowNum++) {
            Row row = sh.createRow(rowNum);
            int cellLength = content[rowNum].length;
            for (int cellNum = 0; cellNum < cellLength; cellNum++) {
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(content[rowNum][cellNum]);
            }
        }
        File file = new File(filePath);
        file.deleteOnExit();
        FileOutputStream out = new FileOutputStream(file);
        sheets.write(out);
        out.close();
        sheets.dispose();
    }


    public static void WriteAddressToExcel(String fileName) throws IOException {
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        Sheet sh = wb.createSheet();
        for (int rowNum = 0; rowNum < 3; rowNum++) {
            Row row = sh.createRow(rowNum);
            for (int cellNum = 0; cellNum < 4; cellNum++) {
                Cell cell = row.createCell(cellNum);
                String address = new CellReference(cell).formatAsString();
                cell.setCellValue(address);
            }
        }
        FileOutputStream out = new FileOutputStream(new File(excelSourcePath + fileName));
        wb.write(out);
        out.close();
        wb.dispose();
    }

    public static String[][] ReadFromExcel(String fileName) throws IOException {
        File file = new File(excelSourcePath + fileName);
        FileInputStream fileInputStream = new FileInputStream(file);
        return ReadFromInputStream(fileInputStream);
    }

    public static String[][] ReadFromInputStream(InputStream inputStream) throws IOException {
        XSSFWorkbook sheets = new XSSFWorkbook(inputStream);
        Sheet sheet = sheets.getSheetAt(0);
        List<String[]> bookListStringArray = new ArrayList<>();
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();
        for (int rowNum = firstRowNum; rowNum <= lastRowNum; rowNum++) {
            Row row = sheet.getRow(rowNum);
            short lastCellNum = row.getLastCellNum();
            List<String> rowStingArray = new ArrayList<>();
            for (int columnNum = 0; columnNum < lastCellNum; columnNum++) {
                Cell cell = row.getCell(columnNum);
                rowStingArray.add(cell.getStringCellValue());
            }
            System.out.println(rowStingArray.toString());
            bookListStringArray.add(rowStingArray.toArray(new String[0]));
        }
        sheets.close();
        return bookListStringArray.toArray(new String[0][0]);
    }

    public static void main(String[] args) throws IOException {
        String fileName = "HelloWorld.xlsx";
        WriteAddressToExcel(fileName);
        ReadFromExcel(fileName);
    }
}
