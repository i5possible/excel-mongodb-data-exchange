package springboot.scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScopeConfig.class);
        DemoSingletonSerivce s1 = context.getBean(DemoSingletonSerivce.class);
        DemoSingletonSerivce s2 = context.getBean(DemoSingletonSerivce.class);

        DemoPropertypeSerivce p1 = context.getBean(DemoPropertypeSerivce.class);
        DemoPropertypeSerivce p2 = context.getBean(DemoPropertypeSerivce.class);

        System.out.println("s1 ?= s2 : " + s1.equals(s2));
        System.out.println("p1 ?= p2 : " + p1.equals(p2));

        context.close();
    }
}
