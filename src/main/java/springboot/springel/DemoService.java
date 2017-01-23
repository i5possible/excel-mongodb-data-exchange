package springboot.springel;

import org.springframework.beans.factory.annotation.Value;

public class DemoService {
    @Value("injected other class properties")
    private String another;

    public String getAnother() {
        return another;
    }

    public void setAnother(String another) {
        this.another = another;
    }
}
