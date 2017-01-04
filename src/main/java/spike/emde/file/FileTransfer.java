package spike.emde.file;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class FileTransfer {

    private final String targetPath = "src/main/resources/upload/";
    final String sourceFilePath = "download/firstCard.xlsx";
    private final File sourceFile = new File("src/main/resources/download/firstCard.xlsx");

    public static void main(String[] args) {
        final String sourceFilePath = "download/firstCard.xlsx";
        ClassPathResource xlsxFile = new ClassPathResource(sourceFilePath);
        System.out.println(xlsxFile.getFilename());
    }

    @GetMapping(value = "/downloadxlsx", produces = "application/xlsx")
    public ResponseEntity<InputStreamResource> downloadLocalFile() throws IOException {
        ClassPathResource xlsxFile = new ClassPathResource(sourceFilePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(xlsxFile.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(xlsxFile.getInputStream()));
    }

    @GetMapping(value = "/download")
    public ResponseEntity download() {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=\"" +
                        sourceFile.getName() + "\"").body((Object) sourceFile);
    }

    @GetMapping(value = "/downloadStream406", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity downloadStream() throws IOException {
        InputStream is = new FileInputStream(sourceFile);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len;
        byte[] buffer = new byte[4096];
        while ((len = is.read(buffer, 0, buffer.length)) != -1) {
            baos.write(buffer, 0, len);
        }
        return ResponseEntity.ok(baos);
    }

    @PostMapping(value = "/upload")
    public ResponseEntity upload(@RequestParam(value="uploadFile") MultipartFile uploadFile ) throws IOException {
        InputStream inputStream = uploadFile.getInputStream();
        File file = new File(targetPath+uploadFile.getOriginalFilename());
        file.deleteOnExit();
        if(file.exists()) {
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
