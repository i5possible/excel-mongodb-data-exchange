package springboot.aop;

import org.springframework.stereotype.Component;

@Component
public class DemoAnnotationService {
    @AopAction( name = "Annotated intercept action.")
    public void add() {}
}
