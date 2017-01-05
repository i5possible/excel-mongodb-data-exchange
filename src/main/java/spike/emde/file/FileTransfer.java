package spike.emde.file;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
public class FileTransfer {

    final String sourceFilePath = "download/test.pdf";
    private final String targetPath = "src/main/resources/upload/";
    private final File sourceFile = new File("src/main/resources/download/test.pdf");
    private final File xlsxFile = new File("src/main/resources/download/firstCard.xlsx");

    public static void main(String[] args) {
        final String sourceFilePath = "download/firstCard.xlsx";
        ClassPathResource xlsxFile = new ClassPathResource(sourceFilePath);
        System.out.println(xlsxFile.getFilename());
    }

    /**
     * This method is trying to return an InputStreamResource instead of return the file.
     *
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/downloadpdf", produces = "application/pdf")
    public void downloadLocalFile(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment; filename="
                + sourceFile.getName());
        InputStream inputStream = new FileInputStream(sourceFile);
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();

    }

    /**
     * This method ISN'T WORK, I just leave it here.
     * The reason is that the ResponseRender use "application/octet_stream" to process the body.
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/downloadxlsxerror", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public ResponseEntity downloadXlsxError(HttpServletResponse response) throws IOException {
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Disposition", "attachment; filename=\"" + xlsxFile.getName() + "\"")
                .body(xlsxFile);
    }

    /**
     * This method use FileSystemResource to complete the download. it's simple to write and easy to read.
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/downloadxlsx", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public ResponseEntity<FileSystemResource> downloadXlsxFile(HttpServletResponse response) throws IOException {
        FileSystemResource fileSystemResource = new FileSystemResource(xlsxFile);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Disposition", "attachment; filename=\"" + xlsxFile.getName() + "\"")
                .body(fileSystemResource);
    }

    /**
     * This method use stream directly to deal with the file. There must be a better way to complete it.
     * @param uploadFile
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/upload")
    public ResponseEntity upload(@RequestParam(value = "uploadFile") MultipartFile uploadFile) throws IOException {
        InputStream inputStream = uploadFile.getInputStream();
        File file = new File(targetPath + uploadFile.getOriginalFilename());
//        file.deleteOnExit();
        if (file.exists()) {
            inputStream.close();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This sourceFile already exist.");
        } else {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            IOUtils.copy(inputStream, fileOutputStream);
            inputStream.close();
            fileOutputStream.close();
            return ResponseEntity.status(HttpStatus.OK).body("File upload successfully");
        }
    }
}
