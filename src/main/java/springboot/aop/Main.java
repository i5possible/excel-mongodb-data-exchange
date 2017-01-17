package springboot.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main{
    public static void main(String[] args) {
        Class javaConfigClass = AopConfig.class;
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(javaConfigClass);
        DemoAnnotationService demoAnnotationService = context.getBean(DemoAnnotationService.class);
        DemoMethodService demoMethodService = context.getBean(DemoMethodService.class);
        demoAnnotationService.add();
        demoMethodService.add();
        context.close();
    }
}
