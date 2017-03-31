package spike.emde.dropdown;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        addNotes("notes.xlsx");
    }

    static void addNotes(String workbookName) throws IOException {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet();
        NotesService notesService = new NotesService();
        List<Note> notes = dummyNotes();

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Font font = workbook.createFont();
        font.setFontName("Arial");
        cellStyle.setFont(font);

        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);

        notesService.addNotesTosheet(sheet, 0, 0, notes, cellStyle);

        FileOutputStream fos = new FileOutputStream(workbookName);
        workbook.write(fos);
        fos.close();
        workbook.close();
    }

    static List<Note> dummyNotes() {
        List<Note> dummyNotes = new ArrayList();
        Note root = new Note("root", 0);
        Note note1 = new Note("level1-1", 1);
        note1.addChildren(new Note("2-1", 2));
        note1.addChildren(new Note("2-2", 2));
        root.addChildren(note1);
        Note note2 = new Note("level1-2", 1);
        note2.addChildren(new Note("3-1", 2));
        note2.addChildren(new Note("3-2", 2));
        note2.addChildren(new Note("3-3", 2));
        root.addChildren(note2);
        dummyNotes.add(root);

        Note note3 = new Note("root2", 0);
        note3.addChildren(new Note("node1", 1));
        note3.addChildren(new Note("node2", 1));
        note3.addChildren(new Note("node3", 1));
        note3.addChildren(new Note("node4", 1));
        dummyNotes.add(note3);
        return dummyNotes;
    }
}
