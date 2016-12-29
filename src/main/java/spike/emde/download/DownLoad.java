package spike.emde.download;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
public class DownLoad {

    private final File file = new File("src/main/resources/download/toDownLoadFile");

    public static void main(String[] args) {
        File file = new File("src/main/resources/download/toDownLoadFile");
    }

    @GetMapping(value = "/download406", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity downloadLocalFile() {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"").body(file);
    }

    @GetMapping(value = "/download")
    public ResponseEntity download() {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"").body(file);
    }

    @GetMapping(value = "/downloadStream406", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity downloadStream() throws IOException {
        InputStream is = new FileInputStream(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len;
        byte[] buffer = new byte[4096];
        while ((len = is.read(buffer, 0, buffer.length)) != -1) {
            baos.write(buffer, 0, len);
        }
        return ResponseEntity.ok(baos);
    }
}
