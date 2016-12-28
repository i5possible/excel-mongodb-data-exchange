package spike.emde;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ExcelMongoDataExchangeApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ExcelMongoDataExchangeApplication.class).run(args);
    }
}
