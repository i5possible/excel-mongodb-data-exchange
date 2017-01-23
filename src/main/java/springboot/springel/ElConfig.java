package springboot.springel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("")
@PropertySource("springboot/springel/el.properties")
public class ElConfig {
    @Value("I love you!")
    private String normal;

    @Value("#{systemProperties['os.anem']}")
    private String osName;
}
