package spike.emde.download;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class DownLoad {

    private final String targetPath = "src/main/resources/upload/";
    private final File sourceFile = new File("src/main/resources/download/toDownLoadFile");

    public static void main(String[] args) {
        File file = new File("src/main/resources/download/toDownLoadFile");
    }

    @GetMapping(value = "/download406", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity downloadLocalFile() {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=\"" + sourceFile.getName() + "\"").body(sourceFile);
    }

    @GetMapping(value = "/download")
    public ResponseEntity download() {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=\"" + sourceFile.getName() + "\"").body(sourceFile);
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This sourceFile already exist.");
        } else {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            WriteToFile(inputStream,fileOutputStream);
            return ResponseEntity.status(HttpStatus.OK).body("File upload successfully");
        }
    }

    public void WriteToFile (InputStream inputStream,OutputStream outputStream) throws IOException {
        int len;
        byte[] buffer = new byte[4096];
        while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
            outputStream.write(buffer, 0, len);
        }
    }
}
