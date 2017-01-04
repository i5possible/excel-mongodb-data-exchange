package hello;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hello {
    public static void main(String[] args) {
        System.out.println("hello!");
    }

    @GetMapping(path = "/hello")
    public ResponseEntity hello() {
        return ResponseEntity.status(HttpStatus.OK).body("HelloWorld");
    }
}
